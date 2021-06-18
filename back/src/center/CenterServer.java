package center;
import entity.User;
import exception.MyException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 中央服务的入口类
 *  @author 于添 / 季晓东
 *  @version 2.0
 */
public class CenterServer {
    /**
     * 存储当前在线的用户所对应的MySocket {@link center.MySocket}
     */
    public static ArrayList<MySocket> clients=new ArrayList<>();
    /**
     * 服务器socket
     */
    ServerSocket server;
    /**
     * 数据库会话
     */
    public static SqlSession sqlSession;

    public static void main(String[] args) {
        new CenterServer();
    }

    /**
     * MyBatis的配置文件的位置
     */
    String resource="mapper/mybatis-config.xml";

    /**
     * CenterServer的构造方法
     * <p>作用是：</p>
     * <li>打开服务器的ServerSocket</li>
     * <li>打开数据库连接</li>
     * <li>为连入的socket分配线程来处理</li>
     * @author 于添 / 季晓东
     */
    private CenterServer() {
        // 开启数据库连接
        try{
            InputStream inputStream  = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
        }catch(IOException e){e.printStackTrace();}
        //建立socket连接，每有一个socket连进来就开一个新线程。
        try{
            System.out.println("当前服务器IP地址："+InetAddress.getLocalHost());
        }catch(UnknownHostException e){
            System.out.println(e);
        }
        try {
            int port = 8080;
            //建立一个socket监听8080
            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                Mythread mythread = new Mythread(socket);
                mythread.start();
                synchronized (System.out){
                    System.out.println("another thread go on");
                    System.out.println(socket.getPort()+"\n=================================");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 服务器线程类
     * 用于监听用户的消息并处理
     * @author 季晓东 / 于添
     * @version 2.0
     */
    class Mythread extends Thread {
        /**
         * @value 这个线程所服务的MySocket {@link center.MySocket}
         */
        MySocket ssocket;
        /**
         * @value 这个线程所服务的socket
         */
        Socket targetClientSocket;
        /**
         * 构造方法
         * @param s 该线程所对应的MySocket
         */
        public Mythread(Socket s) {
            ssocket=new MySocket(s);
        }

        /**
         * 线程运行内容
         */
        public void run(){
            String msg;
            try {
                targetClientSocket = ssocket.getSocket();
                //try catch
                BufferedReader br = new BufferedReader(new InputStreamReader(targetClientSocket.getInputStream(),StandardCharsets.UTF_8));
                while ((msg = br.readLine()) != null) {
                    synchronized (System.out){
                        System.out.println(ssocket.getSocket());
                        System.out.println(new Date());
                        System.out.println(msg);
                        System.out.println("=================================");
                    }
                    //用户登录,登录成功后直接初始化
                    if(msg.startsWith("login#")){
                        login(msg);
                    }
                    //发送群聊消息
                    else if(msg.startsWith("G#")){
                        groupTalk(msg);
                    }
                    //创建群聊
                    else if(msg.startsWith("cgroup#")){
                        createGroup(msg);
                    }
                    //加入群聊
                    else if(msg.startsWith("jgroup#")){
                        joinGroup(msg);
                    }
                    //接受私聊消息
                    else if(msg.startsWith("P#")){
                        privateTalk(msg);
                    }
                    //注册账号
                    else if(msg.startsWith("register#")){
                        register(msg);
                    }
                    //登出
                    else if(msg.startsWith("logout")){
                        br.close();
                        offline(ssocket);
                        return;
                    }
                    //加好友
                    else if(msg.startsWith("add#")){//加好友
                        addFriend(msg);
                    }
                    //私聊历史记录
                    else if(msg.startsWith("ph")){
                        privateHistory(msg);
                    }
                    //群聊历史记录
                    else if(msg.startsWith("gh")){
                        groupHistory(msg);
                    }
                    //搜索小组
                    else if(msg.startsWith("sg")){
                        searchGroup(msg);
                    }
                    //搜索用户
                    else if(msg.startsWith("sf")){
                        searchUser(msg);
                    }
                    else {
                        sendToSpecificUser(ssocket.getSocket(),"command not exist");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * 处理用户登录流程的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void login(String msg){
            //重复登录直接拒绝，不需要调用方法
            if (ssocket.getUser()!=null){
                sendToSpecificUser(ssocket.getSocket(),"login#403");
                return;
            }
            try{
                //返回登录成功
                User user = ServerController.login(msg);
                ssocket.setUser(user);
                clients.add(ssocket);
                sendToSpecificUser(ssocket.getSocket(),"login#200");
                //返回初始化信息
                String result=ServerController.initialize(ssocket.getUser());
                sendToSpecificUser(ssocket.getUser().getId(),result);
            }catch (MyException e){
                //报错信息需要更加细化
                System.out.println(ssocket.getSocket());
                sendToSpecificUser(ssocket.getSocket(),"login#"+e.toString());
            }
        }
        /**
         * 处理用户发送群聊消息的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void groupTalk(String msg){
            try{
                ServerController.groupTalk(ssocket.getUser(),msg);
                //sendToSpecificUser(targetClientSocket.getPort(),"200");
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(targetClientSocket.getPort(),e.toString());
            }
        }
        /**
         * 处理用户创建群聊的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void createGroup(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.createGroup(msg));
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"cgroup#"+e.toString());
            }
        }
        /**
         * 处理用户加入群聊的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void joinGroup(String msg){
            try{
                ServerController.joinGroup(ssocket.getUser(),msg);
                sendToSpecificUser(ssocket.getSocket(),"jgroup#200");
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"jgroup#"+e.toString());
            }
        }
        /**
         * 处理用户发送私聊消息的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void privateTalk(String msg){
            try{
                ServerController.privateTalk(msg);
            }catch (MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"p#"+e.toString());
            }
        }
        /**
         * 处理用户发起注册的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void register(String msg){
            try{
                String result= ServerController.register(msg);
                sendToSpecificUser(ssocket.getSocket(),result);
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"register#"+e.toString());
            }
        }
        /**
         * 处理用户添加好友的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void addFriend(String msg){
            try{
                ServerController.addFriends(ssocket.getUser(),msg);
                sendToSpecificUser(ssocket.getSocket(),"add#200");
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"add#"+e.toString());
            }
        }
        /**
         * 处理用户拉取私聊历史数据的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void privateHistory(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.privateHistory(ssocket.getUser(),msg));
            }catch (MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"ph#"+e.toString());
            }
        }
        /**
         * 处理用户拉取群聊历史数据的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void groupHistory(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.groupHistory(ssocket.getUser(),msg));
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"gh#"+e.toString());
            }
        }
        /**
         * 处理用户搜索群聊的入口方法。
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void searchGroup(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.searchGroup(msg));
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"sg#"+e.toString());
            }
        }
        /**
         * 处理用户搜索用户的入口方法
         * @author 季晓东
         * @param msg 客户端的原信息
         */
        private void searchUser(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.searchUser(msg));
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"sf#"+e.toString());
            }
        }
    }

    /**
     * 群发消息方法
     * <p>用于向一批用户群发指定消息，其中只有在线用户会收到这些消息</p>
     * @author 于添 / 季晓东
     * @param userlist 计划群发信息的目标用户列表
     * @param msg 计划发出的信息
     */
    public static void send2Group(List<User> userlist,String msg){
        int i,j;
        ArrayList<MySocket> socketList = new ArrayList<>();
        int len=clients.size();
        try {
            //筛选所有在线的群友
            for(i=0;i<userlist.size();i++) {
                for (j = 0; j < len; j++) {
                    if (clients.get(j).getUser().getId() == userlist.get(i).getId()) {
                        socketList.add(clients.get(j));
                        //break;
                    }
                }
            }
            for(i=0;i<socketList.size();i++){
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(socketList.get(i).getSocket().getOutputStream(),StandardCharsets.UTF_8),true);
                pw.println(msg);
                pw.flush();
            }
        } catch (IOException e) {
            synchronized (System.out){
                e.printStackTrace();
            }
        }
    }

    /**
     * 私发消息方法
     * <p>用于向指定id的用户私发消息，该用户只有上线才能收到消息</p>
     * @author 季晓东 / 于添
     * @param targetUserID 目标用户的id
     * @param msg 将要发送的消息
     */
    public static void sendToSpecificUser(int targetUserID,String msg){
        try {
            int i;
            MySocket targetClient=null;
            for(i=0;i<clients.size();i++){
                if(clients.get(i).getUser().getId()==targetUserID){
                    targetClient = clients.get(i);
                }
            }
            if(targetClient!=null){
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(targetClient.getSocket().getOutputStream(), StandardCharsets.UTF_8),true);
                pw.println(msg);
                pw.flush();
            }
        } catch (IOException e) {
            synchronized (System.out){
                e.printStackTrace();
            }
        }
    }
    /**
     * 私发消息方法
     * <p>用于向指定socket私发消息</p>
     * @author 于添
     * @param targetSocket 目标socket
     * @param msg 将要发送的消息
     */
    public static void sendToSpecificUser(Socket targetSocket,String msg){
        try{
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(targetSocket.getOutputStream(), StandardCharsets.UTF_8), true);
            pw.println(msg);
            pw.flush();
        }catch(IOException e){
            synchronized (System.out){
                e.printStackTrace();
            }
        }
    }

    /**
     * 下线
     * @author 于添 / 季晓东
     * @param ssocket 将要下线的用户对应的MySocket {@link center.MySocket}
     */
    public static void offline(MySocket ssocket){
        try {
            int tNum=ssocket.getSocket().getPort();
            ssocket.getSocket().close();
            for (int i=0;i<clients.size();i++){
                //从缓存中移除该socket
                if(clients.get(i).getSocket().getPort()==tNum){
                    clients.remove(i);
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
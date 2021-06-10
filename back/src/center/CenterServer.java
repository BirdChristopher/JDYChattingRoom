package center;

import com.sun.security.ntlm.Server;
import entity.Group;
import entity.User;
import exception.MyException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class CenterServer {
    int port;
    //ArrayList<Socket> clients=new ArrayList<>();
    //存储当前的所有已登录的socket,未登录的socket可以使用但是不会被放入这个缓存中
    public static ArrayList<MySocket> clients=new ArrayList<>();

    ServerSocket server;

    public static SqlSession sqlSession;

    public static void main(String[] args) {
        new CenterServer();
    }

    String resource="mapper/mybatis-config.xml";

    private CenterServer() {
        // 开启数据库连接
        try{
            InputStream inputStream  = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSession = sqlSessionFactory.openSession();
        }catch(IOException e){e.printStackTrace();}
        //建立socket连接，每有一个socket连进来就开一个新线程。
        try {
            port = 8080;
            //建立一个socket监听8080
            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                //clients.add(new MySocket(socket));
                //clients.add(socket);
                Mythread mythread = new Mythread(socket);
                mythread.start();
                System.out.println("another thread go on");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //处理客户端请求
    class Mythread extends Thread {
        MySocket ssocket;
        //ssocket.getSocket(),简写版
        Socket targetClientSocket;
        public Mythread(Socket s) {
            ssocket=new MySocket(s);
        }
        public void run() {
            String msg;
            try {
                targetClientSocket = ssocket.getSocket();
                System.out.println(targetClientSocket.getPort());
                //try catch
                BufferedReader br = new BufferedReader(new InputStreamReader(targetClientSocket.getInputStream()));
                while ((msg = br.readLine()) != null) {
                    System.out.println(ssocket.getSocket());
                    System.out.println(new Date());
                    System.out.println(msg);
                    System.out.println("=================================");
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
                    else if(msg.startsWith("logout#")){
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
        //ok
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
        //ok
        private void groupTalk(String msg){
            try{
                ServerController.groupTalk(ssocket.getUser(),msg);
                //sendToSpecificUser(targetClientSocket.getPort(),"200");
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(targetClientSocket.getPort(),e.toString());
            }
        }
        //ok
        private void createGroup(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.createGroup(msg));
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"cgroup#"+e.toString());
            }
        }
        //ok
        private void joinGroup(String msg){
            try{
                ServerController.joinGroup(ssocket.getUser(),msg);
                sendToSpecificUser(ssocket.getSocket(),"jgroup#200");
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"jgroup#"+e.toString());
            }
        }
        //ok
        private void privateTalk(String msg){
            try{
                ServerController.privateTalk(msg);
            }catch (MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"p#"+e.toString());
            }
        }
        //ok
        private void register(String msg){
            try{
                String result= ServerController.register(msg);
                sendToSpecificUser(ssocket.getSocket(),result);
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"register#"+e.toString());
            }
        }
        //ok
        private void addFriend(String msg){
            try{
                ServerController.addFriends(ssocket.getUser(),msg);
                sendToSpecificUser(ssocket.getSocket(),"add#200");
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"add#"+e.toString());
            }
        }
        //ok
        private void privateHistory(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.privateHistory(ssocket.getUser(),msg));
            }catch (MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"ph#"+e.toString());
            }
        }
        //ok
        private void groupHistory(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.groupHistory(ssocket.getUser(),msg));
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"gh#"+e.toString());
            }
        }
        //ok
        private void searchGroup(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.searchGroup(msg));
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"sg#"+e.toString());
            }
        }
        //ok
        private void searchUser(String msg){
            try{
                sendToSpecificUser(ssocket.getSocket(),ServerController.searchUser(msg));
            }catch(MyException e){
                e.printStackTrace();
                sendToSpecificUser(ssocket.getSocket(),"sf#"+e.toString());
            }
        }
    }

    //群发消息
    public static void send2Group(List<User> userlist,String msg){
        int i,j;
        ArrayList<MySocket> socketList = new ArrayList<>();
        int len=clients.size();
        try {
            //筛选所有在线的群友
            for(i=0;i<userlist.size();i++){
                for(j=0;j<len;j++){
                    if(clients.get(j).getUser().getId() == userlist.get(i).getId()){
                        socketList.add(clients.get(j));
                        break;
                    }
                }
            }
            for(i=0;i<socketList.size();i++){
                PrintWriter pw = new PrintWriter(socketList.get(i).getSocket().getOutputStream(),true);
                pw.println(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //私发消息
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
                PrintWriter pw = new PrintWriter(targetClient.getSocket().getOutputStream(),true);
                pw.println(msg);
                pw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void sendToSpecificUser(Socket targetSocket,String msg){
        try{
            PrintWriter pw = new PrintWriter(targetSocket.getOutputStream(),true);
            pw.println(msg);
            pw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    //下线
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
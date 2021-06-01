import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class Server {
    int port;
    ArrayList<Socket> clients=new ArrayList<>();
    ServerSocket server;
    public static void main(String[] args) {
        new Server();
    }
    public Server() {
        try {
            port = 8080;
            server = new ServerSocket(port);
            while (true) {
                Socket socket = server.accept();
                clients.add(socket);
                Mythread mythread = new Mythread(socket);
                mythread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //处理客户端请求
    class Mythread extends Thread {
        MySocket ssocket;
        private BufferedReader br;
        private PrintWriter pw;//用于传给每个客户端消息
        public String msg;
        public Mythread(Socket s) {
            ssocket=new MySocket(s);
        }
        public void run() {
            try {
                br = new BufferedReader(new InputStreamReader(ssocket.getSocket().getInputStream()));
                msg = "欢迎【" + ssocket.getSocket().getPort() + "】进入聊天室！当前聊天室有【"
                        + clients.size() + "】人";
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
                msg+=date;
                sendtoall();
                while ((msg = br.readLine()) != null) {
                    if(msg.startsWith("G#")){
                        String gid=msg.split("#")[1];
                        int g=Integer.parseInt(gid);
                        String uid=msg.split("#")[2];
                        String message=msg.split("#")[3];
                        msg = gid+":"+"【" + ssocket.getUser().getUsername() + "】说：" + message;
                        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
                        msg+=date;
                        sendtogroup(g);
//                        sendtoall();
                        HistoryMessage.getInstance().addMessage(msg);
                    }
                    else if(msg.startsWith("cgroup#")){
                        int id=GroupList.getInstance().getList().size()+1;
                        pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                        pw.println(id);
                        pw.flush();
                        Group group=new Group(id);
                        group.add(ssocket.getUser());
                        GroupList.getInstance().add(group);
                    }
                    else if(msg.startsWith("jgroup#")){
                        String gid=msg.split("#")[1];
                        int g=Integer.parseInt(gid);
                        if(g>GroupList.getInstance().getList().size()){
                            pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                            pw.println("群聊不存在");
                            pw.println(0);
                            pw.flush();
                            return;
                        }
                        GroupList.getInstance().getList().get(g-1).add(ssocket.getUser());
                        pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                        pw.println(1);
                    }
                    else if(msg.startsWith("P#")){
                        String userfrom=msg.split("#")[1];
                        String userto=msg.split("#")[2];
                        String message=msg.split("#")[3];
                        msg=userfrom+":"+message;
                        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                        date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
                        msg+=date;
                        if(UserList.getInstance().searchUser(userto).getSocket()==null){
                            HistoryPrivateMessage.getInstance().addMessage(UserList.getInstance().searchUser(userto),msg);
                            continue;
                        }
                        int port=UserList.getInstance().searchUser(userto).getSocket().getPort();
                        sendToSpecificUser(port);
                    }
                    else if(msg.startsWith("private#")){
                        String u1=msg.split("#")[1];
                        String u2=msg.split("#")[2];
                        //加入表中
                        FriendList.getInstance().add(new Friend(UserList.getInstance().searchUser(u1),UserList.getInstance().searchUser(u2)));
                    }
                    else if(msg.startsWith("register#")){
                        pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                        pw.println("请输入用户名：");
                        pw.flush();
                        BufferedReader getter = new BufferedReader(new InputStreamReader(ssocket.getSocket().getInputStream()));
                        String username=getter.readLine();
                        pw.println("请输入密码：");
                        pw.flush();
                        String password=getter.readLine();
                        User user=new User(username,password,UserList.getInstance().getList().size()+1);
                        if(UserList.getInstance().addUser(user)){
                            pw.println("注册成功");
                            pw.println(1);
                        }
                        else {
                            pw.println("用户已存在");
                            pw.println(0);
                        }
                    }
                    else if(msg.startsWith("login#")){
                        if(ssocket.getUser()!=null){
                            pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                            pw.println("请不要重复登录");
                            continue;
                        }
                        pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                        pw.println("请输入用户名：");
                        BufferedReader getter = new BufferedReader(new InputStreamReader(ssocket.getSocket().getInputStream()));
                        String username=getter.readLine();
                        pw.println("请输入密码：");
                        String password=getter.readLine();
                        User user=new User(username,password,UserList.getInstance().getList().size()+1);
                        switch (UserList.getInstance().login(user)){
                            case 1:
                                pw.println("登录成功");
                                pw.println("欢迎："+user.getUsername());
                                pw.println(1);
                                pw.println(UserList.getInstance().getList().size());
                                ssocket.setUser(user);
                                user.setSocket(ssocket.getSocket());
                                UserList.getInstance().refreshUser(user);
                                try {
                                    pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                pw.println("群聊历史记录:");
                                for(int i=0;i<HistoryMessage.getInstance().getList().size();i++){
                                    pw.println(HistoryMessage.getInstance().getList().get(i));
                                }
                                pw.println("私聊历史记录:");
                                for(int i=0;i<HistoryPrivateMessage.getInstance().getList().size();i++){
                                    System.out.println("enter success");
                                    if(HistoryPrivateMessage.getInstance().getList().get(i).getToUser().getUsername().equals(ssocket.getUser().getUsername())){
                                        System.out.println("catch success");
                                        ArrayList<String> ls=HistoryPrivateMessage.getInstance().getList().get(i).getList();
                                        for(int j=0;j<ls.size();j++){
                                            pw.println(ls.get(j));
                                        }
                                        return;
                                    }
                                }
                                break;
                            case 0:
                                pw.println("密码错误");
                                pw.println(0);
                                break;
                            case -1:
                                pw.println("用户不存在");
                                pw.println(-1);
                                break;
                            case -2:
                                pw.println("用户已登录");
                                pw.println(-2);
                                break;
                            default:
                                break;
                        }
                    }
                    else if(msg.startsWith("logout#")){
                        offline();
                        return;
                    }
                    else if(msg.startsWith("add#")){//加好友
                        String friend=msg.split("#")[1];
                        if(UserList.getInstance().searchUser(friend)==null){
                            pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                            pw.println("用户不存在：");
                        }
                        else {
                            pw=new PrintWriter(UserList.getInstance().searchUser(friend).getSocket().getOutputStream(),true);
                            pw.println("用户"+ssocket.getUser().getUsername()+"想要添加你为好友，请输入[y/n]:");
                            pw.flush();
                            BufferedReader getter = new BufferedReader(new InputStreamReader(UserList.getInstance().searchUser(friend).getSocket().getInputStream()));
                            if(getter.readLine().equals("y")){
                                pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                                pw.println("添加成功");
                                //添加到好友列表
                            }
                            else {
                                pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                                pw.println("添加失败");
                            }
                        }
                    }
                    else {
                        pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                        pw.println(msg);
                        pw.println("指令不存在：");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void sendtoall() {
            try {
                System.out.println(msg);
                for (int i = 0; i < clients.size(); i++) {

                    System.out.println(clients.size());
                    System.out.println(clients.get(i).getPort());

                    if(ssocket.getSocket().getPort()==clients.get(i).getPort()){
                        System.out.println("repeat");
                        continue;
                    }
                    if(UserList.getInstance().searchUser(clients.get(i).getPort())==null){
                        System.out.println("unload");
                        continue;
                    }
                    pw = new PrintWriter(clients.get(i).getOutputStream(), true);
                    pw.println(msg);
                    pw.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void sendtogroup(int gid){
            try {
                System.out.println(msg);
                for(int i=0;i<GroupList.getInstance().getList().size();i++){
                    ArrayList<Group> list=GroupList.getInstance().getList();
                    if(list.get(i).getGid()==gid){
                        for(int j=0;j<list.get(i).getList().size();j++){
                            pw = new PrintWriter(list.get(i).getList().get(j).getSocket().getOutputStream(), true);
                            pw.println(msg);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void sendToSpecificUser(int port){
            try {
                System.out.println(msg);
                if(!UserList.getInstance().searchUser(port).getis_online()){
                    HistoryPrivateMessage.getInstance().addMessage(UserList.getInstance().searchUser(port),msg);
                    return;
                }
                for(int i=0;i<clients.size();i++){
                    if(port==clients.get(i).getPort()){
                        pw = new PrintWriter(clients.get(i).getOutputStream(), true);
                        pw.println(msg);
                        pw.flush();
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void offline(){
            try {
                msg="OFFLINE[#]"+ssocket.getUser().getUsername();
                System.out.println(msg);
                sendtoall();
                pw.close();
                br.close();
                int tNum=ssocket.getSocket().getPort();
                ssocket.getSocket().close();
                for (int i=0;i<clients.size();i++){
                    if(clients.get(i).getPort()==tNum){
                        clients.remove(i);
                        return;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
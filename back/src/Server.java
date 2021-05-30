import java.net.*;
import java.io.*;
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
                sendtoall();
                while ((msg = br.readLine()) != null) {
                    if(msg.startsWith("Group:")){
                        String message=msg.split(":")[1];
                        msg = "【" + ssocket.getUser().getUsername() + "】说：" + message;
                        sendtoall();
                        HistoryMessage.getInstance().addMessage(msg);
                    }
                    else if(msg.startsWith("Specific:")){
                        String username=msg.split(":")[1];
                        String message=msg.split(":")[2];
                        msg=ssocket.getUser().getUsername()+":"+message;
                        if(UserList.getInstance().searchUser(username).getSocket()==null){
                            HistoryPrivateMessage.getInstance().addMessage(UserList.getInstance().searchUser(username),msg);
                            continue;
                        }
                        int port=UserList.getInstance().searchUser(username).getSocket().getPort();
                        sendToSpecificUser(port);
                    }
                    else if(msg.startsWith("register")){
                        pw=new PrintWriter(ssocket.getSocket().getOutputStream(),true);
                        pw.println("请输入用户名：");
                        BufferedReader getter = new BufferedReader(new InputStreamReader(ssocket.getSocket().getInputStream()));
                        String username=getter.readLine();
                        pw.println("请输入密码：");
                        String password=getter.readLine();
                        User user=new User(username,password);
                        if(UserList.getInstance().addUser(user)){
                            pw.println("注册成功");
                        }
                        else {
                            pw.println("用户名已存在");
                        }
                    }
                    else if(msg.startsWith("login")){
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
                        User user=new User(username,password);
                        switch (UserList.getInstance().login(user)){
                            case 1:
                                pw.println("登录成功");
                                pw.println("欢迎："+user.getUsername());
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
                                break;
                            case -1:
                                pw.println("用户不存在");
                                break;
                            case -2:
                                pw.println("用户已登录");
                                break;
                            default:
                                break;
                        }
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
    }
}
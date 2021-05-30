import java.net.Socket;

public class User {
    private String username;
    private String password;
    private Socket socket;
    private boolean is_online;
    public String getUsername(){
        return this.username;
    }
    public String getPassword(){
        return this.password;
    }
    public Socket getSocket(){
        return this.socket;
    }
    public User(String username,String password){
        this.password=password;
        this.username=username;
        this.socket=null;
        this.is_online=false;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setSocket(Socket socket){
        this.socket=socket;
    }
    public void setis_online(boolean state){
        this.is_online=state;
    }
    public boolean getis_online(){
        return this.is_online;
    }
}

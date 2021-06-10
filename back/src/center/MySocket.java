package center;

import entity.User;

import java.net.Socket;

public class MySocket {
    private Socket socket;
    private User user;
    public MySocket(Socket socket){
        this.socket=socket;
        this.user=null;
    }
    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return this.user;
    }
    public Socket getSocket(){
        return this.socket;
    }
    public void setSocket(Socket socket){
        this.socket=socket;
    }
}

package center;

import entity.User;

import java.net.Socket;

/**
 * 用于存储已登录的socket的类
 * @author 于添
 * 用于将连入的socket与用户信息关联
 */
public class MySocket {
    /**
     * 客户端的socket
     */
    private Socket socket;
    /**
     * 客户端对应的用户信息
     */
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

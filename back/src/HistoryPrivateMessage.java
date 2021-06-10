import java.util.ArrayList;

public class HistoryPrivateMessage {
    private static HistoryPrivateMessage instance;
    private ArrayList<UserMessage>list;
    private HistoryPrivateMessage(){
        this.list=new ArrayList<>();
    }
    public static HistoryPrivateMessage getInstance(){
        if(instance==null){
            instance=new HistoryPrivateMessage();
        }
        return instance;
    }
    public void addMessage(User user,String message){
        for (int i=0;i<list.size();i++){
            if (user.getUsername().equals(list.get(i).getToUser().getUsername())){
                list.get(i).addMessage(message);
                return;
            }
        }
        UserMessage nm=new UserMessage(user);
        list.add(nm);
        list.get(list.size()-1).addMessage(message);
    }
    public ArrayList<UserMessage> getList(){
        return this.list;
    }
}
class UserMessage{
    private User toUser;
    private ArrayList<String> list;
    public User getToUser(){
        return this.toUser;
    }
    public void addMessage(String message){
        this.list.add(message);
        System.out.println("add success");
    }
    public UserMessage(User user){
        this.toUser=user;
        this.list=new ArrayList<>();
    }
    public ArrayList<String> getList(){
        return this.list;
    }
}
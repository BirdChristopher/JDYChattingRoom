import java.util.ArrayList;

public class FriendList {
    private static FriendList instance;
    private ArrayList<Friend> list;
    private FriendList(){
        this.list=new ArrayList<>();
    }
    public static FriendList getInstance(){
        if(instance==null){
            instance=new FriendList();
        }
        return instance;
    }
    public void add(Friend f1){
        this.list.add(f1);
    }
    public ArrayList<Friend> getList(){
        return this.list;
    }
}
class Friend{
    private User u1;
    private User u2;
    public Friend(User u1,User u2){
        this.u1=u1;
        this.u2=u2;
    }
}

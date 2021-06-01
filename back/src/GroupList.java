import java.util.ArrayList;

public class GroupList {
    private static GroupList instance;
    private ArrayList<Group> list;
    private GroupList(){
        this.list=new ArrayList<>();
    }
    public static GroupList getInstance(){
        if(instance==null){
            instance=new GroupList();
        }
        return instance;
    }
    public ArrayList<Group> getList(){
        return this.list;
    }
    public void add(Group g){
        this.list.add(g);
    }
}
class Group{
    private ArrayList<User> list;
    private int gid;
    public Group(int gid){
        this.list=new ArrayList<>();
        this.gid=gid;
    }
    public void add(User u1){
        this.list.add(u1);
    }
    public ArrayList<User> getList(){
        return this.list;
    }
    public int getGid(){
        return this.gid;
    }

}

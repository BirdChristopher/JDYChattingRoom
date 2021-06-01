import java.util.ArrayList;

public class UserList {
    private static UserList instance;
    private ArrayList<User> list;
    private UserList(){
        this.list=new ArrayList<>();
    }
    public static UserList getInstance(){
        if (instance==null){
            instance=new UserList();
        }
        return instance;
    }
    public boolean addUser(User user){
        for (int i=0;i<list.size();i++){
            if(user.getUsername().equals(list.get(i).getUsername())){
                return false;
            }
        }
        this.list.add(user);
        return true;
    }
    public int login(User user){
        for(int i=0;i<list.size();i++){
            if(user.getUsername().equals(list.get(i).getUsername())){
                if(user.getPassword().equals(list.get(i).getPassword())){
                    if(list.get(i).getis_online()){
                        return -2;
                    }
                    return 1;
                }
                else {
                    return 0;
                }
            }
        }
        return -1;
    }
    public void refreshUser(User user){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUsername().equals(user.getUsername())){
                list.get(i).setPassword(user.getPassword());
                list.get(i).setSocket(user.getSocket());
                list.get(i).setis_online(true);
            }
        }
    }
    public User searchUser(String username){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getUsername().equals(username)){
                return list.get(i);
            }
        }
        return null;
    }
    public User searchUser(int port){
        if(list==null){
            System.out.println("null");
            return null;
        }
        for(int i=0;i<list.size();i++){
            System.out.println(i);
            System.out.println(list.size());
            if(list.get(i).getSocket()==null){
                continue;
            }
            if(list.get(i).getSocket().getPort()==port){
                return list.get(i);
            }
        }
        return null;
    }
    public ArrayList<User> getList(){
        return this.list;
    }
}

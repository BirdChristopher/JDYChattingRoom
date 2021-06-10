package Service;
import center.CenterServer;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserService{
    //加入用户,不允许用户名重复！！！！！！！
    public static int addUser(User user){
        CenterServer.sqlSession.insert("addUser",user);
        CenterServer.sqlSession.commit();
        return user.getId();
    }
    //修改个人信息
    public static void refreshUser(User user){
        CenterServer.sqlSession.update("updateUserInfo",user);
        CenterServer.sqlSession.commit();
    }
    //寻找用户
    public static List<User> searchUser(User user){
        return CenterServer.sqlSession.selectList("findUser",user);
    }
}

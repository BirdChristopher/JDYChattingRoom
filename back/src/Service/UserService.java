package Service;
import center.CenterServer;
import entity.User;

import java.util.List;

/**
 * 用户信息数据库服务类
 * <p>包含了与用户信息相关的，与数据库交互的方法</p>
 * @author 季晓东
 */
public class UserService{
    /**
     * 加入用户
     * @param user 欲加入的用户信息
     * @return 新用户的id
     */
    public static int addUser(User user){
        CenterServer.sqlSession.insert("addUser",user);
        CenterServer.sqlSession.commit();
        return user.getId();
    }

    /**
     * 更新用户信息
     * @param user 新用户信息
     */
    public static void refreshUser(User user){
        CenterServer.sqlSession.update("updateUserInfo",user);
        CenterServer.sqlSession.commit();
    }

    /**
     * 寻找指定用户
     * @param user 目标用户信息
     * @return 符合条件的用户表
     */
    public static List<User> searchUser(User user){
        return CenterServer.sqlSession.selectList("findUser",user);
    }
}

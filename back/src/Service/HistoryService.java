package Service;

import center.CenterServer;
import com.jfoenix.animation.alert.CenterTransition;
import entity.*;

import java.util.List;

/**
 * 历史记录服务类
 * <p>包含所有与历史记录和关系记录相关的与数据库交互的方法</p>
 * @author 季晓东
 */
public class HistoryService {
    /**
     * 获取好友列表
     * @param user 操作发起者
     * @return 好友列表
     */
    public static List<User> getFriendList(User user){
        return CenterServer.sqlSession.selectList("userFindFriends",user);
    }

    /**
     * 获取群聊列表
     * @param user 操作发起者
     * @return 返回用户的群聊列表
     */
    public static List<Group> getGroupList(User user){
        return CenterServer.sqlSession.selectList("userFindGroups",user);
    }

    /**
     * 获取群聊历史记录
     * @param group 指定的群聊对象的信息
     * @return 群聊信息列表
     */
    public static List<GroupMessage> getGroupMessage(Group group){
        return CenterServer.sqlSession.selectList("findGroupMessage",group);
    }

    /**
     * 获取私聊历史记录
     * @param friend 指定的好友关系
     * @return 私聊信息列表
     */
    public static List<PrivateMessage> getPrivateMessage(Friends friend){
        return CenterServer.sqlSession.selectList("findPrivateMessage",friend);
    }

    /**
     * 建立好友关系
     * @param friend 欲建立的好友关系
     */
    public static void buildFriendShip(Friends friend){
        CenterServer.sqlSession.insert("buildFriendShip",friend);
        CenterServer.sqlSession.commit();
    }

    /**
     * 加入私聊历史记录
     * @param privateMessage 欲加入的私聊历史记录
     */
    public static void addPrivateMessage(PrivateMessage privateMessage){
        CenterServer.sqlSession.insert("addPrivateMessage",privateMessage);
        CenterServer.sqlSession.commit();
    }

    /**
     * 加入群聊历史记录
     * @param groupMessage 欲加入的群聊历史记录
     */
    public static void addGroupMessage(GroupMessage groupMessage){
        CenterServer.sqlSession.insert("addGroupMessage",groupMessage);
        CenterServer.sqlSession.commit();
    }

    /**
     * 群聊加入新成员
     * @param belong_entry 欲加入的从属关系
     */
    public static void addMember2Group(userBelong2Group belong_entry) {
        CenterServer.sqlSession.insert("addMember", belong_entry);
        CenterServer.sqlSession.commit();
    }

    /**
     * 退出群聊
     * @param belong_entry 欲删除的从属记录
     */
    public static void quitGroup(userBelong2Group belong_entry){
        CenterServer.sqlSession.delete("quitGroup",belong_entry);
        CenterServer.sqlSession.commit();
    }

    /**
     * 提取所有的群聊成员
     * @param group 指定的群聊
     * @return 成员列表
     */
    public static List<User> showAllMembers(Group group){
        return CenterServer.sqlSession.selectList("showAllMembers",group);
    }

    /**
     * 寻找用户-群聊从属关系
     * @param user 指定的用户（携带了从属信息）
     * @return 从属关系记录
     */
    public static List<userBelong2Group> showBelongInfo(User user){
        return CenterServer.sqlSession.selectList("showBelongInfo",user);
    }

    /**
     * 寻找好友关系
     * @param friend 指定的好友关系
     * @return 好友关系记录
     */
    public static List<Friends> findFriendRelation(Friends friend){
        return CenterServer.sqlSession.selectList("findFriendRelation",friend);
    }
}

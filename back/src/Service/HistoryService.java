package Service;

import center.CenterServer;
import com.jfoenix.animation.alert.CenterTransition;
import entity.*;

import java.util.List;

//历史记录服务类
public class HistoryService {
    // 获取好友列表
    public static List<User> getFriendList(User user){
        return CenterServer.sqlSession.selectList("userFindFriends",user);
    }
    // 获取群聊列表，还需要考虑。
    public static List<Group> getGroupList(User user){
        return CenterServer.sqlSession.selectList("userFindGroups",user);
    }
    public static List<GroupMessage> getGroupMessage(Group group){
        return CenterServer.sqlSession.selectList("findGroupMessage",group);
    }
    public static List<PrivateMessage> getPrivateMessage(Friends friend){
        return CenterServer.sqlSession.selectList("findPrivateMessage",friend);
    }

    public static void buildFriendShip(Friends friend){
        CenterServer.sqlSession.insert("buildFriendShip",friend);
        CenterServer.sqlSession.commit();
    }

    public static void addPrivateMessage(PrivateMessage privateMessage){
        CenterServer.sqlSession.insert("addPrivateMessage",privateMessage);
        CenterServer.sqlSession.commit();
    }

    public static void addGroupMessage(GroupMessage groupMessage){
        CenterServer.sqlSession.insert("addGroupMessage",groupMessage);
        CenterServer.sqlSession.commit();
    }

    public static void addMember2Group(userBelong2Group belong_entry) {
        CenterServer.sqlSession.insert("addMember", belong_entry);
        CenterServer.sqlSession.commit();
    }
    public static void quitGroup(userBelong2Group belong_entry){
        CenterServer.sqlSession.delete("quitGroup",belong_entry);
        CenterServer.sqlSession.commit();
    }
    public static List<User> showAllMembers(Group group){
        return CenterServer.sqlSession.selectList("showAllMembers",group);
    }
    public static List<userBelong2Group> showBelongInfo(User user){
        return CenterServer.sqlSession.selectList("showBelongInfo",user);
    }
    public static List<Friends> findFriendRelation(Friends friend){
        return CenterServer.sqlSession.selectList("findFriendRelation",friend);
    }
}

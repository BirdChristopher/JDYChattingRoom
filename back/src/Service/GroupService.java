package Service;

import java.util.ArrayList;
import java.util.List;

import center.CenterServer;
import entity.Group;
public class GroupService {
    //创建群聊
    public static int addGroup(Group group){
        CenterServer.sqlSession.insert("addGroup",group);
        CenterServer.sqlSession.commit();
        return group.getId();
    }
    //寻找群聊
    public static List<Group> findGroup(Group group){
        return CenterServer.sqlSession.selectList("findGroupInfo",group);
    }
}


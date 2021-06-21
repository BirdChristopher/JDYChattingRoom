package Service;

import java.util.ArrayList;
import java.util.List;

import center.CenterServer;
import entity.Group;

/**
 * 小组信息服务类
 * <p>包含与数据库交互的方法</p>
 * @author 季晓东
 */
public class GroupService {
    /**
     * 加入群聊信息
     * @param group 存储了所有的新群聊参数
     * @return 新群聊的id
     */
    public static int addGroup(Group group){
        CenterServer.sqlSession.insert("addGroup",group);
        CenterServer.sqlSession.commit();
        return group.getId();
    }

    /**
     * 寻找群聊
     * @param group 包含了搜索的限制条件
     * @return 满足要求的群聊列表
     */
    public static List<Group> findGroup(Group group){
        return CenterServer.sqlSession.selectList("findGroupInfo",group);
    }
}


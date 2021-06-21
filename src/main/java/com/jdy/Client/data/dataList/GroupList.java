package com.jdy.Client.data.dataList;

import com.jdy.Client.data.group.Group;

import java.util.Vector;

/**
 * 群聊列表类.
 *
 * 存储当前登录用户的群聊信息.
 *
 * @author dh
 */
public class GroupList {
    public static Vector<Group> groups = new Vector<>();

    public GroupList() {}

    /**
     * 根据id查找群聊.
     * @param id 群聊id
     * @return 返回对应群聊，不存在返回null
     */
    public static Group getGroupById(String id) {
        Group group = null;
        for (Group g : groups) {
            if (g.getGid().equals(id)){
                group = g;
                break;
            }
        }
        return group;
    }
}

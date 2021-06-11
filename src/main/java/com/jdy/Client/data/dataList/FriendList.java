package com.jdy.Client.data.dataList;

import com.jdy.Client.data.user.User;

import java.util.Vector;

/**
 * 好友列表类.
 *
 * 存储当前登录用户的好友信息.
 *
 * @author dh
 */
public class FriendList {
    public static Vector<User> friends = new Vector<>();

    public FriendList() {}

    /**
     * 根据id查找好友.
     * @param id 用户id
     * @return 对应id的用户，不存在返回null
     */
    public static User getUserById(String id) {
        User user = null;
        for (User u : friends) {
            if (u.getUid().equals(id))
                user = u;
        }
        return user;
    }
}

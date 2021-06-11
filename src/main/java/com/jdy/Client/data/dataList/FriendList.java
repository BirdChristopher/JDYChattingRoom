package com.jdy.Client.data.dataList;

import com.jdy.Client.data.user.User;

import java.util.Vector;

public class FriendList {
    public static Vector<User> friends = new Vector<>();

    public FriendList() {}

    public static User getUserById(String id) {
        User user = null;
        for (User u : friends) {
            if (u.getUid().equals(id))
                user = u;
        }
        return user;
    }
}

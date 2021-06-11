package com.jdy.Client.data.dataList;

import com.jdy.Client.data.user.User;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class MemberList {
    private static ConcurrentHashMap<String, ArrayList<User>> memberLists = new ConcurrentHashMap<>();

    public MemberList() {}

    public static ArrayList<User> getList(String id) {
        if (memberLists.get(id) == null) {
            ArrayList<User> list = new ArrayList<>();
            memberLists.put(id, list);
            return list;
        }
        return memberLists.get(id);
    }

    public static User getUserById(String cid, String uid) {
        User user = null;
        ArrayList<User> list = getList(cid);
        for (User u : list) {
            if (u.getUid().equals(uid)) {
                user = u;
                break;
            }
        }
        return user;
    }
}

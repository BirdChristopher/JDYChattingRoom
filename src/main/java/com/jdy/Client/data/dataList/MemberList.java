package com.jdy.Client.data.dataList;

import com.jdy.Client.data.user.User;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 聊天成员列表类.
 *
 * 存储所有聊天的成员列表，用ConcurrentHashMap存储，key为聊天id，value为成员列表，
 * 懒加载，用户点击对应聊天时才会新建对应成员列表.
 *
 * @author dh
 */
public class MemberList {
    private static ConcurrentHashMap<String, ArrayList<User>> memberLists = new ConcurrentHashMap<>();

    public MemberList() {}

    /**
     * 更具聊天id返回成员列表.
     * @param id 聊天id
     * @return 成员列表，不存在则新建
     */
    public static ArrayList<User> getList(String id) {
        if (memberLists.get(id) == null) {
            ArrayList<User> list = new ArrayList<>();
            memberLists.put(id, list);
            return list;
        }
        return memberLists.get(id);
    }

    /**
     * 查找成员.
     * @param cid 聊天id
     * @param uid 用户id
     * @return 对应用户，不存在返回null
     */
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

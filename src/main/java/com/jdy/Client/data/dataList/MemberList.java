package com.jdy.Client.data.dataList;

import com.jdy.Client.data.user.User;

import java.util.HashMap;

/**
 * 聊天成员列表类.
 *
 * 存储所有聊天的成员列表，用ConcurrentHashMap存储，key为聊天id，value为成员列表，
 * 懒加载，用户点击对应聊天时才会新建对应成员列表.
 *
 * @author dh
 */
public class MemberList {
    private static HashMap<String, HashMap<String, User>> memberLists = new HashMap<>();

    public MemberList() {}

    /**
     * 更具聊天id返回成员列表.
     * @param id 聊天id
     * @return 成员列表，不存在则新建
     */
    public static HashMap<String, User> getList(String id) {
        if (memberLists.get(id) == null) {
            HashMap<String, User> list = new HashMap<>();
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
        HashMap<String, User> list = getList(cid);
        return list.get(uid);
    }
}

package com.jdy.Client.data.dataList;

import com.jdy.Client.data.message.Message;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 消息列表类
 *
 * 存储所有聊天的消息列表，用ConcurrentHashMap存储，key为聊天id，value为消息列表，
 * 懒加载，用户点击对应聊天时才会新建对应消息列表.
 *
 * @author dh
 */
public class MessageList {
    private static ConcurrentHashMap<String, ArrayList<Message>> messageLists = new ConcurrentHashMap<>();

    public MessageList() {}

    /**
     * 获取消息记录.
     * @param id 聊天id
     * @return 消息列表，不存在则新建
     */
    public static ArrayList<Message> getList(String id) {
        if (messageLists.get(id) == null) {
            ArrayList<Message> list = new ArrayList<>();
            messageLists.put(id, list);
            return list;
        }
        return messageLists.get(id);
    }
}

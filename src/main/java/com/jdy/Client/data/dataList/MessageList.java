package com.jdy.Client.data.dataList;

import com.jdy.Client.data.message.Message;

import java.util.ArrayList;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class MessageList {
    private static ConcurrentHashMap<String, ArrayList<Message>> messageLists = new ConcurrentHashMap<>();

    public MessageList() {}

    public static ArrayList<Message> getList(String id) {
        if (messageLists.get(id) == null) {
            ArrayList<Message> list = new ArrayList<>();
            messageLists.put(id, list);
            return list;
        }
        return messageLists.get(id);
    }
}

package com.jdy.Client.data.chat;

import com.jdy.Client.data.message.Message;
import com.jdy.Client.data.user.User;

import java.util.Vector;

/**
 * 聊天类.
 *
 * 存储一个聊天消息和成员.
 *
 * @author dh
 */
public class Chat {
    protected Vector<User> members;
    protected Vector<Message> messages;

    /**
     * 更新消息列表.
     * @param message 新增消息
     */
    public void updateMessages(Message message) {
        messages.add(message);
    }

    public Vector<User> getMembers() {
        return members;
    }

    public Vector<Message> getMessages() {
        return messages;
    }
}

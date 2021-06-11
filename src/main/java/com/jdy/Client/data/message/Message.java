package com.jdy.Client.data.message;

import com.jdy.Client.data.user.CurrentUser;
import com.jdy.Client.data.user.User;

import java.sql.Timestamp;

public class Message {
    private final User sender;
    private final String cid;
    // 内容
    private final String content;
    // 时间戳
    private final MessageType type;

    public Message(String cid , User sender, String content, MessageType type) {
        this.cid = cid;
        this.sender = sender;
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public User getSender() {
        return sender;
    }

    public String getCid() { return cid; }

    public MessageType getType() { return type; }
}

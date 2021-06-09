package com.jdy.Client.data.message;

import com.jdy.Client.data.user.CurrentUser;
import com.jdy.Client.data.user.User;

import java.sql.Timestamp;

public class Message {
    private final User sender;
    private final String cid;
    private final MessageType type;
    // 内容
    private final String content;
    // 时间戳
    private final Timestamp timestamp;

    public Message(String cid , User sender, String content, Timestamp timestamp, MessageType type) {
        this.cid = cid;
        this.sender = sender;
        this.type = type;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public User getSender() {
        return sender;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getCid() { return cid; }

    public MessageType getType() { return type; }
}

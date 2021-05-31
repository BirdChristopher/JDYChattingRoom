package com.jdy.Client.data.message;

import com.jdy.Client.data.user.CurrentUser;

import java.sql.Timestamp;

public class Message {
    private final String sender;
    private final String receiver;
    // 私聊消息还是群聊消息
    private final MessageType type;
    // 内容
    private final String content;
    // 时间戳
    private final Timestamp timestamp;

    public Message(String sender, String receiver, String content, Timestamp timestamp, MessageType type) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public MessageType getType() {
        return type;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}

enum MessageType {
    PRIVATE, GROUP
}

package com.jdy.Client.data.chat;

import com.jdy.Client.data.message.Message;
import com.jdy.Client.data.user.User;

import java.util.Vector;

public class Chat {
    protected Vector<User> members;
    protected Vector<Message> messages;

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

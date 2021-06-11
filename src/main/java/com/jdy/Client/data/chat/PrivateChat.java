package com.jdy.Client.data.chat;

import com.jdy.Client.data.user.User;

public class PrivateChat extends Chat {
    public PrivateChat(User user1, User user2) {
        this.members.add(user1);
        this.members.add(user2);
    }
}

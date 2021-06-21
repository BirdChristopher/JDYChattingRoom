package com.jdy.Client.data.chat;

import com.jdy.Client.data.user.User;

/**
 * 私聊类.
 *
 * 存储一个私聊的消息记录和成员.
 *
 * @author dh
 */
public class PrivateChat extends Chat {
    public PrivateChat(User user1, User user2) {
        this.members.add(user1);
        this.members.add(user2);
    }
}

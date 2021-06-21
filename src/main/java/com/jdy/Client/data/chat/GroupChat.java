package com.jdy.Client.data.chat;

import com.jdy.Client.data.group.Group;

/**
 * 群聊类.
 *
 * 存储一个群聊的消息记录和成员.
 *
 * @author dh
 */
public class GroupChat extends Chat {
    public GroupChat(Group group) {
        this.members = group.getMembers();
    }
}

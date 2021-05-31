package com.jdy.Client.data.chat;

import com.jdy.Client.data.group.Group;

public class GroupChat extends Chat {
    public GroupChat(Group group) {
        this.members = group.getMembers();
    }
}

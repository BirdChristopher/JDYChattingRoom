package com.jdy.Client.data.group;

import com.jdy.Client.data.user.User;
import javafx.scene.image.Image;

import java.util.Vector;

public class Group {
    private String gid;
    private String name;
    private Image avatar;
    private Vector<User> members = new Vector<>();

    public Group(String gid, String name, Image avatar) {
        this.gid = gid;
        this.name = name;
        this.avatar = avatar;
    }

    public void addMember(User member) {
        members.add(member);
    }

    public String getGid() {
        return gid;
    }

    public String getName() {
        return name;
    }

    public Image getAvatar() {
        return avatar;
    }

    public Vector<User> getMembers() {
        return members;
    }
}

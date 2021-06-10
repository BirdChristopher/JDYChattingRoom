package com.jdy.Client.data.dataList;

import com.jdy.Client.data.group.Group;

import java.util.Vector;

public class GroupList {
    public static Vector<Group> groups = new Vector<>();

    public GroupList() {}

    public static Group getGroupById(String id) {
        Group group = null;
        for (Group g : groups) {
            if (g.getGid().equals(id)){
                group = g;
                break;
            }
        }
        return group;
    }
}

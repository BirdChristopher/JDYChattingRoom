package entity;

import java.util.Date;
public class GroupMessage {
    public int group_id;
    public String content;
    public int user_id;
    public String sender_name;
    public Date time;

    public GroupMessage(int group_id, String content, int user_id, String sender_name) {
        this.group_id = group_id;
        this.content = content;
        this.user_id = user_id;
        this.sender_name = sender_name;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSender_name() {
        return sender_name;
    }

    public void setSender_name(String sender_name) {
        this.sender_name = sender_name;
    }

    public Date getTime_stamp() {
        return time;
    }

    public void setTime_stamp(Date time_stamp) {
        this.time = time_stamp;
    }
}

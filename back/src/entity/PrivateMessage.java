package entity;

import java.util.Date;

public class PrivateMessage {
    public int user_from;
    public int user_to;
    public String content;
    public Date push_time;

    public PrivateMessage(int user_from, int user_to, String content) {
        this.user_from = user_from;
        this.user_to = user_to;
        this.content = content;
    }

    public int getUser_from() {
        return user_from;
    }

    public void setUser_from(int user_from) {
        this.user_from = user_from;
    }

    public int getUser_to() {
        return user_to;
    }

    public void setUser_to(int user_to) {
        this.user_to = user_to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPush_time() {
        return push_time;
    }

    public void setPush_time(Date push_time) {
        this.push_time = push_time;
    }
}

package entity;

import java.util.Date;

/**
 * 私聊消息实体类
 * <p>用于映射数据库中private_message表</p>
 * @author 季晓东
 */
public class PrivateMessage {
    /**
     * @value 发送者id
     */
    public int user_from;
    /**
     * @value 接受者id
     */
    public int user_to;
    /**
     * @value 内容
     */
    public String content;
    /**
     * @value 推送时间
     */
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

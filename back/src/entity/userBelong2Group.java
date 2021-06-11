package entity;

/**
 * 用户群聊从属关系实体类
 * <p>用于映射数据库中的userbelong2group表</p>
 * @author 季晓东
 */
public class userBelong2Group {
    /**
     * @value 用户id
     */
    public int user_id;
    /**
     * @value 所属群聊id
     */
    public int group_id;
    /**
     * @value 用户在群聊中的备注名
     */
    public String nickname;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}

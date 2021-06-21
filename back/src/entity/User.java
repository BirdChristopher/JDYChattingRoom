package entity;

import java.net.Socket;

/**
 * 用户信息实体类
 * <p>用于映射数据库中的userinfo表</p>
 * @author 季晓东
 */
public class User {
    /**
     * 用户名
     */
    public String username;
    /**
     * 密码
     */
    public String password;
    /**
     * 用户id
     */
    public int id;
    /**
     * 用户介绍
     */
    public String introduction;
    /**
     * 用户头像
     */
    public int avatar;
    /**
     * 用户性别
     */
    public String sex;
    //用于resultMap，一般不用
    public userBelong2Group belongInfo;

    public int belong_group_id;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public userBelong2Group getBelongInfo() {
        return belongInfo;
    }

    public void setBelongInfo(userBelong2Group belongInfo) {
        this.belongInfo = belongInfo;
    }

    public int getBelong_group_id() {
        return belong_group_id;
    }

    public void setBelong_group_id(int belong_group_id) {
        this.belong_group_id = belong_group_id;
    }

    //不含密码信息
    public String loginInfo(){
        return username+"&"+id+"&"+avatar+"&"+sex+"&"+introduction;
    }

    public String friendInfo(){
        return id+"&"+username+"&"+avatar;
    }

    public String memberInfo(){
        return id+"@@"+belongInfo.nickname+"@@"+avatar;
    }
}

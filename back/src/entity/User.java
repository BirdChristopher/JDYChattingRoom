package entity;

import java.net.Socket;

public class User {
    public String username;
    public String password;
    public int id;
    public String introduction;
    public int avatar;
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

package com.jdy.Client.data.user;

import javafx.scene.image.Image;

public class User {
    private String uid;
    private String name;
    private String sex;
    private Image avatar;

    public User() {
        this("000000", "Doctor", "男", new Image("/image/avatar01.jpg"));
    }

    public User(String uid, String name, String sex, Image avatar) {
        this.uid = uid;
        this.name = name;
        this.sex = sex;
        this.avatar = avatar;
    }

    private void changeName(String newName) {
        // TODO: 修改密码，包括同步数据库信息
        this.name = newName;
    }

    private boolean changePassword(String oldPassword, String newPassword) {
        // TODO: 检查旧密码是否正确以及新密码是否合法，同步数据库信息
        return true;
    }

    public String getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public Image getAvatar() {
        return avatar;
    }
}

package com.jdy.Client.data.user;

import javafx.scene.image.Image;

/**
 * 用户类.
 *
 * 用户的相关信息.
 *
 * @author dh
 */
public class User {
    protected String uid;
    protected String name;
    protected String sex;
    protected String signature;
    protected Image avatar;

    public User() {
        this("000000", "Doctor", "男", new Image("/image/avatar/0.jpg"));
    }

    public User(String uid, String name, Image avatar) {
        this(uid, name, "未知", avatar);
    }

    public User(String uid, String name, String sex, Image avatar) {
        this(uid, name, sex, "两面包夹芝士", avatar);
    }

    public User(String uid, String name, String sex, String signature, Image avatar) {
        this.uid = uid;
        this.name = name;
        this.sex = sex;
        this.signature = signature;
        this.avatar = avatar;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
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

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }
}

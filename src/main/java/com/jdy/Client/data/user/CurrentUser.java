package com.jdy.Client.data.user;

import javafx.scene.image.Image;

public class CurrentUser extends User{
    private String password;

    private static CurrentUser instance;

    private CurrentUser() { }

    public static CurrentUser getInstance() {
        if (instance == null)
            instance = new CurrentUser();
        return instance;
    }

    public CurrentUser(String uid, String name, String sex, int age, Image avatar, String password) {
        super(uid, name, sex, age, avatar);
        this.password = password;
    }
}

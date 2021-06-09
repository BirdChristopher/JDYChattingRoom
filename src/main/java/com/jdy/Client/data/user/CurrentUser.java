package com.jdy.Client.data.user;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class CurrentUser extends User{
    private String password;

    private static CurrentUser instance;

    private CurrentUser() { }

    public static CurrentUser getInstance() {
        if (instance == null)
            instance = new CurrentUser();
        return instance;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

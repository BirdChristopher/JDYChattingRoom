package com.jdy.Client.data.user;

public class CurrentUser extends User{
    private static CurrentUser instance;

    private CurrentUser() { }

    public static CurrentUser getInstance() {
        if (instance == null)
            instance = new CurrentUser();
        return instance;
    }
}

package com.jdy.Client.data.user;

/**
 * 当前用户类.
 *
 * 继承User，实现单例.
 *
 * @author dh
 */
public class CurrentUser extends User{
    private static CurrentUser instance;

    private CurrentUser() { }

    public static CurrentUser getInstance() {
        if (instance == null)
            instance = new CurrentUser();
        return instance;
    }
}

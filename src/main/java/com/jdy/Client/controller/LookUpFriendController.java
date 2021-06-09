package com.jdy.Client.controller;

import com.jdy.Client.component.window.LookUpWindow;

public class LookUpFriendController {
    private LookUpWindow window;

    public LookUpFriendController() {
        window = new LookUpWindow("查找好友");
    }

    public void showWindow() {
        window.show();
    }

    public void closeWindow() {
        window.close();
    }
}

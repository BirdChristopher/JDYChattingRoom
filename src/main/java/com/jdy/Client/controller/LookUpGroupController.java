package com.jdy.Client.controller;

import com.jdy.Client.component.window.LookUpWindow;

public class LookUpGroupController {
    private LookUpWindow window;

    public LookUpGroupController() {
        window = new LookUpWindow("查找群聊");
    }

    public void showWindow() {
        window.show();
    }

    public void closeWindow() {
        window.close();
    }
}

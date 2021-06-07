package com.jdy.Client.controller;

import com.jdy.Client.component.window.HomeWindow;

public class HomeController {
    private HomeWindow window;
    public HomeController() {
        window = new HomeWindow();
    }

    public void showWindow() {
        window.show();
    }
    public void closeWindow() {
        window.close();
    }
}

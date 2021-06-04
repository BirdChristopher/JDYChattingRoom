package com.jdy.Client.controller;

import com.jdy.Client.component.window.MyWindow;
import com.jdy.Client.component.window.RegisterWindow;

public class RegisterController {
    private MyWindow window;

    public RegisterController() {
        window = new MyWindow();
    }

    public void showWindow() {
        window.show();
    }
    public void closeWindow() {
        window.close();
    }
}

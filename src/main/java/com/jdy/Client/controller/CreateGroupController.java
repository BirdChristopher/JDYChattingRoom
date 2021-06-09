package com.jdy.Client.controller;

import com.jdy.Client.component.window.CreateGroupWindow;

public class CreateGroupController {
    private CreateGroupWindow window;

    public CreateGroupController() {
        window = new CreateGroupWindow();
    }

    public void showWindow() {
        window.show();
    }

    public void closeWindow() {
        window.close();
    }
}

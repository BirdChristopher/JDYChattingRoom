package com.jdy.Client.controller;

import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.window.HomeWindow;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.scene.image.Image;

public class HomeController {
    private HomeWindow window;
    private JFXListView<ListViewCell> friends;
    private Image image;

    public HomeController() {
        window = new HomeWindow();
        friends = window.getFriends();
        image = new Image("/image/avatar01.jpg");
    }

    public void receiveMessage(String text) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                friends.getItems().add(0, new ListViewCell(image, text));
            }
        });
    }

    public void showWindow() {
        window.show();
    }
    public void closeWindow() {
        window.close();
    }
}

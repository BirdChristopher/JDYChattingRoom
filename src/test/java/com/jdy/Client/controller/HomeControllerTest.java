package com.jdy.Client.controller;

import com.jdy.Client.data.user.CurrentUser;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class HomeControllerTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CurrentUser user = CurrentUser.getInstance();
        user.setName("test");
        user.setAvatar(new Image("/image/avatar/3.jpg"));
        HomeController controller = new HomeController();
        controller.showWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

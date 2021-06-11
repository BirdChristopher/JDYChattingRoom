package com.jdy.Client.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class LoginControllerTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginController controller = new LoginController();
        controller.showWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

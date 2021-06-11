package com.jdy.Client.controller;

import javafx.application.Application;
import javafx.stage.Stage;

public class RegisterControllerTest extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        RegisterController controller = ControllerFactory.getRegisterController();
        controller.showWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

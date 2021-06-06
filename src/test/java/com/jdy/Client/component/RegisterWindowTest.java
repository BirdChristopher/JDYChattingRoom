package com.jdy.Client.component;

import com.jdy.Client.controller.RegisterController;
import com.jdy.Client.data.ControllerFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class RegisterWindowTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        RegisterController controller = ControllerFactory.getRegisterController();
        controller.showWindow();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

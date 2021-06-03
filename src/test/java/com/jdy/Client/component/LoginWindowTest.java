package com.jdy.Client.component;

import com.jdy.Client.component.window.LoginWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class LoginWindowTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginWindow window = new LoginWindow();
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

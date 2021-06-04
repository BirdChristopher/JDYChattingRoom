package com.jdy.Client;

import com.jdy.Client.controller.LoginController;
import com.jdy.Client.data.ControllerFactory;
import com.jdy.Client.util.DataManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginController controller = ControllerFactory.getLoginController();
        controller.showWindow();
        DataManager.getInstance().connect();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

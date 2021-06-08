package com.jdy.Client;

import com.jdy.Client.controller.ChatController;
import com.jdy.Client.controller.HomeController;
import com.jdy.Client.controller.LoginController;
import com.jdy.Client.util.ControllerFactory;
import com.jdy.Client.util.DataManager;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Scanner;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ControllerFactory.getLoginController().showWindow();
        DataManager.getInstance().connect();
    }

    public static String load(String path) {
        return App.class.getResource(path).toExternalForm();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

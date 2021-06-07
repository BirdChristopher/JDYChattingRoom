package com.jdy.Client;

import com.jdy.Client.controller.LoginController;
import com.jdy.Client.util.ControllerFactory;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        //ControllerFactory.getHomeController().showWindow();
        ControllerFactory.getRegisterController().showWindow();
        //DataManager.getInstance().connect();
    }

    public static String load(String path) {
        return App.class.getResource(path).toExternalForm();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

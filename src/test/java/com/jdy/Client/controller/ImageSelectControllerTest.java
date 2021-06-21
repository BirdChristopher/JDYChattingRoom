package com.jdy.Client.controller;

import com.jdy.Client.component.window.ImageSelectWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class ImageSelectControllerTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ImageSelectController controller = ControllerFactory.getImageSelectedController();
       // controller.showWindow("test");
    }

    public static void main(String[] args) {
        launch(args);
    }
}

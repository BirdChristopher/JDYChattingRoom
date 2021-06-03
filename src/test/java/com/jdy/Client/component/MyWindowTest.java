package com.jdy.Client.component;

import com.jdy.Client.component.window.MyWindow;
import javafx.application.Application;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class MyWindowTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        MyWindow myWindow = new MyWindow();
        myWindow.getContainer().setCenter(new Label("hhhhhh"));
        myWindow.setTitle("MyWindowTest");
        myWindow.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

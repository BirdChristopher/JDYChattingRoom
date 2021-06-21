package com.jdy.Client.component;

import com.jdy.Client.component.window.ImageSelectWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class ImageSelectWindowTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ImageSelectWindow window = new ImageSelectWindow();
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

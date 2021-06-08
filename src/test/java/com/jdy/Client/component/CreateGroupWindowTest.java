package com.jdy.Client.component;

import com.jdy.Client.component.window.CreateGroupWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class CreateGroupWindowTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        CreateGroupWindow window = new CreateGroupWindow();
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

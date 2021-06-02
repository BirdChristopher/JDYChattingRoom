package com.jdy.Client.component;

import com.jdy.Client.component.frame.HomeFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomeFrameTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        HomeFrame homeFrame = new HomeFrame();
        Scene scene = new Scene(homeFrame, 300, 600);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

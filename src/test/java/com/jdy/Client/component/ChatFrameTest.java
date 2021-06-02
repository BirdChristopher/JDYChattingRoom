package com.jdy.Client.component;

import com.jdy.Client.component.frame.ChatFrame;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChatFrameTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        ChatFrame chatFrame = new ChatFrame();
        Scene scene = new Scene(chatFrame, 400, 400);
        scene.getStylesheets().add("CSS/SplitPane.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

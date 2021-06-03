package com.jdy.Client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Scene scene = new Scene(chatFrame, 400, 400);
        //scene.getStylesheets().add("CSS/SplitPane.css");

        VBox vBox = new VBox();
        Image image = new Image(getClass().getResource("/GIF/login_background.gif").toExternalForm());
        ImageView imageView = new ImageView(image);
        vBox.getChildren().add(imageView);
        Scene scene = new Scene(vBox);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

package com.jdy.Client;

import com.jfoenix.controls.JFXTextField;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class AppTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
/*        StackPane stackPane = new StackPane();
        JFXTextField textField = new JFXTextField();
        textField.setPrefHeight(30);
        textField.setPrefWidth(100);
        textField.setPromptText("account");
        textField.setId("Account");
        stackPane.getChildren().add(textField);
        stackPane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(stackPane, 400, 400);
        scene.getStylesheets().add("/CSS/login.css");
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();*/

        StackPane stackPane1 = new StackPane();
        ImageView imageView = new ImageView(new Image("/"));
        stackPane1.getChildren().add(imageView);
        Scene scene1 = new Scene(stackPane1);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

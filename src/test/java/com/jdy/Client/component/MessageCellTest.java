package com.jdy.Client.component;

import com.jdy.Client.component.base.MessageCell;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MessageCellTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox vBox = new VBox();
        MessageCell sent = new MessageCell(MessageCell.MessageType.SENT);
        sent.setAvatar(new Image("/image/avatar01.jpg"), 60, 60);
        sent.setMessage("xxxxxxxxxxxxxx");
        sent.setSender("hhhhhh");
        MessageCell receive = new MessageCell(MessageCell.MessageType.RECEIVED);
        receive.setAvatar(new Image("/image/avatar01.jpg"), 60, 60);
        receive.setMessage("yyyyyyyyyyy");
        sent.setSender("wwww");
        vBox.getChildren().addAll(sent, receive);

        Scene scene = new Scene(vBox, 500, 100);
        Stage stage = new Stage();
        stage.setTitle("MessageCellDemo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.jdy.Client.component;

import com.jdy.Client.component.base.MessageCell;
import com.jdy.Client.data.message.Message;
import com.jdy.Client.data.message.MessageType;
import com.jdy.Client.data.user.User;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Timestamp;
import java.util.Date;

public class MessageCellTest extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*VBox vBox = new VBox();
        //Message sentMessage = new Message("P01", new User(), "xxxxxxxxxxx000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000", Timestamp.valueOf("2021-06-07 21:31:28"), MessageType.SENT);
        //Message receivedMessage = new Message("P01", new User(), "h", Timestamp.valueOf("2021-06-07 21:32:45"), MessageType.RECEIVED);
        MessageCell sent = new MessageCell(sentMessage);
        MessageCell receive = new MessageCell(receivedMessage);
        vBox.getChildren().addAll(sent, receive);

        Scene scene = new Scene(vBox, 500, 100);
        Stage stage = new Stage();
        stage.setTitle("MessageCellDemo");
        stage.setScene(scene);
        stage.show();*/
    }

    public static void main(String[] args) {
        launch(args);
    }
}

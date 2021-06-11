package com.jdy.Client.component.base;

import com.jdy.Client.data.message.Message;
import com.jdy.Client.data.message.MessageType;
import com.jdy.Client.util.ImageUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MessageCell extends HBox {
    // View
    private Label messageText;
    private Label senderLabel;
    private StackPane avatarPane;
    private AnchorPane messagePane;

    private MessageType type;
    private Image avatar;
    private String name;
    private String message;

    public MessageCell(Message message) {
        this.type = message.getType();
        this.avatar = message.getSender().getAvatar();
        this.name = message.getSender().getName();
        this.message = message.getContent();
        this.messageText = new Label();
        this.senderLabel = new Label();
        this.avatarPane = new StackPane();
        this.messagePane = new AnchorPane();
        initialize();
    }

    private void initialize() {
        messageText.setText(message);
        messageText.setFont(Font.font(18));
        messageText.setMaxWidth(230);
        messageText.setWrapText(true);
        messageText.setPadding(new Insets(5, 8, 5, 8));
        senderLabel.setText(name);
        senderLabel.setFont(Font.font(15));
        senderLabel.setTextFill(Color.GRAY);
        messagePane.getChildren().add(messageText);
        messagePane.getChildren().add(senderLabel);
        avatarPane.getChildren().add(ImageUtil.circleImage(avatar, 20));
        avatarPane.setAlignment(Pos.TOP_CENTER);
        if (type == MessageType.SENT) {
            messageText.setLayoutY(8);
            messageText.setStyle("-fx-background-color: #9EEA6A; -fx-background-radius: 10px;");
            senderLabel.setVisible(false);
            this.getChildren().add(0, messagePane);
            this.getChildren().add(1, avatarPane);
            this.setAlignment(Pos.CENTER_RIGHT);
        }
        if (type == MessageType.RECEIVED) {
            messageText.setLayoutY(23);
            messageText.setStyle("-fx-background-color: #12B7F5; -fx-background-radius: 10px;");
            this.getChildren().add(0, ImageUtil.circleImage(avatar, 20));
            this.getChildren().add(1, messagePane);
            this.setAlignment(Pos.CENTER_LEFT);
        }
        this.setPadding(new Insets(5));
        this.setSpacing(8);

    }

    public MessageType getType() { return type; }

    public Image getAvatar() { return avatar; }

    public String getName() { return name; }

    public String getMessage() { return message; }
}
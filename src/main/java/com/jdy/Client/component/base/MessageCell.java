package com.jdy.Client.component.base;

import com.jdy.Client.data.message.Message;
import com.jdy.Client.util.DataManager;
import com.jfoenix.svg.SVGGlyph;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MessageCell extends HBox {
    // View
    private Label messageLabel;
    private ImageView avatarView;
    private Label senderLabel;
    private VBox vBox;
    // 聊天气泡
    private static final SVGGlyph bubbleReceived = new SVGGlyph(0,
            "CHATBUBBLERECEIVED",
            "M2190.222222 0a227.555556 227.555556 0 0 1 227.555556 227.555556v568.888888a227.555556 227.555556 0 0 1-227.555556 227.555556H369.777778a227.555556 227.555556 0 0 1-227.555556-227.555556l-0.028444-341.390222L0 170.666667h149.390222C174.648889 72.533333 263.736889 0 369.777778 0h1820.444444z",
            Color.web("#3399CC"));
    private static final SVGGlyph bubbleSent = new SVGGlyph(1,
            "CHATBUBBLESENT",
            "M2048 0c106.040889 0 195.128889 72.533333 220.387556 170.666667H2417.777778l-142.222222 284.444444v341.333333a227.555556 227.555556 0 0 1-227.555556 227.555556H227.555556a227.555556 227.555556 0 0 1-227.555556-227.555556V227.555556a227.555556 227.555556 0 0 1 227.555556-227.555556h1820.444444z",
            Color.web("#3399CC"));
    // Data
    private Message message;
    private MessageType type;

    public MessageCell(Message message, MessageType type) {
        this.message = message;
        this.type = type;
        initialize();
    }

    private void initialize() {
        Image image = new Image(DataManager.getAvatarById(message.getUid()));
        avatarView.setImage(image);
        messageLabel.setGraphic(bubbleSent);
        messageLabel.setText(message.getContent());
        if (type == MessageType.SENT) {
            vBox.getChildren().add(0, messageLabel);
        }
        if (type == MessageType.RECEIVED) {
            senderLabel.setText(message.getUid());
            vBox.getChildren().add(0, senderLabel);
            vBox.getChildren().add(1, messageLabel);
        }
        super.getChildren().add(0, vBox);
        super.getChildren().add(1, avatarView);
    }

}

enum MessageType{
    SENT, RECEIVED
}
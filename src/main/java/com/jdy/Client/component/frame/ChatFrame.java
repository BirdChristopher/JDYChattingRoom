package com.jdy.Client.component.frame;

import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.base.MessageCell;
import com.jdy.Client.data.chat.Chat;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;
import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ChatFrame extends SplitPane {
    // View
    private SplitPane left;
    private JFXScrollPane messageArea;
    private JFXTextArea textArea;
    private JFXButton sendButton;
    private VBox vBox;
    private JFXListView<ListViewCell> memberListView;
    private JFXListView<MessageCell> messageListView;

    public ChatFrame() {
        this.left = new SplitPane();
        this.messageArea = new JFXScrollPane();
        this.textArea = new JFXTextArea();
        this.sendButton = new JFXButton();
        this.vBox = new VBox();
        this.memberListView = new JFXListView<>();
        this.messageListView = new JFXListView<>();
        initialize();
    }
    public ChatFrame(Chat chat) {
        initialize();
    }

    private void initialize() {
        sendButton.setText("发送");
        sendButton.setMinHeight(30.0);
        sendButton.setMaxHeight(30.0);
        vBox.getChildren().add(0, textArea);
        vBox.getChildren().add(1, sendButton);
        VBox.setVgrow(textArea, Priority.ALWAYS);
        messageArea.getChildren().add(messageListView);
        left.setOrientation(Orientation.VERTICAL);
        left.getItems().add(0, messageArea);
        left.getItems().add(1, vBox);

        memberListView.setMinWidth(50);
        super.getItems().add(left);
        super.getItems().add(memberListView);
    }
}

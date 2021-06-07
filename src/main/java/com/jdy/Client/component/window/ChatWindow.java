package com.jdy.Client.component.window;

import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.base.MessageCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChatWindow extends Stage {
    private Scene scene;
    // View
    private VBox root;
    private SplitPane content;
    // 标题栏
    private GridPane topBar;
    private Label title;
    private JFXButton minButton;
    private JFXButton maxButton;
    private JFXButton closeButton;
    // 左侧聊天区域
    private SplitPane left;
    private JFXScrollPane messagePane;
    private JFXListView<MessageCell> messageListView;
    private VBox bottomPane;
    private JFXTextArea textArea;
    private JFXButton sendButton;
    // 右侧成员列表
    private JFXListView<ListViewCell> memberListView;
    // 一些资源
    private String name;

    public ChatWindow() {
        this.root = new VBox();
        this.content = new SplitPane();
        // 标题栏
        this.topBar = new GridPane();
        this.title = new Label();
        this.minButton = new JFXButton();
        this.maxButton = new JFXButton();
        //
    }
}

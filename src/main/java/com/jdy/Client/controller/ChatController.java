package com.jdy.Client.controller;

import com.jdy.Client.component.frame.ChatFrame;
import com.jdy.Client.component.base.MessageCell;
import com.jdy.Client.data.chat.Chat;
import com.jdy.Client.data.message.Message;
import com.jdy.Client.data.user.User;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * 聊天窗口的控制类<br>
 * 显示窗口，更新信息
 * @author dh
 */
public class ChatController implements Initializable {
    // 界面控件
    private ChatFrame chatFrame;
    private JFXListView<MessageCell> messageList;

    // 界面数据
    private ChatType chatType;
    private Chat chat;
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<User> members = new ArrayList<>();


    public ChatController (Chat chat, ChatType chatType) {
        this.chat = chat;
        this.chatType = chatType;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initData();
        initView();
    }

    private void initData() {
        // TODO: 从数据库获取聊天的成员和历史聊天记录
    }

    private void initView() {
        if (chatType == ChatType.SINGLE) {
            StackPane container = new StackPane(chatFrame);

        } else {
            //JFXListView<MemberCell> memberListView = new JFXListView<>();
            /*for (User u : members) {
                memberListView.getItems().add(new MemberCell(u));
            }*/
            JFXListView<Label> list = new JFXListView<>();
            for (int i = 0; i < 4; i++) {
                list.getItems().add(new Label("item" + i));
            }
            SplitPane container = new SplitPane();
            container.getItems().add(0, chatFrame);
            container.getItems().add(1, list);

        }
    }

    private void sendMessage(String text) {

    }
}

enum ChatType {
    SINGLE, GROUP
}

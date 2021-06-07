package com.jdy.Client.controller;

import com.jdy.Client.component.frame.ChatFrame;
import com.jdy.Client.component.base.MessageCell;
import com.jdy.Client.component.window.ChatWindow;
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
public class ChatController{
    public enum ChatType {
        SINGLE, GROUP
    }
    private ChatWindow window;
    private String id;
    private ChatType chatType;
    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<User> members = new ArrayList<>();


    public ChatController (String id, ChatType chatType) {
        this.id = id;
        this.chatType = chatType;
        this.window = new ChatWindow();
    }

    private void initData() {
        // TODO: 从数据库获取聊天的成员和历史聊天记录
    }

    private void initView() {

    }

    private void sendMessage(String text) {

    }

    public void showWindow() {
        window.show();
    }

    public void closeWindow() {
        window.close();
    }
}

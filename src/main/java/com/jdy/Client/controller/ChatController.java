package com.jdy.Client.controller;

import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.base.MessageCell;
import com.jdy.Client.component.window.ChatWindow;
import com.jdy.Client.data.dataList.FriendList;
import com.jdy.Client.data.dataList.GroupList;
import com.jdy.Client.data.dataList.MemberList;
import com.jdy.Client.data.dataList.MessageList;
import com.jdy.Client.data.group.Group;
import com.jdy.Client.data.message.Message;
import com.jdy.Client.data.message.MessageType;
import com.jdy.Client.data.user.CurrentUser;
import com.jdy.Client.data.user.User;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.IdUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Vector;

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

    private JFXButton sentButton;
    private JFXTextArea textArea;
    private JFXListView<MessageCell> messageListView;
    private JFXListView<ListViewCell> memberListView;

    public ChatController (String id, ChatType chatType) {
        this.id = id;
        this.chatType = chatType;
        this.window = new ChatWindow();
        this.sentButton = window.getSendButton();
        this.textArea = window.getTextArea();
        this.memberListView = window.getMemberListView();
        this.messageListView = window.getMessageListView();

        sentButton.setOnAction(event -> {
            String content = textArea.getText();
            Message message = new Message(this.id, CurrentUser.getInstance(), content, MessageType.SENT);
            MessageCell cell = new MessageCell(message);
            messageListView.getItems().add(cell);
            textArea.clear();
            String myId = IdUtil.C2S(CurrentUser.getInstance().getUid());
            if (chatType == ChatType.SINGLE)
                DataManager.getInstance().sent("P#" + myId + "#" + IdUtil.C2S(this.id) + "#" + content);
            else
                DataManager.getInstance().sent("G#" + IdUtil.C2S(this.id) + "#" + myId + content);
        });

        textArea.setOnKeyPressed(event -> {
            String content = textArea.getText();
            Message message = new Message(this.id, CurrentUser.getInstance(), content, MessageType.SENT);
            MessageCell cell = new MessageCell(message);
            messageListView.getItems().add(cell);
            textArea.clear();
            String myId = IdUtil.C2S(CurrentUser.getInstance().getUid());
            if (chatType == ChatType.SINGLE)
                DataManager.getInstance().sent("P#" + myId + "#" + IdUtil.C2S(this.id) + "#" + content);
            else
                DataManager.getInstance().sent("G#" + IdUtil.C2S(this.id) + "#" + myId + "#" + content);
        });
    }

    public void init() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ArrayList<Message> messages = MessageList.getList(id);
                ArrayList<User> members = MemberList.getList(id);
                for (User u : members) {
                    memberListView.getItems().add(new ListViewCell(u.getAvatar(), u.getName()));
                }
                for (Message m : messages) {
                    messageListView.getItems().add(new MessageCell(m));
                }
                if (chatType == ChatType.SINGLE)
                    window.getTitleLabel().setText(FriendList.getUserById(id).getName());
                else if (chatType == ChatType.GROUP)
                    window.getTitleLabel().setText(GroupList.getGroupById(id).getName());
                window.show();
                messages.clear();
                members.clear();
            }
        });
    }

    public void updateMessage(String cid, User user, String content) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messageListView.getItems().add(new MessageCell(new Message(cid, user, content, MessageType.RECEIVED)));
            }
        });
    }

    public void addMember(User user) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                memberListView.getItems().add(new ListViewCell(user.getAvatar(), user.getName()));
            }
        });
    }

    public void showWindow() {
        window.show();
    }

    public void closeWindow() {
        window.close();
    }
}

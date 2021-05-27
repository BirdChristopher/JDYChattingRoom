package com.jdy.Client.chat;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    private JFXButton sendButton;
    @FXML
    private JFXScrollPane scrollPane;
    @FXML
    private FlowPane flowPane;
    @FXML
    private JFXTextArea messageEditArea;
    @FXML
    private JFXListView<?> memberList;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}

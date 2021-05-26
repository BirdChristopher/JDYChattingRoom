package com.jdy.Client.login;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private VBox root;
    @FXML
    private Pane imagePane;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private ImageView avatar;
    @FXML
    private GridPane infoPane;
    @FXML
    private Label accountLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private TextField accountText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private GridPane buttonPane;
    @FXML
    private JFXButton registerButton;
    @FXML
    private JFXButton loginButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO: 读取上次登录缓存的信息，并显示到界面上，不做可删除
    }

    private String getId() {
        return accountText.getText();
    }

    private String getPassword() {
        return passwordText.getText();
    }

    private boolean checkFormatOfId() {
        return getId().matches("1[0-9]{5}");
    }

    private boolean checkId() {
        // TODO: 从数据库获取id是否存在
        return true;
    }

    private boolean checkPassword() {
        // TODO: 从数据库获取密码是否正确
        return true;
    }

    @FXML
    public void onClickLoginButton(ActionEvent event) {
        // TODO: 点击登录，暂时用Dialog，后面再画
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("登录成功");
        alert.show();
    }
    @FXML
    public void onClickRegisterButton(ActionEvent event) {
        // TODO: 点击注册
    }
}

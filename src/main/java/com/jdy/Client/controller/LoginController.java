package com.jdy.Client.controller;

import com.jdy.Client.component.window.LoginWindow;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.LoginStatus;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class LoginController{
    private LoginWindow loginWindow;
    private JFXButton loginButton;
    private Text registerText;

    public LoginController() {
        this.loginWindow = new LoginWindow();
        this.loginButton = loginWindow.getLoginButton();
        this.registerText = loginWindow.getRegister();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String account = loginWindow.getAccountField().getText();
                String password = loginWindow.getPasswordField().getText();
                DataManager.getInstance().sent("login#" + account + "#" + password);
                // TODO: 加载界面
                LoginStatus status;
                while ((status = DataManager.getInstance().getLoginStatus()) == LoginStatus.WAITING) {
                    System.out.println("[login]: waiting");
                }
                switch (status) {
                    case SUCCESS:
                        loginSuccess();
                        break;
                    case PASSWORD_ERROR:
                        loginFail("密码错误");
                        break;
                    case NONEXISTENT:
                        loginFail("用户不存在");
                        break;
                    case LOGGED_IN:
                        loginFail("用户已登录");
                        break;
                    default:
                        break;
                }
            }
        });

        registerText.setOnMouseClicked((MouseEvent event) -> {
            this.loginWindow.hide();
            ControllerFactory.getRegisterController().showWindow();
        });
    }

    private void loginFail(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(error);
        alert.showAndWait();
    }

/*    public void loginFail() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("失败");
                alert.showAndWait();
            }
        });
    }*/

    private void loginSuccess() {
        loginWindow.close();
        ControllerFactory.getHomeController().showWindow();
    }

    public void showWindow() { loginWindow.show(); }

    public void closeWindow() { loginWindow.close(); }
}

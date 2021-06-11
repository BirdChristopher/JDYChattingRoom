package com.jdy.Client.controller;

import com.jdy.Client.component.window.LoginWindow;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.DialogBuilder;
import com.jdy.Client.util.IdUtil;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
                if (account == null)
                    new DialogBuilder(loginButton).setTitle("提示").setMessage("账号不能为空").setNegativeBtn("确认").create();
                else if (password == null)
                    new DialogBuilder(loginButton).setTitle("提示").setMessage("请输入密码").setNegativeBtn("确认").create();
                else
                    DataManager.getInstance().sent("login#" + account + "#" + password);
            }
        });

        registerText.setOnMouseClicked((MouseEvent event) -> {
            this.loginWindow.hide();
            ControllerFactory.getRegisterController().showWindow();
        });
    }

    public void loginFail(int error) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                String str = "未知错误";
                switch (error) {
                    case 401:
                        str = "密码错误";
                        break;
                    case 402:
                        str = "用户不存在";
                        break;
                    case 403:
                        str = "用户已登录";
                        break;
                    default:
                        break;
                }
                new DialogBuilder(loginWindow.getLoginButton()).setTitle("登录失败").setMessage(str).setNegativeBtn("确认").create();
            }
        });
    }

    public void loginSuccess() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginWindow.close();
                ControllerFactory.getHomeController().showWindow();
            }
        });
    }

    public void showWindow() { loginWindow.show(); }

    public void closeWindow() { loginWindow.close(); }
}

package com.jdy.Client.controller;

import com.jdy.Client.component.window.LoginWindow;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.DialogBuilder;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * 登录窗口的控制类.
 *
 * 控制登录窗口的开关，一些组件的事件绑定，视图更新.
 *
 * @author dh
 */
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
                if (account.equals(""))
                    new DialogBuilder(loginButton).setTitle("提示").setMessage("账号不能为空").setNegativeBtn("确认").create();
                else if (password.equals(""))
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

    /**
     * 登录失败显示弹窗.
     * @param error 错误代码
     */
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
                    case 410:
                        str = "用户已登录";
                        break;
                    default:
                        break;
                }
                new DialogBuilder(loginWindow.getLoginButton()).setTitle("登录失败").setMessage(str).setNegativeBtn("确认").create();
            }
        });
    }

    /**
     * 登录成功，关闭窗口，打开主页.
     */
    public void loginSuccess() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                loginWindow.close();
                ControllerFactory.getHomeController().showWindow();
            }
        });
    }

    public LoginWindow getLoginWindow() { return loginWindow; }

    public void showWindow() { loginWindow.show(); }

    public void closeWindow() {
        loginWindow.close();
        Platform.exit();
        System.exit(0);
    }
}

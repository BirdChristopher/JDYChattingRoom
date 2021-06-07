package com.jdy.Client.controller;

import com.jdy.Client.component.window.LoginWindow;
import com.jdy.Client.util.ControllerFactory;
import com.jfoenix.controls.JFXButton;
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
               /* DataManager.getInstance().sent("login#" + account + "#" + password);
                // TODO: 加载界面
                int status;
                while ((status = DataManager.getInstance().getLoginStatus()) == -3) { }
                switch (status) {
                    case 1:
                        loginSuccess();
                        break;
                    case 0:
                        loginFail("密码错误");
                        break;
                    case -1:
                        loginFail("用户不存在");
                        break;
                    case -2:
                        loginFail("用户已登录");
                        break;
                    default:
                        break;
                }*/
            }
        });

        registerText.setOnMouseClicked((MouseEvent event) -> {
            ControllerFactory.getRegisterController().showWindow();
        });
    }

    private void loginFail(String error) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(error);
        alert.showAndWait();
    }

    private void loginSuccess() {
        loginWindow.close();
        RegisterController controller = ControllerFactory.getRegisterController();
        controller.showWindow();
    }

    public void showWindow() { loginWindow.show(); }

    public void closeWindow() { loginWindow.close(); }
}

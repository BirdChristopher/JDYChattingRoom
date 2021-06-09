package com.jdy.Client.controller;

import com.jdy.Client.component.window.RegisterWindow;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.DialogBuilder;
import com.jdy.Client.util.IdUtil;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

public class RegisterController {
    private RegisterWindow window;
    private Image avatar;
    private int avatarNum;
    private String name;
    private String password;
    private String sex;

    public RegisterController() {
        window = new RegisterWindow();
        avatar = window.getAvatar();
        avatarNum = 0;
        name = null;
        password = null;
        sex = "未知";

        window.getAvatarView().setOnMouseClicked(event -> {
            // 打开头像选择窗口
            ImageSelectController controller = ControllerFactory.getImageSelectedController();
            controller.showWindow("register");
        });

        window.getRegisterButton().setOnAction(event -> {
            name = window.getNameField().getText();
            password = window.getPasswordField().getText();
            sex = window.getSelectedSex();
            DataManager.getInstance().sent("register#" + avatarNum+ "#" + name + "#" + password + "#" + sex);
        });
    }

    public void success(String uid) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new DialogBuilder(window.getRegisterButton()).setTitle("注册成功").
                        setMessage("您的账号为 " + IdUtil.S2C(uid)).setNegativeBtn("确认").create();
                closeWindow();
                ControllerFactory.getLoginController().showWindow();
            }
        });
    }

    public void fail() {
        new DialogBuilder(window.getRegisterButton()).setTitle("注册失败").setNegativeBtn("确认").create();
    }

    public void updateAvatar(Image avatar, int avatarNum) {
        this.avatar = avatar;
        this.avatarNum = avatarNum;
        window.getAvatarView().setFill(new ImagePattern(avatar));
    }

    public void showWindow() {
        window.show();
    }
    public void closeWindow() {
        window.close();
    }
}

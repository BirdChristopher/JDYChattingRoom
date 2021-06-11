package com.jdy.Client.controller;

import com.jdy.Client.component.window.RegisterWindow;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.DialogBuilder;
import com.jdy.Client.util.IdUtil;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * 注册界面的控制类.
 *
 * 控制注册窗口的开关，一些组件的事件绑定，视图更新.
 *
 * @author dh
 */
public class RegisterController {
    private RegisterWindow window;
    private Image avatar;
    private int avatarNum;
    private String name;
    private String password;
    private String confirm;
    private String sex;
    private String signature;

    public RegisterController() {
        window = new RegisterWindow();
        avatar = window.getAvatar();
        avatarNum = 0;
        name = null;
        password = null;
        confirm = null;
        sex = "未知";
        signature = "两面包加芝士";

        window.getAvatarView().setOnMouseClicked(event -> {
            // 打开头像选择窗口
            ImageSelectController controller = ControllerFactory.getImageSelectedController();
            controller.showWindow("register");
        });

        window.getRegisterButton().setOnAction(event -> {
            name = window.getNameField().getText();
            password = window.getPasswordField().getText();
            confirm = window.getConfirmField().getText();
            sex = window.getSelectedSex();
            signature = window.getSignature().getText();
            if (name.equals(""))
                new DialogBuilder(window.getRegisterButton()).setTitle("提示").setMessage("用户名不能为空").setNegativeBtn("确认").create();
            else if (!name.matches("\\w+"))
                new DialogBuilder(window.getRegisterButton()).setTitle("提示").setMessage("用户名只能有数字和字母").setNegativeBtn("确认").create();
            else if (password.equals(""))
                new DialogBuilder(window.getRegisterButton()).setTitle("提示").setMessage("密码不能为空").setNegativeBtn("确认").create();
            else if (!password.equals(confirm))
                new DialogBuilder(window.getRegisterButton()).setTitle("提示").setMessage("密码不一致").setNegativeBtn("确认").create();
            else if (sex == null)
                new DialogBuilder(window.getRegisterButton()).setTitle("提示").setMessage("请选择您的性别").setNegativeBtn("确认").create();
            else if (signature.equals(""))
                new DialogBuilder(window.getRegisterButton()).setTitle("提示").setMessage("给自己写个酷酷的签名吧").setNegativeBtn("确认").create();
            else
                DataManager.getInstance().sent("register#" + name + "#" + password + "#" + sex + "#" + avatarNum + "#" + signature);
        });
    }

    /**
     * 注册成功提示弹窗，关闭窗口返回登录界面.
     * @param uid 账号id
     */
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

    /**
     * 注册失败，弹窗提示.
     */
    public void fail() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new DialogBuilder(window.getRegisterButton()).setTitle("注册失败").setMessage("用户名已存在").setNegativeBtn("确认").create();
            }
        });
    }

    /**
     * 更新用户选择的头像.
     * @param avatar 头像
     * @param avatarNum 头像编号
     */
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

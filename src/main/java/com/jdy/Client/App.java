package com.jdy.Client;

import com.jdy.Client.controller.ControllerFactory;
import com.jdy.Client.controller.LoginController;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.DialogBuilder;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * 程序主入口.
 *
 * 打开登录界面，连接数据库.
 *
 * @author dh
 */
public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        LoginController controller =  ControllerFactory.getLoginController();
        controller.showWindow();
        try {
            DataManager.getInstance().connect();
        } catch (IOException e) {
            new DialogBuilder(controller.getLoginWindow().getLoginButton()).setTitle("网络错误")
                    .setMessage("连接服务器失败").setNegativeBtn("确认").create();
        }
    }

    /**
     * 用于加载css文件
     * @param path 文件路径
     * @return 返回string表达
     */
    public static String load(String path) {
        return App.class.getResource(path).toExternalForm();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

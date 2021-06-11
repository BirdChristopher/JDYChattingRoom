package com.jdy.Client;

import com.jdy.Client.controller.ControllerFactory;
import com.jdy.Client.util.DataManager;
import javafx.application.Application;
import javafx.stage.Stage;

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
        ControllerFactory.getLoginController().showWindow();
        DataManager.getInstance().connect();
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

package com.jdy.Client.util;

import com.jdy.Client.controller.ChatController;
import com.jdy.Client.controller.HomeController;
import com.jdy.Client.controller.LoginController;
import com.jdy.Client.controller.RegisterController;
import com.jdy.Client.data.chat.Chat;

import java.util.HashMap;

public class ControllerFactory {
    private static HashMap<String, ChatController> chatControllerHashMap = new HashMap<>();
    private static LoginController loginController = null;
    private static RegisterController registerController = null;
    private static HomeController homeController = null;

    public static LoginController getLoginController() {
        if (loginController == null)
            loginController = new LoginController();
        return loginController;
    }
    public static RegisterController getRegisterController() {
        if (registerController == null)
            registerController = new RegisterController();
        return registerController;
    }
    public static HomeController getHomeController() {
        if (homeController == null)
            homeController = new HomeController();
        return homeController;
    }

    public static ChatController createChatController(String id, ChatController.ChatType type) {
        ChatController controller = new ChatController(id, type);
        chatControllerHashMap.put(id, controller);
        return controller;
    }

    public static ChatController getChatController(String id) {
        return chatControllerHashMap.get(id);
    }
}

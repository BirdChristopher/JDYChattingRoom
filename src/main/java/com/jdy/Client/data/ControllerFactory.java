package com.jdy.Client.data;

import com.jdy.Client.controller.ChatController;
import com.jdy.Client.controller.LoginController;
import com.jdy.Client.controller.RegisterController;

import java.util.HashMap;

public class ControllerFactory {
    private static HashMap<Integer, ChatController> chatControllerHashMap = null;
    private static LoginController loginController = null;
    private static RegisterController registerController = null;

    public static void creatRegisterController() {
        registerController = new RegisterController();
    }
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

}

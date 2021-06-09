package com.jdy.Client.controller;

import java.util.concurrent.ConcurrentHashMap;

public class ControllerFactory {
    private static ConcurrentHashMap<String, ChatController> chatControllerHashMap = new ConcurrentHashMap<>();
    private static LoginController loginController = null;
    private static RegisterController registerController = null;
    private static HomeController homeController = null;
    private static ImageSelectController imageSelectController = null;
    private static LookUpFriendController lookUpFriendController = null;
    private static LookUpGroupController lookUpGroupController = null;
    private static CreateGroupController createGroupController = null;

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

    public static ImageSelectController getImageSelectedController() {
        if (imageSelectController == null)
            imageSelectController = new ImageSelectController();
        return imageSelectController;
    }

    public static LookUpFriendController getLookUpFriendController() {
        if (lookUpFriendController == null)
            lookUpFriendController = new LookUpFriendController();
        return lookUpFriendController;
    }

    public static LookUpGroupController getLookUpGroupController() {
        if (lookUpGroupController == null)
            lookUpGroupController = new LookUpGroupController();
        return lookUpGroupController;
    }

    public static CreateGroupController getCreateGroupController() {
        if (createGroupController == null)
            createGroupController = new CreateGroupController();
        return createGroupController;
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

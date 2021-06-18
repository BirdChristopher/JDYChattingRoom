package com.jdy.Client.controller;

import java.util.concurrent.ConcurrentHashMap;

/**
 * controller类的工厂类.
 *
 * 管理所有controller类，提供获取controller的方法.
 *
 * @author dh
 */
public class ControllerFactory {
    private static ConcurrentHashMap<String, ChatController> chatControllerHashMap = new ConcurrentHashMap<>();
    private static LoginController loginController = null;
    private static RegisterController registerController = null;
    private static HomeController homeController = null;
    private static ImageSelectController imageSelectController = null;
    private static LookUpFriendController lookUpFriendController = null;
    private static LookUpGroupController lookUpGroupController = null;
    private static CreateGroupController createGroupController = null;

    /**
     * @return 登录界面的controller
     */
    public static LoginController getLoginController() {
        if (loginController == null)
            loginController = new LoginController();
        return loginController;
    }

    /**
     * @return 注册界面的controller
     */
    public static RegisterController getRegisterController() {
        if (registerController == null)
            registerController = new RegisterController();
        return registerController;
    }

    /**
     * @return 主页的controller
     */
    public static HomeController getHomeController() {
        if (homeController == null)
            homeController = new HomeController();
        return homeController;
    }

    /**
     * @return 选择头像界面的controller
     */
    public static ImageSelectController getImageSelectedController() {
        if (imageSelectController == null)
            imageSelectController = new ImageSelectController();
        return imageSelectController;
    }

    /**
     * @return 查找好友界面的controller
     */
    public static LookUpFriendController getLookUpFriendController() {
        if (lookUpFriendController == null)
            lookUpFriendController = new LookUpFriendController();
        return lookUpFriendController;
    }

    /**
     * @return 查找群聊界面的contoller
     */
    public static LookUpGroupController getLookUpGroupController() {
        if (lookUpGroupController == null)
            lookUpGroupController = new LookUpGroupController();
        return lookUpGroupController;
    }

    /**
     * @return 创建群聊的controller
     */
    public static CreateGroupController getCreateGroupController() {
        if (createGroupController == null)
            createGroupController = new CreateGroupController();
        return createGroupController;
    }

    /**
     * @param id 聊天的id
     * @return 聊天界面的controller
     */
    public static ChatController getChatController(String id) {
        if (chatControllerHashMap.get(id) == null) {
            ChatController.ChatType type = ChatController.ChatType.SINGLE;
            if (id.length() == 7)
                type = ChatController.ChatType.GROUP;
            ChatController controller = new ChatController(id, type);
            chatControllerHashMap.put(id, controller);
            return controller;
        }
        return chatControllerHashMap.get(id);
    }

    /**
     * 创建聊天界面的controller.
     * @param id 聊天id
     */
    public static void createChatController(String id) {
        if (chatControllerHashMap.get(id) == null) {
            ChatController.ChatType type = ChatController.ChatType.SINGLE;
            if (id.length() == 7)
                type = ChatController.ChatType.GROUP;
            ChatController controller = new ChatController(id, type);
            chatControllerHashMap.put(id, controller);
        }
    }
}

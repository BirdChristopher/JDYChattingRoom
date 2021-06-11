package com.jdy.Client.controller;

import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.window.HomeWindow;
import com.jdy.Client.data.dataList.FriendList;
import com.jdy.Client.data.dataList.GroupList;
import com.jdy.Client.data.group.Group;
import com.jdy.Client.data.user.CurrentUser;
import com.jdy.Client.data.user.User;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.IdUtil;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.ImagePattern;

/**
 * 主页窗口的控制类.
 *
 * 控制主页窗口的开关，一些组件的事件绑定，视图更新.
 *
 * @author dh
 */
public class HomeController {
    private HomeWindow window;
    private JFXListView<ListViewCell> friendsView;
    private JFXListView<ListViewCell> groupsView;

    private CurrentUser currentUser;

    public HomeController() {
        currentUser = CurrentUser.getInstance();
        window = new HomeWindow();
        friendsView = window.getFriends();
        groupsView = window.getGroups();
        // 加载数据
        initMyInfo();
        initFriends();
        initGroups();
        // 设置点击事件
        // 加好友
        window.getAddFriendButton().setOnAction(event -> {
            ControllerFactory.getLookUpFriendController().showWindow();
        });
        // 加群聊
        window.getAddGroupLabel().setOnMouseClicked(event -> {
            ControllerFactory.getLookUpGroupController().showWindow();
        });
        // 创建群聊
        window.getCreateGroupLabel().setOnMouseClicked(event -> {
            ControllerFactory.getCreateGroupController().showWindow();
        });
    }

    /**
     * 初始化个人信息.
     */
    private void initMyInfo() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                window.getAvatarView().setFill(new ImagePattern(currentUser.getAvatar()));
                window.getName().setText(currentUser.getName());
                window.getDescription().setText(currentUser.getSignature());
            }
        });
    }

    private void initFriends() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (User u : FriendList.friends) {
                    addFriendCell(u);
                }
            }
        });
    }

    private void initGroups() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                for (Group g : GroupList.groups) {
                    addGroupCell(g);
                }
            }
        });
    }

    public void addFriend(User user) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                addFriendCell(user);
            }
        });
    }

    public void addGroup(Group group) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                addGroupCell(group);
            }
        });
    }

    private void addFriendCell(User user) {
        ListViewCell cell = new ListViewCell(user.getAvatar(), user.getName() + " (" + user.getUid() + ")");
        friendsView.getItems().add(0, cell);
        // 打开聊天窗口，双击打开
        cell.setId(user.getUid()); // 绑定cell和聊天
        cell.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                DataManager.getInstance().sent("ph#" + IdUtil.C2S(cell.getId()));
                ControllerFactory.createChatController(cell.getId());
            }
        });
    }

    private void addGroupCell(Group group) {
        ListViewCell cell = new ListViewCell(group.getAvatar(), group.getName() + " (" + group.getGid() + ")");
        groupsView.getItems().add(0, cell);
        // 打开聊天窗口，双击打开
        cell.setId(group.getGid()); // 绑定cell和聊天
        cell.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                DataManager.getInstance().sent("gh#" + IdUtil.C2S(cell.getId()));
                ControllerFactory.createChatController(cell.getId());
            }
        });
    }

    public void showWindow() {
        window.show();
    }

    public void closeWindow() {
        window.close();
    }
}

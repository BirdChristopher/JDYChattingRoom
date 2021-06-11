package com.jdy.Client.controller;

import com.jdy.Client.component.base.UserCheckCell;
import com.jdy.Client.component.window.CreateGroupWindow;
import com.jdy.Client.data.dataList.FriendList;
import com.jdy.Client.data.user.CurrentUser;
import com.jdy.Client.data.user.User;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.DialogBuilder;
import com.jdy.Client.util.IdUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * 创建群聊窗口的控制类.
 *
 * 控制创建群聊窗口的开关，一些组件的事件绑定，视图更新.
 *
 * @author dh
 */
public class CreateGroupController {
    private CreateGroupWindow window;
    private JFXListView<UserCheckCell> friendsView;
    private JFXButton createButton;
    private JFXTextField nameField;
    private StringBuilder data;
    private Circle avatarView;
    private int avatarNum;

    public CreateGroupController() {
        window = new CreateGroupWindow();
        friendsView = window.getUserList();
        createButton = window.getCreateButton();
        avatarView = window.getAvatarView();
        nameField = window.getNameField();
        avatarNum = 0;
        data = new StringBuilder("cgroup#");

        for (User u : FriendList.friends) {
            UserCheckCell cell = new UserCheckCell(u);
            friendsView.getItems().add(0, cell);
            cell.setId(u.getUid()); // 绑定cell和好友
        }

        avatarView.setOnMouseClicked(event -> {
            ImageSelectController controller = ControllerFactory.getImageSelectedController();
            controller.showWindow("group");
        });

        createButton.setOnAction(event -> {
            data.append(nameField.getText()).append("#");
            data.append(avatarNum).append("#");
            for (UserCheckCell cell : friendsView.getItems()) {
                if (cell.getCheckBox().isSelected())
                    data.append(IdUtil.C2S(cell.getId())).append("#");
            }
            String myId = CurrentUser.getInstance().getUid();
            data.append(IdUtil.C2S(myId));
            DataManager.getInstance().sent(data.toString());
            new DialogBuilder(createButton).setTitle("创建群聊").setMessage("创建成功").setNegativeBtn("确认").create();
            window.close();
        });
    }

    /**
     * 更新头像.
     * @param avatar 头像图片类
     * @param avatarNum 头像的编号
     */
    public void updateAvatar(Image avatar, int avatarNum) {
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

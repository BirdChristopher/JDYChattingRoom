package com.jdy.Client.controller;

import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.base.UserCheckCell;
import com.jdy.Client.component.window.CreateGroupWindow;
import com.jdy.Client.data.dataList.FriendList;
import com.jdy.Client.data.user.CurrentUser;
import com.jdy.Client.data.user.User;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.IdUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

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
            // 打开聊天窗口，双击打开
            cell.setId(u.getUid()); // 绑定cell和聊天
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
        });
    }

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

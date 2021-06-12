package com.jdy.Client.controller;

import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.window.LookUpWindow;
import com.jdy.Client.data.dataList.FriendList;
import com.jdy.Client.data.dataList.MemberList;
import com.jdy.Client.data.user.CurrentUser;
import com.jdy.Client.data.user.User;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.DialogBuilder;
import com.jdy.Client.util.IdUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;

import java.util.ArrayList;

/**
 * 查找好友窗口的控制类.
 *
 * 控制查找好友窗口的开关，一些组件的事件绑定，视图更新.
 *
 * @author dh
 */
public class LookUpFriendController {
    private LookUpWindow window;
    private TextField searchField;
    private JFXButton searchButton;
    private JFXListView<ListViewCell> resultListView;

    public LookUpFriendController() {
        this.window = new LookUpWindow("查找好友");
        this.searchButton = window.getSearchButton();
        this.searchField = window.getSearchField();
        this.resultListView = window.getResultListView();

        searchButton.setOnAction(event -> {
            resultListView.getItems().clear();
            String input = searchField.getText();
            if (!input.matches("\\d{6}"))
                new DialogBuilder(searchButton).setTitle("提示").setMessage("用户账号格式错误").setNegativeBtn("确认").create();
            else {
                String id = IdUtil.C2S(searchField.getText());
                DataManager.getInstance().sent("sf#" + id);
            }
        });
    }

    /**
     * 更新显示查询结果.
     * @param user 查找到的用户，null表示用户不存在
     */
    public void setResult(User user) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (user == null)
                    new DialogBuilder(searchButton).setTitle("提示").setMessage("用户不存在").setNegativeBtn("确认").create();
                else {
                    ListViewCell cell = new ListViewCell(user.getAvatar(), user.getName() + " (" + user.getUid() + ")");
                    cell.setId(user.getUid());
                    cell.setMaxWidth(200);
                    resultListView.getItems().add(cell);
                    cell.setOnMouseClicked(event -> {
                        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                            DataManager.getInstance().sent("add#" + IdUtil.C2S(cell.getId()));
                            ControllerFactory.getHomeController().addFriend(user);
                            ControllerFactory.createChatController(cell.getId());
                            FriendList.friends.add(user);
                            new DialogBuilder(window.getSearchButton()).setTitle("添加好友")
                                    .setMessage("添加 " + user.getName() + " (" + user.getUid() + ") 成功!").setNegativeBtn("确认").create();
                        }
                    });
                }
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

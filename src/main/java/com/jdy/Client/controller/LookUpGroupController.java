package com.jdy.Client.controller;

import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.window.LookUpWindow;
import com.jdy.Client.data.dataList.GroupList;
import com.jdy.Client.data.group.Group;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.DialogBuilder;
import com.jdy.Client.util.IdUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.application.Platform;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;

/**
 * 查找群聊窗口的控制类.
 *
 * 控制查找群聊窗口的开关，一些组件的事件绑定，视图更新.
 *
 * @author dh
 */
public class LookUpGroupController {
    private Group foundGroup;
    private LookUpWindow window;
    private TextField searchField;
    private JFXButton searchButton;
    private JFXListView<ListViewCell> resultListView;

    public LookUpGroupController() {
        this.foundGroup = null;
        window = new LookUpWindow("查找群聊");
        this.searchButton = window.getSearchButton();
        this.searchField = window.getSearchField();
        this.resultListView = window.getResultListView();

        searchButton.setOnAction(event -> {
            resultListView.getItems().clear();
            String input = searchField.getText();
            if (!input.matches("G\\d{6}"))
                new DialogBuilder(searchButton).setTitle("提示").setMessage("群聊号格式错误").setNegativeBtn("确认").create();
            else {
                String id = IdUtil.C2S(searchField.getText());
                DataManager.getInstance().sent("sg#" + id);
            }
        });
    }

    public void showWindow() {
        window.show();
    }

    public void closeWindow() {
        window.close();
    }

    /**
     * 更新显示查询结果.
     * @param group 查找到的群聊，null表示不存在
     */
    public void setResult(Group group) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (group == null)
                    new DialogBuilder(searchButton).setTitle("提示").setMessage("群聊不存在").setNegativeBtn("确认").create();
                else {
                    ListViewCell cell = new ListViewCell(group.getAvatar(), group.getName() + " (" + group.getGid() + ")");
                    cell.setId(group.getGid());
                    cell.setMaxWidth(200);
                    resultListView.getItems().add(cell);
                    cell.setOnMouseClicked(event -> {
                        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                            DataManager.getInstance().sent("jgroup#" + IdUtil.C2S(cell.getId()));
                            cell.setDisable(false);
                        }
                    });
                }
            }
        });
    }

    public void joinSuccess() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ControllerFactory.getHomeController().addGroup(foundGroup);
                ControllerFactory.createChatController(foundGroup.getGid());
                GroupList.groups.add(foundGroup);
                new DialogBuilder(window.getSearchButton()).setTitle("加群")
                        .setMessage("加入 " + foundGroup.getName() + " (" + foundGroup.getGid() + ") 成功!").setNegativeBtn("确认").create();
            }
        });
    }

    public void joinFail() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                new DialogBuilder(window.getSearchButton()).setTitle("加群")
                        .setMessage("加入 " + foundGroup.getName() + " (" + foundGroup.getGid() + ") 失败.").setNegativeBtn("确认").create();
                resultListView.getItems().get(0).setDisable(true);
            }
        });
    }
}

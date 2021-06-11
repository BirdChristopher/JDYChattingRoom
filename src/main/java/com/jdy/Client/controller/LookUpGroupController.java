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

public class LookUpGroupController {
    private LookUpWindow window;
    private TextField searchField;
    private JFXButton searchButton;
    private JFXListView<ListViewCell> resultListView;

    public LookUpGroupController() {
        window = new LookUpWindow("查找群聊");
        this.searchButton = window.getSearchButton();
        this.searchField = window.getSearchField();
        this.resultListView = window.getResultListView();

        searchButton.setOnAction(event -> {
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

    public void setResult(Group group) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (group != null)
                    new DialogBuilder(searchButton).setTitle("提示").setMessage("群聊不存在").setNegativeBtn("确认").create();
                else {
                    ListViewCell cell = new ListViewCell(group.getAvatar(), group.getName() + " (" + group.getGid() + ")");
                    cell.setId(group.getGid());
                    cell.setMaxWidth(200);
                    resultListView.getItems().add(cell);
                    cell.setOnMouseClicked(event -> {
                        if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                            DataManager.getInstance().sent("jgroup#" + IdUtil.C2S(cell.getId()));
                            ControllerFactory.getHomeController().addGroup(group);
                            GroupList.groups.add(group);
                            new DialogBuilder(window.getSearchButton()).setTitle("加群")
                                    .setMessage("加入 " + group.getName() + " (" + group.getGid() + ") 成功!").setNegativeBtn("确认").create();
                        }
                    });
                }
            }
        });
    }
}

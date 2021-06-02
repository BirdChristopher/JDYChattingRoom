package com.jdy.Client.component.frame;

import com.jdy.Client.component.base.ListViewCell;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTabPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class HomeFrame extends VBox {
    // View
    private ImageView avatarView;
    private Image avatar;
    private Label name;
    private Label signature;
    private GridPane topRight;
    private GridPane top;
    private JFXTabPane tabPane;
    private Tab friendTab;
    private Tab groupTab;
    private JFXListView<ListViewCell> friends;
    private JFXListView<ListViewCell> groups;

    public HomeFrame() {
        this.avatarView = new ImageView();
        // this.avatar = new Image("/image");
        this.name = new Label();
        this.signature = new Label();
        this.topRight = new GridPane();
        this.top = new GridPane();
        this.tabPane = new JFXTabPane();
        this.friendTab = new Tab("好友");
        this.groupTab = new Tab("群聊");
        this.friends = new JFXListView<>();
        this.groups = new JFXListView<>();
        initialize();
    }

    private void initialize() {
        avatarView.setImage(avatar);
        topRight.add(name, 0, 0);
        topRight.add(signature, 0, 1);
        top.add(avatarView, 0, 0);
        top.add(topRight, 1, 0);
        friendTab.setContent(friends);
        groupTab.setContent(groups);
        tabPane.getTabs().add(friendTab);
        tabPane.getTabs().add(groupTab);
        this.getChildren().add(0, top);
        this.getChildren().add(tabPane);
    }
}

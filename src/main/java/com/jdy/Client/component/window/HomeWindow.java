package com.jdy.Client.component.window;

import com.jdy.Client.App;
import com.jdy.Client.component.base.ListViewCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.svg.SVGGlyph;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class HomeWindow extends Stage {
    private Scene scene;
    // View
    private VBox root;
    // 标题栏 + 头像
    private VBox top;
    private GridPane topBar;
    private Label title;
    private JFXButton addFriendButton;
    private JFXButton addGroupButton;
    private JFXButton minButton;
    private JFXButton closeButton;
    private JFXPopup addMenu;
    private JFXListView<Label> menuList;
    private Label addGroupLabel;
    private Label createGroupLabel;
    private HBox topBottom;
    private ImageView avatarView;
    private VBox rightPane;
    private Text name;
    private Text description;
    // 消息列表和好友列表
    private JFXTabPane tabPane;
    private Tab friendTab;
    private Tab groupTab;
    private StackPane friendPane;
    private StackPane groupPane;
    private JFXListView<ListViewCell> friends;
    private JFXListView<ListViewCell> groups;
    // 资源
    private Image avatar;
    private SVGGlyph icon;
    private SVGGlyph minus;
    private SVGGlyph close;
    private SVGGlyph addFriend;
    private SVGGlyph addGroup;
    private double xOffset;
    private double yOffset;

    public HomeWindow() {
        this.root = new VBox();
        this.top = new VBox();
        // 顶栏
        this.topBar = new GridPane();
        this.title = new Label();
        this.addFriendButton = new JFXButton();
        this.addGroupButton = new JFXButton();
        this.minButton = new JFXButton();
        this.closeButton = new JFXButton();
        this.addMenu = new JFXPopup();
        this.menuList = new JFXListView<>();
        this.addGroupLabel = new Label("加入群聊");
        this.createGroupLabel = new Label("创建群聊");
        this.topBottom = new HBox();
        this.avatarView = new ImageView();
        this.rightPane = new VBox();
        this.name = new Text();
        this.description = new Text();
        // 列表
        this.tabPane = new JFXTabPane();
        this.friendTab = new Tab();
        this.groupTab = new Tab();
        this.friendPane = new StackPane();
        this.groupPane = new StackPane();
        this.friends = new JFXListView<>();
        this.groups = new JFXListView<>();
        this.icon = new SVGGlyph("M512 960H64V512c0-247.04 200.96-448 448-448 246.976 0 448 200.96 448 448 " +
                "0 246.976-201.024 448-448 448z m-384-64h384c211.776 0 384-172.224 384-384s-172.224-384-384-384-384 172.224-384 384v384z " +
                "M448 640c-35.264 0-64-28.672-64-64V448c0-35.264 28.736-64 64-64s64 28.736 64 64v128c0 35.328-28.736 64-64 64z " +
                "m0-64v32V576zM640 640a64 64 0 0 1-64-64V448a64.064 64.064 0 0 1 128 0v128a64 64 0 0 1-64 64z m0-64v32V576z", Color.WHITE);
        this.minus = new SVGGlyph("M872 474H152c-4.4 0-8 3.6-8 8v60c0 4.4 3.6 8 8 8h720c4.4 " +
                "0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z", Color.WHITE);
        this.close = new SVGGlyph("M563.8 512l262.5-312.9c4.4-5.2 0.7-13.1-6.1-13.1h-79.8c-4.7 0-9.2 2.1-12.3 " +
                "5.7L511.6 449.8 295.1 191.7c-3-3.6-7.5-5.7-12.3-5.7H203c-6.8 0-10.5 7.9-6.1 13.1L459.4 512 196.9 " +
                "824.9c-4.4 5.2-0.7 13.1 6.1 13.1h79.8c4.7 0 9.2-2.1 12.3-5.7l216.5-258.1 216.5 258.1c3 3.6 7.5 5.7 " +
                "12.3 5.7h79.8c6.8 0 10.5-7.9 6.1-13.1L563.8 512z", Color.WHITE);
        this.addFriend = new SVGGlyph("M678.3 642.4c24.2-13 51.9-20.4 81.4-20.4h0.1c3 0 4.4-3.6 " +
                "2.2-5.6-30.8-27.6-65.6-49.7-103.7-65.8-0.4-0.2-0.8-0.3-1.2-0.5C719.2 505 759.6 431.7 759.6 " +
                "349c0-137-110.8-248-247.5-248S264.7 212 264.7 349c0 82.7 40.4 156 102.6 201.1-0.4 0.2-0.8 0.3-1.2 0.5-44.7 " +
                "18.9-84.8 46-119.3 80.6-34.5 34.5-61.5 74.7-80.4 119.5C147.9 794.5 138 841 137 888.8c-0.1 4.5 3.5 8.2 8 8.2h59.9c4.3 " +
                "0 7.9-3.5 8-7.8 2-77.2 32.9-149.5 87.6-204.3C357 628.2 432.2 597 512.2 597c56.7 0 111.1 15.7 158 45.1 2.5 1.5 5.5 1.7 8.1 " +
                "0.3zM512.2 521c-45.8 0-88.9-17.9-121.4-50.4-32.4-32.5-50.3-75.7-50.3-121.6 0-45.9 17.9-89.1 50.3-121.6S466.3 177 512.2 177s88.9 " +
                "17.9 121.4 50.4c32.4 32.5 50.3 75.7 50.3 121.6 0 45.9-17.9 89.1-50.3 121.6C601.1 503.1 558 521 512.2 521z" +
                "M880 759h-84v-84c0-4.4-3.6-8-8-8h-56c-4.4 0-8 3.6-8 8v84h-84c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h84v84c0 4.4 3.6 8 8 8h56c4.4 " +
                "0 8-3.6 8-8v-84h84c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8z", Color.WHITE);
        this.addGroup = new SVGGlyph("M892 772h-80v-80c0-4.4-3.6-8-8-8h-48c-4.4 0-8 3.6-8 8v80h-80c-4.4 0-8 3.6-8 8v48c0 4.4 3.6 8 8 " +
                "8h80v80c0 4.4 3.6 8 8 8h48c4.4 0 8-3.6 8-8v-80h80c4.4 0 8-3.6 8-8v-48c0-4.4-3.6-8-8-8z" +
                "M373.5 498.4c-0.9-8.7-1.4-17.5-1.4-26.4 0-15.9 1.5-31.4 4.3-46.5 0.7-3.6-1.2-7.3-4.5-8.8-13.6-6.1-26." +
                "1-14.5-36.9-25.1-25.8-25.2-39.7-59.3-38.7-95.4 0.9-32.1 13.8-62.6 36.3-85.6 24.7-25.3 57.9-39.1 93.2-38.7 " +
                "31.9 0.3 62.7 12.6 86 34.4 7.9 7.4 14.7 15.6 20.4 24.4 2 3.1 5.9 4.4 9.3 3.2 17.6-6.1 36.2-10.4 55.3-12.4 " +
                "5.6-0.6 8.8-6.6 6.3-11.6-32.5-64.3-98.9-108.7-175.7-109.9-110.8-1.7-203.2 89.2-203.2 200 0 62.8 28.9 118.8 74.2 " +
                "155.5-31.8 14.7-61.1 35-86.5 60.4-54.8 54.7-85.8 126.9-87.8 204-0.1 4.5 3.5 8.2 8 8.2h56.1c4.3 0 7.9-3.4 " +
                "8-7.7 1.9-58 25.4-112.3 66.7-153.5 29.4-29.4 65.4-49.8 104.7-59.7 3.8-1.1 6.4-4.8 5.9-8.8z" +
                "M824 472c0-109.4-87.9-198.3-196.9-200C516.3 270.3 424 361.2 424 472c0 62.8 29 118.8 74.2 155.5-31.7 " +
                "14.7-60.9 34.9-86.4 60.4C357 742.6 326 814.8 324 891.8c-0.1 4.5 3.5 8.2 8 8.2h56c4.3 0 7.9-3.4 8-7.7 "+
                "1.9-58 25.4-112.3 66.7-153.5C505.8 695.7 563 672 624 672c110.4 0 200-89.5 200-200z m-109.5 90.5C690.3 " +
                "586.7 658.2 600 624 600s-66.3-13.3-90.5-37.5C509 538 495.7 505.4 496 470.7c0.3-32.8 13.4-64.5 36.3-88 "+
                "24-24.6 56.1-38.3 90.4-38.7 33.9-0.3 66.8 12.9 91 36.6 24.8 24.3 38.4 56.8 38.4 91.4-0.1 34.2-13.4 66.3-37.6 90.5z", Color.WHITE);
        this.avatar = new Image("/image/avatar_default01.png");
        this.scene = new Scene(root, 400, 900);

        xOffset = 0.0;
        yOffset = 0.0;
        initialize();
    }

    private void initialize() {
        this.initStyle(StageStyle.TRANSPARENT);
        // 顶部
        // 标题栏
        icon.setSize(25, 25);
        title.setGraphic(icon);
        title.setPadding(new Insets(5, 10, 0, 10));
        minus.setSize(15, 2);
        minButton.setGraphic(minus);
        minButton.setPrefSize(35, 35);
        close.setSize(15, 15);
        closeButton.setGraphic(close);
        closeButton.setPrefSize(35, 35);
        addFriend.setSize(20, 20);
        addFriendButton.setGraphic(addFriend);
        addFriendButton.setPrefSize(35, 35);
        addGroup.setSize(23, 23);
        addGroupButton.setGraphic(addGroup);
        addGroupButton.setPrefSize(35, 35);
        menuList.getItems().addAll(addGroupLabel, createGroupLabel);
        menuList.setPrefWidth(100);
        addMenu.setPopupContent(menuList);
        topBar.add(title, 0, 0);
        topBar.add(addGroupButton, 1, 0);
        topBar.add(addFriendButton, 2, 0);
        topBar.add(minButton, 3, 0);
        topBar.add(closeButton, 4, 0);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(addFriendButton, VPos.TOP);
        GridPane.setValignment(minButton, VPos.TOP);
        GridPane.setValignment(closeButton, VPos.TOP);
        // 头像，昵称，简介
        avatarView.setImage(avatar);
        avatarView.setFitWidth(100);
        avatarView.setFitHeight(100);
        name.setText("昵称");
        name.setFont(Font.font("MicrosoftYaHei", FontWeight.BOLD, 22));
        name.setFill(Color.WHITE);
        description.setText("个性签名");
        description.setFont(Font.font("MicrosoftYaHei", 17));
        description.setFill(Color.WHITE);
        rightPane.getChildren().add(0, name);
        rightPane.getChildren().add(1, description);
        rightPane.setSpacing(10);
        rightPane.setPadding(new Insets(20, 20, 10, 30));
        topBottom.getChildren().add(0, avatarView);
        topBottom.getChildren().add(1, rightPane);
        topBottom.setPadding(new Insets(10, 10, 5, 30));
        top.getChildren().add(topBar);
        top.getChildren().add(topBottom);
        top.setStyle("-fx-background-image: url('/GIF/login_background.gif');");
        // 列表
        friends.setStyle("-fx-background-color: #abedd8;");
        groups.setStyle("-fx-background-color: #abedd8;");
        friends.setMaxWidth(400);
        groups.setMaxWidth(400);
        friendTab.setText("好友");
        friendTab.setStyle("-fx-background-color: #46cdcf;");
        friendPane.getChildren().add(friends);
        friendPane.setAlignment(Pos.TOP_LEFT);
        friendPane.setStyle("-fx-background-color: #abedd8;");
        friendTab.setContent(friendPane);
        groupTab.setText("群聊");
        groupTab.setStyle("-fx-background-color: #46cdcf;");
        groupPane.getChildren().add(groups);
        groupPane.setAlignment(Pos.TOP_LEFT);
        groupPane.setStyle("-fx-background-color: #abedd8;");
        groupTab.setContent(groupPane);
        tabPane.getTabs().add(friendTab);
        tabPane.getTabs().add(groupTab);
        tabPane.setMinWidth(410);
        tabPane.setTabMinWidth(200);
        tabPane.setTabMaxWidth(200);
        tabPane.setTabMinHeight(45);
        tabPane.setTabMaxHeight(45);

        VBox.setVgrow(tabPane, Priority.ALWAYS);
        //root.setPadding(new Insets(0,0,10,0));
        root.getChildren().add(top);
        root.getChildren().add(tabPane);
        scene.getStylesheets().add(App.load("/css/home-window.css"));
        this.setScene(scene);

        // 最小化
        minButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeWindow.super.setIconified(true);
            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                HomeWindow.super.close();
            }
        });
        // 窗口拖拽
        root.setOnMousePressed((MouseEvent event) -> {
            event.consume();
            xOffset = event.getSceneX();
            if (event.getSceneY() > 35) {
                yOffset = 0;
            } else {
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged((MouseEvent event) -> {
            event.consume();
            if (yOffset != 0) {
                super.setX(event.getScreenX() - xOffset);
                if (event.getScreenY() - yOffset < 0) {
                    super.setY(0);
                } else {
                    super.setY(event.getScreenY() - yOffset);
                }
            }
        });
        // 加入/创建群聊的弹出菜单
        addGroup.setOnMouseClicked(event -> {
            addMenu.show(addGroup, JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.RIGHT);
        });
    }

    public JFXListView<ListViewCell> getFriends() {
        return friends;
    }
}

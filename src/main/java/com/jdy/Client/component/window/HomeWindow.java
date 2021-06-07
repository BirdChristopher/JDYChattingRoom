package com.jdy.Client.component.window;

import com.jdy.Client.App;
import com.jdy.Client.component.base.ListViewCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
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
    private JFXButton addButton;
    private JFXButton minButton;
    private JFXButton closeButton;
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
    private SVGGlyph add;
    private double xOffset;
    private double yOffset;

    public HomeWindow() {
        this.root = new VBox();
        this.top = new VBox();
        // 顶栏
        this.topBar = new GridPane();
        this.title = new Label();
        this.addButton = new JFXButton();
        this.minButton = new JFXButton();
        this.closeButton = new JFXButton();
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
        this.add = new SVGGlyph("M678.3 642.4c24.2-13 51.9-20.4 81.4-20.4h0.1c3 0 4.4-3.6 " +
                "2.2-5.6-30.8-27.6-65.6-49.7-103.7-65.8-0.4-0.2-0.8-0.3-1.2-0.5C719.2 505 759.6 431.7 759.6 " +
                "349c0-137-110.8-248-247.5-248S264.7 212 264.7 349c0 82.7 40.4 156 102.6 201.1-0.4 0.2-0.8 0.3-1.2 0.5-44.7 " +
                "18.9-84.8 46-119.3 80.6-34.5 34.5-61.5 74.7-80.4 119.5C147.9 794.5 138 841 137 888.8c-0.1 4.5 3.5 8.2 8 8.2h59.9c4.3 " +
                "0 7.9-3.5 8-7.8 2-77.2 32.9-149.5 87.6-204.3C357 628.2 432.2 597 512.2 597c56.7 0 111.1 15.7 158 45.1 2.5 1.5 5.5 1.7 8.1 " +
                "0.3zM512.2 521c-45.8 0-88.9-17.9-121.4-50.4-32.4-32.5-50.3-75.7-50.3-121.6 0-45.9 17.9-89.1 50.3-121.6S466.3 177 512.2 177s88.9 " +
                "17.9 121.4 50.4c32.4 32.5 50.3 75.7 50.3 121.6 0 45.9-17.9 89.1-50.3 121.6C601.1 503.1 558 521 512.2 521z" +
                "M880 759h-84v-84c0-4.4-3.6-8-8-8h-56c-4.4 0-8 3.6-8 8v84h-84c-4.4 0-8 3.6-8 8v56c0 4.4 3.6 8 8 8h84v84c0 4.4 3.6 8 8 8h56c4.4 " +
                "0 8-3.6 8-8v-84h84c4.4 0 8-3.6 8-8v-56c0-4.4-3.6-8-8-8z", Color.WHITE);
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
        add.setSize(20, 20);
        addButton.setGraphic(add);
        addButton.setPrefSize(35, 35);
        topBar.add(title, 0, 0);
        topBar.add(addButton, 1, 0);
        topBar.add(minButton, 2, 0);
        topBar.add(closeButton, 3, 0);
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(addButton, VPos.TOP);
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
        friendTab.setContent(friendPane);
        groupTab.setText("群聊");
        groupTab.setStyle("-fx-background-color: #46cdcf;");
        groupPane.getChildren().add(groups);
        groupPane.setAlignment(Pos.TOP_LEFT);
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
    }
}

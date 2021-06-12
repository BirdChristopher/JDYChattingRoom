package com.jdy.Client.component.window;

import com.jdy.Client.App;
import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.base.SVGContent;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.ImageUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.svg.SVGGlyph;
import javafx.application.Platform;
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
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 主页窗口类.
 *
 * 主页的视图.
 *
 * @author dh
 */
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
    private Circle avatarView;
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
        this.avatarView = new Circle();
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
        this.icon = new SVGGlyph(SVGContent.ICON, Color.WHITE);
        this.minus = new SVGGlyph(SVGContent.MINUS, Color.WHITE);
        this.close = new SVGGlyph(SVGContent.CLOSE, Color.WHITE);
        this.addFriend = new SVGGlyph(SVGContent.ADD_FRIEND, Color.WHITE);
        this.addGroup = new SVGGlyph(SVGContent.ADD_GROUP, Color.WHITE);
        this.avatar = new Image("/image/avatar/0.jpg");
        this.scene = new Scene(root, 400, 900);

        xOffset = 0.0;
        yOffset = 0.0;
        initialize();
    }
    /**
     * 初始化样式，绑定事件.
     */
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
        avatarView = ImageUtil.circleImage(avatar, 50);
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
        scene.getStylesheets().add("/CSS/home-window.css");
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
                DataManager.getInstance().sent("logout");
                Platform.exit();
                System.exit(0);
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

    public Text getName() {
        return name;
    }

    public Text getDescription() {
        return description;
    }

    public JFXButton getAddFriendButton() {
        return addFriendButton;
    }

    public Label getAddGroupLabel() {
        return addGroupLabel;
    }

    public Label getCreateGroupLabel() {
        return createGroupLabel;
    }

    public JFXListView<ListViewCell> getFriends() {
        return friends;
    }

    public JFXListView<ListViewCell> getGroups() {
        return groups;
    }

    public Circle getAvatarView() {
        return avatarView;
    }
}

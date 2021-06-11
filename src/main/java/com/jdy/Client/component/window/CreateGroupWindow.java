package com.jdy.Client.component.window;

import com.jdy.Client.component.base.SVGContent;
import com.jdy.Client.component.base.UserCheckCell;
import com.jdy.Client.util.ImageUtil;
import com.jfoenix.controls.*;
import com.jfoenix.svg.SVGGlyph;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.TopLevelAttribute;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 创建群聊窗口类.
 *
 * 创建群聊的视图.
 *
 * @author dh
 */
public class CreateGroupWindow extends Stage {
    private Scene scene;
    // View
    private VBox root;
    // 标题栏
    private GridPane topBar;
    private Label title;
    private JFXButton minButton;
    private JFXButton closeButton;
    // 创建界面
    private StackPane infoPane;
    private AnchorPane infoView;
    private JFXTextField nameField;
    private JFXListView<UserCheckCell> userList;
    // 头像
    private Circle avatarView;
    // 创建按钮
    private JFXButton createButton;
    // 资源
    private Image avatar;
    private SVGGlyph minus;
    private SVGGlyph close;
    private double xOffset;
    private double yOffset;
    public CreateGroupWindow() {
        this.root = new VBox();
        // 标题栏
        this.topBar = new GridPane();
        this.title = new Label();
        this.minButton = new JFXButton();
        this.closeButton = new JFXButton();
        // 创建界面
        this.infoPane = new StackPane();
        this.infoView = new AnchorPane();
        this.nameField = new JFXTextField();
        this.userList = new JFXListView<>();
        this.avatarView = new Circle();
        this.createButton = new JFXButton();
        this.avatar = new Image("/image/avatar/0.jpg");
        this.minus = new SVGGlyph(SVGContent.MINUS, Color.WHITE);
        this.close = new SVGGlyph(SVGContent.CLOSE, Color.WHITE);
        this.scene = new Scene(root, 480, 800);

        xOffset = 0.0;
        yOffset = 0.0;
        initialize();
    }

    /**
     * 初始化样式，绑定事件.
     */
    private void initialize() {
        this.initStyle(StageStyle.TRANSPARENT);
        title.setText("创建群聊");
        title.setFont(Font.font("MicrosoftYaHei", 18));
        title.setTextFill(Color.WHITE);
        title.setPadding(new Insets(10));
        minus.setSize(15, 2);
        minButton.setGraphic(minus);
        minButton.setPrefSize(35, 35);
        close.setSize(15, 15);
        closeButton.setGraphic(close);
        closeButton.setPrefSize(35, 35);
        topBar.add(title, 0, 0);
        topBar.add(minButton, 1, 0);
        topBar.add(closeButton, 2, 0);
        topBar.setStyle("-fx-background-color: #ffb5a7;");
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(minButton, VPos.TOP);
        GridPane.setValignment(closeButton, VPos.TOP);
        // 头像、名字和选人
        avatarView = ImageUtil.circleImage(avatar, 50);
        avatarView.setLayoutY(60);
        avatarView.setLayoutX(200);

        nameField.setLayoutY(135);
        nameField.setLayoutX(130);
        nameField.setFont(Font.font("MicrosoftYaHei", 18));
        nameField.setPrefWidth(150);
        nameField.setPromptText("群聊名");
        nameField.setLabelFloat(true);
        nameField.setUnFocusColor(Color.GRAY);
        nameField.setFocusColor(Color.web("#0a81ab"));

        userList.setLayoutY(190);
        userList.setLayoutX(50);
        userList.setPrefSize(300, 400);
        userList.setMaxWidth(300);

        createButton.setLayoutY(600);
        createButton.setLayoutX(50);
        createButton.setText("确认创建");
        createButton.setFont(Font.font("MicrosoftYaHei", 20));
        createButton.setTextFill(Color.WHITE);
        createButton.setStyle("-fx-background-color: #17b978;");
        createButton.setPrefWidth(300);
        createButton.setPrefHeight(50);

        infoView.setPrefSize(300, 700);
        infoView.setStyle("-fx-background-color: #f8edeb;");
        infoView.setPadding(new Insets(8));
        infoView.getChildren().addAll(avatarView, nameField, userList, createButton);
        DropShadow dps1 = new DropShadow(); // 阴影
        dps1.setRadius(20.0);
        dps1.setColor(Color.GRAY);
        infoView.setEffect(dps1);

        infoPane.getChildren().add(infoView);
        infoPane.setStyle("-fx-background-color: #fcd5ce;");
        infoPane.setPadding(new Insets(30, 40, 50, 40));
        root.getChildren().add(0, topBar);
        root.getChildren().add(1, infoPane);
        this.setScene(scene);

        // 最小化
        minButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateGroupWindow.super.setIconified(true);
            }
        });
        // 关闭窗口
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                CreateGroupWindow.super.close();
            }
        });
        // 窗口拖拽
        root.setOnMousePressed((MouseEvent event) -> {
            event.consume();
            xOffset = event.getSceneX();
            if (event.getSceneY() > 50) {
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

    public JFXListView<UserCheckCell> getUserList() {
        return userList;
    }

    public JFXButton getCreateButton() {
        return createButton;
    }

    public JFXTextField getNameField() {
        return nameField;
    }

    public Circle getAvatarView() {
        return avatarView;
    }
}

package com.jdy.Client.component.window;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.svg.SVGGlyph;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegisterWindow extends Stage {
    private Scene scene;
    // View
    private StackPane root;
    // 背景
    private ImageView backgroundView;
    // 标题栏 + 信息框
    private VBox surfacePane;
    // 标题栏
    private GridPane topBar;
    private Label title;
    private JFXButton minButton;
    private JFXButton closeButton;
    // 信息框
    private StackPane infoPane;
    private AnchorPane infoView;
    private JFXTextField nameField;
    private JFXPasswordField passwordField;
    private JFXPasswordField confirmField;
    private ToggleGroup sexGroup;
    private JFXRadioButton maleButton;
    private JFXRadioButton femaleButton;
    // 头像
    private AnchorPane avatarPane;
    private ImageView avatarView;
    // 注册按钮
    private JFXButton registerButton;

    // 一些资源
    private Image background;
    private Image avatar;
    private SVGGlyph icon;
    private SVGGlyph minus;
    private SVGGlyph close;
    // 窗口偏移量
    private double xOffset;
    private double yOffset;

    public RegisterWindow() {
        this.root = new StackPane();
        // 背景
        this.backgroundView = new ImageView();
        // 标题栏
        this.surfacePane = new VBox();
        this.topBar = new GridPane();
        this.title = new Label();
        this.minButton = new JFXButton();
        this.closeButton = new JFXButton();
        // 信息框
        this.infoPane = new StackPane();
        this.infoView = new AnchorPane();
        this.nameField = new JFXTextField();
        this.passwordField = new JFXPasswordField();
        this.confirmField = new JFXPasswordField();
        this.sexGroup = new ToggleGroup();
        this.maleButton = new JFXRadioButton();
        this.femaleButton = new JFXRadioButton();
        // 注册按钮
        this.registerButton = new JFXButton();
        // 头像
        this.avatarPane = new AnchorPane();
        this.avatarView = new ImageView();
        // 一些资源
        this.icon = new SVGGlyph("M512 960H64V512c0-247.04 200.96-448 448-448 246.976 0 448 200.96 448 448 " +
                "0 246.976-201.024 448-448 448z m-384-64h384c211.776 0 384-172.224 384-384s-172.224-384-384-384-384 172.224-384 384v384z " +
                "M448 640c-35.264 0-64-28.672-64-64V448c0-35.264 28.736-64 64-64s64 28.736 64 64v128c0 35.328-28.736 64-64 64z " +
                "m0-64v32V576zM640 640a64 64 0 0 1-64-64V448a64.064 64.064 0 0 1 128 0v128a64 64 0 0 1-64 64z m0-64v32V576z");
        this.minus = new SVGGlyph("M872 474H152c-4.4 0-8 3.6-8 8v60c0 4.4 3.6 8 8 8h720c4.4 " +
                "0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z", Color.WHITE);
        this.close = new SVGGlyph("M563.8 512l262.5-312.9c4.4-5.2 0.7-13.1-6.1-13.1h-79.8c-4.7 0-9.2 2.1-12.3 " +
                "5.7L511.6 449.8 295.1 191.7c-3-3.6-7.5-5.7-12.3-5.7H203c-6.8 0-10.5 7.9-6.1 13.1L459.4 512 196.9 " +
                "824.9c-4.4 5.2-0.7 13.1 6.1 13.1h79.8c4.7 0 9.2-2.1 12.3-5.7l216.5-258.1 216.5 258.1c3 3.6 7.5 5.7 " +
                "12.3 5.7h79.8c6.8 0 10.5-7.9 6.1-13.1L563.8 512z", Color.WHITE);
        this.avatar = new Image("/image/avatar_default01.png");
        this.background = new Image("/image/register_2.png");
        this.scene = new Scene(root, 400, 600);

        xOffset = 0.0;
        yOffset = 0.0;

        initialize();
    }

    private void initialize() {
        // 隐藏系统样式
        this.initStyle(StageStyle.TRANSPARENT);
        // 背景图
        backgroundView.setImage(background);
        // 标题栏
        icon.setFill(Color.WHITE);
        icon.setSize(32, 32);
        title.setGraphic(icon);
        title.setText("eMessage");
        title.setFont(Font.font("MicrosoftYaHei", 20));
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
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(minButton, VPos.TOP);
        GridPane.setValignment(closeButton, VPos.TOP);
        // 信息框
        nameField.setLayoutY(80);
        nameField.setLayoutX(40);
        nameField.setFont(Font.font("MicrosoftYaHei", 18));
        nameField.setPrefWidth(200);
        nameField.setPromptText("用户名");
        nameField.setLabelFloat(true);
        nameField.requestFocus();

        passwordField.setLayoutY(150);
        passwordField.setLayoutX(40);
        passwordField.setFont(Font.font("MicrosoftYaHei", 18));
        passwordField.setPrefWidth(200);
        passwordField.setPromptText("请输入密码");
        passwordField.setLabelFloat(true);

        confirmField.setLayoutY(220);
        confirmField.setLayoutX(40);
        confirmField.setFont(Font.font("MicrosoftYaHei", 18));
        confirmField.setPrefWidth(200);
        confirmField.setPromptText("请确认密码");
        confirmField.setLabelFloat(true);

        maleButton.setToggleGroup(sexGroup);
        maleButton.setLayoutY(280);
        maleButton.setLayoutX(40);
        maleButton.setText("男");
        maleButton.setFont(Font.font("MicrosoftYaHei", 18));
        femaleButton.setToggleGroup(sexGroup);
        femaleButton.setLayoutY(280);
        femaleButton.setLayoutX(120);
        femaleButton.setText("女");
        femaleButton.setFont(Font.font("MicrosoftYaHei", 18));

        registerButton.setLayoutX(45);
        registerButton.setLayoutY(330);
        registerButton.setText("注册");
        registerButton.setPrefWidth(190);
        registerButton.setPrefHeight(50);
        registerButton.setFont(Font.font("SongTi", 18));
        registerButton.setTextFill(Color.WHITE );
        registerButton.setStyle("-fx-background-color: #17b978;");

        infoView.setPrefSize(280, 395);
        infoView.setStyle("-fx-background-color: white");
        infoView.getChildren().add(nameField);
        infoView.getChildren().add(passwordField);
        infoView.getChildren().add(confirmField);
        infoView.getChildren().add(maleButton);
        infoView.getChildren().add(femaleButton);
        infoView.getChildren().add(registerButton);

        infoPane.getChildren().add(infoView);
        infoPane.setPadding(new Insets(100, 60, 40, 60));
        // 添加标题栏和信息框
        surfacePane.getChildren().add(topBar);
        surfacePane.getChildren().add(infoPane);
        // 设置头像
        avatarView.setLayoutY(100);
        avatarView.setLayoutX(150);
        avatarView.setFitHeight(100);
        avatarView.setFitWidth(100);
        avatarView.setImage(avatar);
       /* DropShadow dps = new DropShadow();
        dps.setRadius(10.0);
        dps.setOffsetX(0.0);
        dps.setOffsetY(0.0);
        dps.setColor(Color.BLACK);
        avatarView.setEffect(dps);*/

        avatarPane.setPrefSize(400, 400);
        avatarPane.getChildren().add(avatarView);
        avatarPane.setManaged(false);

        // 背景模糊
        GaussianBlur gsb = new GaussianBlur();
        gsb.setRadius(10.0);
        backgroundView.setEffect(gsb);

        root.getChildren().add(backgroundView);
        root.getChildren().add(surfacePane);
        root.getChildren().add(avatarPane);
        this.setScene(scene);

        // 设置事件
        // 最小化
        minButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RegisterWindow.super.setIconified(true);
            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                RegisterWindow.super.close();
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
    public JFXButton getRegisterButton() {
        return registerButton;
    }

    public JFXTextField getNameField() {
        return nameField;
    }

    public JFXPasswordField passwordField() {
        return passwordField;
    }
}

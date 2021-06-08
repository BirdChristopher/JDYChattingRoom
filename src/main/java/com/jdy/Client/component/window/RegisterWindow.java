package com.jdy.Client.component.window;

import com.jdy.Client.component.base.SVGContent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.svg.SVGGlyph;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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
        this.icon = new SVGGlyph(SVGContent.ICON, Color.WHITE);
        this.minus = new SVGGlyph(SVGContent.MINUS, Color.WHITE);
        this.close = new SVGGlyph(SVGContent.CLOSE, Color.WHITE);
        this.avatar = new Image("/image/avatar_default01.png");
        this.background = new Image("/image/background/register_2.png");
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

package com.jdy.Client.component.window;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.svg.SVGGlyph;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sun.security.jca.GetInstance;

public class LoginWindow extends Stage {
    private Scene scene;
    // 根节点
    private StackPane root;
    // 最底层
    private VBox back;
    // 中间层上半
    private StackPane backgroundPane;
    private ImageView backgroundView;
    private GridPane topBar;
    private Label title;
    private JFXButton minButton;
    private JFXButton closeButton;
    // 中间层下半
    private AnchorPane bottom;
    private ImageView accountIcon;
    private ImageView passwordIcon;
    private JFXTextField accountField;
    private JFXPasswordField passwordField;
    private JFXButton loginButton;
    private Text register;
    // 顶层头像
    private AnchorPane avatarPane;
    private ImageView avatarView;

    // 默认图标和头像
    private SVGGlyph icon;
    private Image avatar;
    private Image background;
    private SVGGlyph minus;
    private SVGGlyph close;

    public LoginWindow() {
        this.root = new StackPane();
        this.back = new VBox();

        this.backgroundPane = new StackPane();
        this.backgroundView = new ImageView();

        this.topBar = new GridPane();
        this.title = new Label();
        this.minButton = new JFXButton();
        this.closeButton = new JFXButton();

        this.bottom = new AnchorPane();
        this.accountIcon = new ImageView();
        this.accountField = new JFXTextField();
        this.passwordIcon = new ImageView();
        this.loginButton = new JFXButton();
        this.register = new Text();

        this.avatarPane = new AnchorPane();
        this.avatarView = new ImageView();
        this.accountField = new JFXTextField();
        this.passwordField = new JFXPasswordField();
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
        this.scene = new Scene(root, 480, 400);
        this.background = new Image("/GIF/login_background.gif");
        initialize();
    }

    private void initialize() {
        // 隐藏系统样式
        this.initStyle(StageStyle.TRANSPARENT);
        // 标题栏
        icon.setFill(Color.WHITE);
        icon.setSize(32, 32);
        title.setGraphic(icon);
        title.setText("eMessage");
        title.setFont(Font.font("Microsoft YaHei", 20));
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
        topBar.setAlignment(Pos.TOP_CENTER); // 不知道为啥这个设置失效了
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(minButton, VPos.TOP);
        GridPane.setValignment(closeButton, VPos.TOP);
        // 上半动图背景
        backgroundView.setImage(background);
        backgroundPane.getChildren().add(0, backgroundView);
        backgroundPane.getChildren().add(1, topBar);
        // 下半信息
        accountIcon.setImage(new Image("/image/account_default.png"));
        passwordIcon.setImage(new Image("/image/lock_default.png"));
        bottom.getChildren().add(accountIcon);
        bottom.getChildren().add(accountField);
        bottom.getChildren().add(passwordIcon);
        bottom.getChildren().add(passwordField);
        bottom.getChildren().add(loginButton);
        bottom.getChildren().add(register);
        // 账号栏
        accountIcon.setLayoutY(75);
        accountIcon.setLayoutX(100);
        accountField.setLayoutY(60);
        accountField.setLayoutX(130);
        accountField.setPrefSize(240, 30);
        accountField.setFont(Font.font("MicrosoftYaHei", 20));
        accountField.setFocusColor(Color.web("3edbf0"));
        accountField.setPromptText("账号");
        // 密码栏
        passwordIcon.setLayoutY(120);
        passwordIcon.setLayoutX(100);
        passwordField.setLayoutY(105);
        passwordField.setLayoutX(130);
        passwordField.setPrefSize(240, 30);
        passwordField.setFont(Font.font("MicrosoftYaHei", 20));
        passwordField.setFocusColor(Color.web("3edbf0"));
        passwordField.setPromptText("密码");
        // 登录按钮
        loginButton.setLayoutX(130);
        loginButton.setLayoutY(165);
        loginButton.setPrefSize(240, 40);
        loginButton.setText("登录");
        loginButton.setFont(Font.font("SongTi", 20));
        loginButton.setTextFill(Color.WHITE);
        loginButton.setStyle("-fx-background-color: #3edbf0;");
        // 注册按钮
        register.setLayoutX(305);
        register.setLayoutY(225);
        register.setText("注册账号");
        register.setFont(Font.font("SongTi", 15));
        register.setFill(Color.web("bbbbbb"));
        //register.setId("registerText");
        // 组装
        back.getChildren().add(backgroundPane);
        back.getChildren().add(bottom);
        back.setAlignment(Pos.TOP_CENTER);

        avatarView.setLayoutX(176);
        avatarView.setLayoutY(86);
        avatarView.setFitWidth(128);
        avatarView.setFitHeight(128);
        avatarView.setImage(avatar);
        avatarPane.setPrefSize(480, 214);
        avatarPane.getChildren().add(avatarView);
        avatarPane.setManaged(false);

        root.getChildren().add(0, back);
        root.getChildren().add(1, avatarPane);
        root.setAlignment(Pos.BASELINE_CENTER);
        //scene.getStylesheets().add("/CSS/Register.css");
        this.setScene(scene);
    }
}

package com.jdy.Client.component.window;

import com.jdy.Client.component.base.SVGContent;
import com.jdy.Client.util.DataManager;
import com.jdy.Client.util.ImageUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.svg.SVGGlyph;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * 登录窗口类.
 *
 * 登录时的视图.
 *
 * @author dh
 */
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
    private Circle avatarView;
    // 默认图标和头像
    private SVGGlyph icon;
    private Image avatar;
    private Image background;
    private SVGGlyph minus;
    private SVGGlyph close;
    // 窗口偏移量
    private double xOffset;
    private double yOffset;

    public LoginWindow() {
        this.root = new StackPane();
        this.back = new VBox();
        // 背景
        this.backgroundPane = new StackPane();
        this.backgroundView = new ImageView();
        // 标题栏
        this.topBar = new GridPane();
        this.title = new Label();
        this.minButton = new JFXButton();
        this.closeButton = new JFXButton();
        // 下半界面
        this.bottom = new AnchorPane();
        this.accountIcon = new ImageView();
        this.accountField = new JFXTextField();
        this.passwordIcon = new ImageView();
        this.loginButton = new JFXButton();
        this.register = new Text();
        // 头像
        this.avatarPane = new AnchorPane();
        this.avatarView = new Circle();
        this.accountField = new JFXTextField();
        this.passwordField = new JFXPasswordField();
        this.icon = new SVGGlyph(SVGContent.ICON);
        this.minus = new SVGGlyph(SVGContent.MINUS, Color.WHITE);
        this.close = new SVGGlyph(SVGContent.CLOSE, Color.WHITE);
        this.avatar = new Image("/image/avatar/0.jpg");
        this.scene = new Scene(root, 480, 400);
        this.background = new Image("/GIF/login_background.gif");

        xOffset = 0.0;
        yOffset = 0.0;
        initialize();
    }

    /**
     * 初始化样式，绑定事件.
     */
    private void initialize() {
        // 隐藏系统样式
        this.initStyle(StageStyle.TRANSPARENT);
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
        topBar.setAlignment(Pos.TOP_CENTER); // 不知道为啥这个设置失效了
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setValignment(minButton, VPos.TOP);
        GridPane.setValignment(closeButton, VPos.TOP);
        // 上半动图背景
        backgroundView.setImage(background);
        backgroundPane.getChildren().add(0, backgroundView);
        backgroundPane.getChildren().add(1, topBar);
        // 下半信息
        accountIcon.setImage(new Image("/image/icon/account_default.png"));
        passwordIcon.setImage(new Image("/image/icon/lock_default.png"));
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
        // 头像
        DropShadow dps1 = new DropShadow(); // 阴影
        dps1.setRadius(20.0);
        dps1.setColor(Color.BLACK);
        avatarView = ImageUtil.circleImage(avatar, 60);
        avatarView.setEffect(dps1);
        avatarPane.setPrefSize(480, 214);
        avatarPane.getChildren().add(avatarView);
        avatarPane.setManaged(false);
        avatarView.setLayoutX(240);
        avatarView.setLayoutY(140);

        root.getChildren().add(0, back);
        root.getChildren().add(1, avatarPane);
        root.setAlignment(Pos.BASELINE_CENTER);
        //scene.getStylesheets().add("/CSS/Register.css");
        this.setScene(scene);
        this.setTitle("eMessage");

        // 设置事件
        // 最小化
        minButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginWindow.super.setIconified(true);
            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginWindow.super.close();
                DataManager.getInstance().sent("logout");
                Platform.exit();
                System.exit(0);
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
    public JFXButton getLoginButton() {
        return loginButton;
    }

    public Text getRegister() {
        return register;
    }

    public JFXTextField getAccountField() {
        return accountField;
    }

    public JFXPasswordField getPasswordField() {
        return passwordField;
    }
}

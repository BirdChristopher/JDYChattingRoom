package com.jdy.Client.component.window;

import com.jdy.Client.component.base.ListViewCell;
import com.jdy.Client.component.base.MessageCell;
import com.jdy.Client.component.base.SVGContent;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.svg.SVGGlyph;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChatWindow extends Stage {
    private Scene scene;
    // View
    private StackPane root;
    private VBox contentPane;
    private SplitPane content;
    // 标题栏
    private StackPane topPane;
    private GridPane topBar;
    private Label title;
    private JFXButton minButton;
    private JFXButton maxButton;
    private JFXButton closeButton;
    // 左侧聊天区域
    private SplitPane messagePane;
    private JFXListView<MessageCell> messageListView;
    private VBox leftPane;
    private JFXTextArea textArea;
    private StackPane buttonPane;
    private JFXButton sendButton;
    // 右侧成员列表
    private JFXListView<ListViewCell> memberListView;
    // 一些资源
    private String name;
    private SVGGlyph minus;
    private SVGGlyph max;
    private SVGGlyph close;
    // 偏移量
    private double xOffset;
    private double yOffset;
    private double x;
    private double y;
    private double width;
    private double height;
    private boolean isMax;
    private boolean isRight;
    private boolean isBottomRight;
    private boolean isBottom;
    private double RESIZE_WIDTH;
    private double MIN_WIDTH;
    private double MIN_HEIGHT;

    public ChatWindow() {
        this.root = new StackPane();
        this.contentPane = new VBox();
        this.content = new SplitPane();
        // 标题栏
        topPane = new StackPane();
        this.topBar = new GridPane();
        this.title = new Label();
        this.minButton = new JFXButton();
        this.maxButton = new JFXButton();
        this.closeButton = new JFXButton();
        // 左侧聊天区域
        this.messagePane = new SplitPane();
        this.messageListView = new JFXListView<>();
        this.leftPane = new VBox();
        this.textArea = new JFXTextArea();
        this.buttonPane = new StackPane();
        this.sendButton = new JFXButton();
        // 右侧聊天成员列表
        this.memberListView = new JFXListView<>();

        this.name = "标题";
        this.minus = new SVGGlyph(SVGContent.MINUS, Color.WHITE);
        this.close = new SVGGlyph(SVGContent.CLOSE, Color.WHITE);
        this.max = new SVGGlyph(SVGContent.MAX, Color.WHITE);
        this.scene = new Scene(root, 850, 600);

        xOffset = 0.0;
        yOffset = 0.0;
        x = 0.0;
        y = 0.0;
        width = 0.0;
        height = 0.0;
        isMax = false;
        RESIZE_WIDTH = 5.0;
        MIN_HEIGHT = 500;
        MIN_WIDTH = 600;
        initialize();
    }

    private void initialize() {
        this.initStyle(StageStyle.TRANSPARENT);
        // 标题栏
        title.setText(name);
        title.setPadding(new Insets(10));
        title.setFont(Font.font("MicrosoftYaHei", 22));
        title.setTextFill(Color.WHITE);
        title.setAlignment(Pos.TOP_CENTER);
        minus.setSize(15, 2);
        minButton.setGraphic(minus);
        minButton.setPrefSize(35, 35);
        close.setSize(15, 15);
        closeButton.setGraphic(close);
        closeButton.setPrefSize(35, 35);
        max.setSize(15, 15);
        maxButton.setGraphic(max);
        maxButton.setPrefSize(35, 35);
        topBar.add(title, 0, 0);
        topBar.add(minButton, 1, 0);
        topBar.add(maxButton, 2, 0);
        topBar.add(closeButton, 3, 0);
        topPane.getChildren().add(topBar);
        topPane.setStyle("-fx-background-image: url('/image/background/chat_title.png');");
        GridPane.setHgrow(title, Priority.ALWAYS);
        GridPane.setHalignment(title, HPos.CENTER);
        GridPane.setValignment(minButton, VPos.TOP);
        GridPane.setValignment(maxButton, VPos.TOP);
        GridPane.setValignment(closeButton, VPos.TOP);
        // 左侧聊天区域
        messageListView.setStyle("-fx-background-color: #a4ebf3;");
        messageListView.setMinHeight(200);
        messageListView.setPadding(new Insets(8));
        textArea.setFont(Font.font(18));
        textArea.setPadding(new Insets(8));
        messagePane.getItems().add(messageListView);
        messagePane.getItems().add(textArea);
        messagePane.setOrientation(Orientation.VERTICAL);
        messagePane.setPrefSize(600, 500);
        messagePane.setDividerPosition(0, 0.7);
        sendButton.setText("发送");
        sendButton.setPrefSize(90,40);
        sendButton.setFont(Font.font(16));
        sendButton.setTextFill(Color.WHITE);
        sendButton.setStyle("-fx-background-color: #019CFE;");
        buttonPane.getChildren().add(sendButton);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        buttonPane.setPadding(new Insets(10));
        leftPane.getChildren().add(messagePane);
        leftPane.getChildren().add(buttonPane);
        leftPane.setPrefSize(600, 560);
        leftPane.setMinWidth(600);
        // 右侧成员列表
        memberListView.setMinWidth(150);
        memberListView.setMinWidth(180);
        memberListView.setStyle("-fx-background-color: #a4ebf3;");
        content.getItems().add(leftPane);
        content.getItems().add(memberListView);
        content.setDividerPosition(0, 0.7);
        content.setStyle("-fx-background-color: #a4ebf3;");
        contentPane.getChildren().add(topPane);
        contentPane.getChildren().add(content);
        root.getChildren().add(contentPane);
        scene.getStylesheets().add("/CSS/chat-window.css");
        this.setScene(scene);

        // 最小化
        minButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                ChatWindow.super.setIconified(true);
            }
        });
        // TODO: 窗口最大化和拖拽改大小有bug
        // 最大化及还原
        maxButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
                isMax = !isMax;
                if (isMax) {
                    // 最大化
                    ChatWindow.super.setX(rectangle2D.getMinX());
                    ChatWindow.super.setY(rectangle2D.getMinY());
                    ChatWindow.super.setWidth(rectangle2D.getWidth());
                    ChatWindow.super.setHeight(rectangle2D.getHeight());
                } else {
                    // 还原
                    ChatWindow.super.setX(x);
                    ChatWindow.super.setY(y);
                    ChatWindow.super.setWidth(width);
                    ChatWindow.super.setHeight(height);
                }
            }
        });

        // 关闭
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                close();
            }
        });

        // 监听大小
        this.xProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && !isMax) {
                    x = newValue.doubleValue();
                }
            }
        });
        this.yProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && !isMax) {
                    y = newValue.doubleValue();
                }
            }
        });
        this.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && !isMax) {
                    width = newValue.doubleValue();
                }
            }
        });
        this.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue != null && !isMax) {
                    height = newValue.doubleValue();
                }
            }
        });

        // 监听鼠标位置，改变鼠标光标
        root.setOnMouseMoved((MouseEvent event) -> {
            event.consume();
            double x = event.getSceneX();
            double y = event.getSceneY();
            double width = super.getWidth();
            double height = super.getHeight();
            Cursor cursorType = Cursor.DEFAULT; // 光标默认类型
            isRight = isBottomRight = isBottom = false;
            if (y >= height - RESIZE_WIDTH) {
                if (x <= RESIZE_WIDTH) {
                } else if (x >= width - RESIZE_WIDTH) {
                    isBottomRight = true;
                    cursorType = Cursor.SE_RESIZE;
                } else {
                    isBottom = true;
                    cursorType = Cursor.S_RESIZE;
                }
            } else if (x >= width - RESIZE_WIDTH) {
                isRight = true;
                cursorType = Cursor.E_RESIZE;
            }
            root.setCursor(cursorType);
        });
        // 监听鼠标拖拽
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
            double x = event.getSceneX();
            double y = event.getSceneY();
            // 保存窗口改变后的x, y及宽高
            double nextX = super.getX();
            double nextY = super.getY();
            double nextWidth = super.getWidth();
            double nextHeight = super.getHeight();
            // 调整窗口
            if (isRight || isBottomRight) {
                nextWidth = x;
            }
            if (isBottomRight || isBottom) {
                nextHeight = y;
            }
            // 判断宽高是否超过最小宽高
            if (nextWidth <= MIN_WIDTH) {
                nextWidth = MIN_WIDTH;
            }
            if (nextHeight <= MIN_HEIGHT) {
                nextHeight = MIN_HEIGHT;
            }
            // 统一调整窗口，避免多次刷新
            super.setX(nextX);
            super.setY(nextY);
            super.setWidth(nextWidth);
            super.setHeight(nextHeight);
        });

        // 鼠标点击获取横纵坐标
        root.setOnMousePressed(event -> {
            event.consume();
            xOffset = event.getSceneX();
            if (event.getSceneY() > 50) {
                yOffset = 0;
            } else {
                yOffset = event.getSceneY();
            }
        });
    }
}

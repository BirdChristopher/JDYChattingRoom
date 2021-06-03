package com.jdy.Client.component.window;

import com.jfoenix.controls.JFXButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MyWindow extends Stage {
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
    private double xOffset;
    private double yOffset;
    private JFXButton btnMin;
    private JFXButton btnMax;
    private JFXButton btnClose;
    private Label leftTitle;
    private Label centerTitle;
    private GridPane topBar;
    private BorderPane container;
    private Scene scene;

    public MyWindow() {
        this.x = 0.0;
        this.y = 0.0;
        this.width = 0.0;
        this.height = 0.0;
        this.isMax = false;
        this.RESIZE_WIDTH = 5.0;
        this.MIN_WIDTH = 400.0;
        this.MIN_HEIGHT = 300.0;
        this.xOffset = 0.0;
        this.yOffset = 0.0;
        this.btnMin = new JFXButton("min");
        this.btnMax = new JFXButton("max");
        this.btnClose = new JFXButton("close");
        this.leftTitle = new Label();
        this.centerTitle = new Label();
        this.topBar = new GridPane();
        this.container = new BorderPane();
        this.scene = new Scene(container, 400, 300);
        initialize();
    }

    private void initialize() {
        this.initStyle(StageStyle.TRANSPARENT);
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setPadding(new Insets(10));
        topBar.add(leftTitle, 0, 0);
        topBar.add(btnMin, 1, 0);
        topBar.add(btnMax, 2, 0);
        topBar.add(btnClose, 3, 0);
        GridPane.setHgrow(leftTitle, Priority.ALWAYS);
        container.setTop(topBar);
        topBar.setStyle("-fx-background-color: rgb(58.0,154.0,242.0);");

        // 事件
        // 最小化
        btnMin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyWindow.super.setIconified(true);
            }
        });

        // 最大化及还原
        btnMax.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Rectangle2D rectangle2D = Screen.getPrimary().getVisualBounds();
                isMax = !isMax;
                if (isMax) {
                    // 最大化
                    MyWindow.super.setX(rectangle2D.getMinX());
                    MyWindow.super.setY(rectangle2D.getMinY());
                    MyWindow.super.setWidth(rectangle2D.getWidth());
                    MyWindow.super.setHeight(rectangle2D.getHeight());
                } else {
                    // 还原
                    MyWindow.super.setX(x);
                    MyWindow.super.setY(y);
                    MyWindow.super.setWidth(width);
                    MyWindow.super.setHeight(height);
                }
            }
        });

        // 关闭
        btnClose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MyWindow.super.close();
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
        container.setOnMouseMoved((MouseEvent event) -> {
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
            container.setCursor(cursorType);
        });
        // 监听鼠标拖拽
        container.setOnMouseDragged((MouseEvent event) -> {
            event.consume();
            if (yOffset != 0) {
                super.setX(event.getScreenX() - xOffset);
                if (event.getScreenX() - yOffset < 0) {
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
        container.setOnMousePressed(event -> {
            event.consume();
            xOffset = event.getSceneX();
            if (event.getSceneY() > 46) {
                yOffset = 0;
            } else {
                yOffset = event.getSceneY();
            }
        });
        this.setScene(scene);
    }

    public BorderPane getContainer() {
        return container;
    }
}

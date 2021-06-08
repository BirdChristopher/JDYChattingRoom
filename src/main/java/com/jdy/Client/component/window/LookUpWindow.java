package com.jdy.Client.component.window;

import com.jdy.Client.App;
import com.jdy.Client.component.base.ListViewCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.svg.SVGGlyph;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LookUpWindow extends Stage {
    private Scene scene;
    // View
    private VBox root;
    // 标题栏
    private GridPane topBar;
    private Label title;
    private JFXButton minButton;
    private JFXButton closeButton;
    // 搜索框
    private TextField searchField;
    private Label searchIcon;
    private JFXButton searchButton;
    private HBox searchPane;
    // 查询结果框
    private JFXListView<ListViewCell> resultListView;

    private SVGGlyph minus;
    private SVGGlyph close;
    private String titleName;

    private double xOffset;
    private double yOffset;

    public LookUpWindow(String title) {
        this.titleName = title;
        this.root = new VBox();
        // 标题栏
        this.topBar = new GridPane();
        this.title = new Label();
        this.minButton = new JFXButton();
        this.closeButton = new JFXButton();
        // 搜索框
        this.searchField = new TextField();
        this.searchIcon = new Label();
        this.searchPane = new HBox();
        this.searchButton = new JFXButton("搜索");
        // 查询结果框
        this.resultListView = new JFXListView<>();
        this.scene = new Scene(root, 400, 600);
        this.minus = new SVGGlyph("M872 474H152c-4.4 0-8 3.6-8 8v60c0 4.4 3.6 8 8 8h720c4.4 " +
                "0 8-3.6 8-8v-60c0-4.4-3.6-8-8-8z", Color.WHITE);
        this.close = new SVGGlyph("M563.8 512l262.5-312.9c4.4-5.2 0.7-13.1-6.1-13.1h-79.8c-4.7 0-9.2 2.1-12.3 " +
                "5.7L511.6 449.8 295.1 191.7c-3-3.6-7.5-5.7-12.3-5.7H203c-6.8 0-10.5 7.9-6.1 13.1L459.4 512 196.9 " +
                "824.9c-4.4 5.2-0.7 13.1 6.1 13.1h79.8c4.7 0 9.2-2.1 12.3-5.7l216.5-258.1 216.5 258.1c3 3.6 7.5 5.7 " +
                "12.3 5.7h79.8c6.8 0 10.5-7.9 6.1-13.1L563.8 512z", Color.WHITE);

        xOffset = 0.0;
        yOffset = 0.0;
        initialize();
    }

    private void initialize() {
        this.initStyle(StageStyle.TRANSPARENT);
        title.setText(titleName);
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
        // 搜索框
        searchField.setPrefSize(230, 35);
        searchButton.setPrefSize(62, 35);
        searchButton.setStyle("-fx-background-color: #82c0cc;");
        searchButton.setTextFill(Color.WHITE);
        searchPane.getChildren().addAll(searchField, searchButton);
        searchPane.setPadding(new Insets(5));
        searchPane.setAlignment(Pos.TOP_CENTER);
        searchPane.setSpacing(8);
        searchPane.setPadding(new Insets(8, 0, 0, 0));
        // 查询结果
        resultListView.setPrefSize(300, 450);
        resultListView.setMaxWidth(300);
        resultListView.setStyle("-fx-background-color: #f8edeb;");

        root.getChildren().addAll(topBar, searchPane, resultListView);
        root.setStyle("-fx-background-color: #fcd5ce;");
        root.setSpacing(10);
        root.setAlignment(Pos.TOP_CENTER);
        scene.getStylesheets().add(App.load("/css/home-window.css"));
        this.setScene(scene);

        // 最小化
        minButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LookUpWindow.super.setIconified(true);
            }
        });
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LookUpWindow.super.close();
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
}

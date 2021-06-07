package com.jdy.Client.component.base;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.image.CropImageFilter;

/**
 * 列表单元，封装一下自定义的Label
 */
public class ListViewCell extends Label {
    private ImageView imageView;
    public ListViewCell() {
        imageView = new ImageView(new Image("/image/avatar_default.png"));
        this.setText("默认文字");
        this.setGraphic(imageView);
        initialize();
    }

    public ListViewCell(Image image, String text) {
        imageView = new ImageView(image);
        ImagePattern pattern = new ImagePattern(image);
        Circle circle = new Circle(25);
        circle.setFill(pattern);
        this.setGraphic(circle);
        this.setText(text);
        initialize();
    }

    private void initialize() {
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        this.setPrefSize(350, 60);
        this.setMaxWidth(350);
        this.setPadding(new Insets(5, 8, 5, 5));
        this.setFont(Font.font("SongTi", 18));
        this.setTextFill(Color.BLACK);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setStyle("-fx-background-color: TRANSPARENT;");
    }
}
package com.jdy.Client.component.base;

import com.jdy.Client.util.ImageUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * 列表单元，封装一下自定义的Label.
 *
 * 主要用于好友列表、群聊列表和群成员列表.
 * @author dh
 */
public class ListViewCell extends Label {
    /**
     * 默认构造方法.
     */
    public ListViewCell() {
        this.setText("默认文字");
        this.setGraphic(ImageUtil.circleImage(new Image("/image/avatar01.jpg"), 25));
        initialize();
    }

    /**
     * 带参数的构造方法.
     *
     * @param image 左侧图片
     * @param text  右侧文字表述
     */
    public ListViewCell(Image image, String text) {
        this.setGraphic(ImageUtil.circleImage(image, 25));
        this.setText(text);
        initialize();
    }

    /**
     * 初始化cell的样式.
     */
    private void initialize() {
        this.setPrefSize(350, 60);
        this.setMaxWidth(350);
        this.setPadding(new Insets(5, 8, 5, 5));
        this.setFont(Font.font("SongTi", 18));
        this.setTextFill(Color.BLACK);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setStyle("-fx-background-color: TRANSPARENT;");
    }
}
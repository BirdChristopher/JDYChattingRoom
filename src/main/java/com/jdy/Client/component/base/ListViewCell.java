package com.jdy.Client.component.base;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * 列表单元，图标 + 名字 + 描述（可关闭）
 */
public class ListViewCell extends HBox {
    private ImageView imageView;
    private Text nameText;
    private Text descriptionText;

    public ListViewCell() {
        this.imageView = new ImageView();
        this.nameText = new Text();
        this.descriptionText = new Text();
        initialize();
    }

    private void initialize() {
        super.getChildren().add(0, imageView);
        super.getChildren().add(1, nameText);
        super.getChildren().add(2, descriptionText);
        // TODO: 设置初始化样式
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }

    public void setName(String string) {
        nameText.setText(string);
    }

    public void setDescription(String string) {
        descriptionText.setText(string);
    }

    public void setDescriptionVisible(boolean bool) {
        descriptionText.setVisible(false);
        descriptionText.setManaged(false);
    }
}
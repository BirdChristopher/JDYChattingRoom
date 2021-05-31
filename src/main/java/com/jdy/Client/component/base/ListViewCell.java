package com.jdy.Client.component.base;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ListViewCell extends HBox {
    // View
    private ImageView imageView;
    private Text nameText;
    private Text descriptionText;
    // Data
    private Image image;
    private String name;
    private String description;

    public ListViewCell() {
        this(null, null, null);
    }
    public ListViewCell(Image image, String name) {
        this(image, name, null);
    }
    public ListViewCell(Image image, String name, String description) {
        this.imageView = new ImageView();
        this.nameText = new Text();
        this.descriptionText = new Text();
        this.image = image;
        this.name = name;
        this.description = description;

        imageView.setImage(image);
        nameText.setText(name);
        descriptionText.setText(description);
        super.getChildren().add(0, imageView);
        super.getChildren().add(1, nameText);
        super.getChildren().add(2, descriptionText);
        initialize();
    }

    private void initialize() {
        // TODO: 设置初始化样式
    }

    public Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void changeImage(Image image) {
        imageView.setImage(image);
    }

    public void changeName(String string) {
        nameText.setText(string);
    }

    public void changeDescription(String string) {
        descriptionText.setText(string);
    }
}
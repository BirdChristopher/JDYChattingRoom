package com.jdy.Client.component.base;

import com.jdy.Client.data.user.User;
import com.jdy.Client.util.ImageUtil;
import com.jfoenix.controls.JFXCheckBox;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class UserCheckCell extends HBox {
    private Label userLabel;
    private JFXCheckBox checkBox;

    public UserCheckCell(User user) {
        this.userLabel = new Label();
        this.checkBox = new JFXCheckBox();
        this.userLabel.setGraphic(ImageUtil.circleImage(user.getAvatar(), 20));
        this.userLabel.setText(user.getName() + "(" + user.getUid() + ")");
        this.setPadding(new Insets(8));
        this.getChildren().add(userLabel);
        this.getChildren().add(checkBox);
    }

    public JFXCheckBox getCheckBox() {
        return checkBox;
    }
}

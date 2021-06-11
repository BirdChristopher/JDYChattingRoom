package com.jdy.Client.component.base;

import com.jdy.Client.data.user.User;
import com.jdy.Client.util.ImageUtil;
import com.jfoenix.controls.JFXCheckBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * 新建群聊窗口的列表单元.
 * @author dh
 */
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
        this.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * 返回复选框让调用者获取选择状态.
     * @return 复选框
     */
    public JFXCheckBox getCheckBox() {
        return checkBox;
    }
}

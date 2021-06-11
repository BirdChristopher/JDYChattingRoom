package com.jdy.Client.util;

import com.jdy.Client.controller.ControllerFactory;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.*;

import java.util.Optional;

public class DialogBuilder {
    private String title, message;
    private JFXButton negativeBtn = null;
    private JFXButton positiveBtn = null;
    private Window window;
    private Paint negativeBtnPaint = Paint.valueOf("#747474");//否定按钮文字颜色，默认灰色
    private Paint positiveBtnPaint = Paint.valueOf("#0099ff");
    private JFXAlert<String> alert;

    public DialogBuilder(Control control) {
        window = control.getScene().getWindow();
    }

    public DialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public DialogBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public DialogBuilder setPositiveBtn(String positiveBtnText) {
        return setPositiveBtn(positiveBtnText, null, null);
    }

    public DialogBuilder setNegativeBtn(String negativeBtnText) {
        return setNegativeBtn(negativeBtnText, null, null);
    }

    public DialogBuilder setNegativeBtn(String negativeBtnText, String color) {
        return setNegativeBtn(negativeBtnText, null, color);
    }

    public DialogBuilder setNegativeBtn(String negativeBtnText, OnClickListener negativeBtnOnclickListener, String color) {
        if (color != null) {
            this.negativeBtnPaint = Paint.valueOf(color);
        }
        return setNegativeBtn(negativeBtnText, negativeBtnOnclickListener);
    }

    public DialogBuilder setNegativeBtn(String negativeBtnText, OnClickListener negativeBtnOnclickListener) {

        negativeBtn = new JFXButton(negativeBtnText);
        negativeBtn.setCancelButton(true);
        negativeBtn.setTextFill(negativeBtnPaint);
        negativeBtn.setButtonType(JFXButton.ButtonType.FLAT);
        negativeBtn.setOnAction(addEvent -> {
            alert.hideWithAnimation();
            if (negativeBtnOnclickListener != null) {
                negativeBtnOnclickListener.onClick(); // 接口回调
            }
        });
        return this;
    }

    public DialogBuilder setPositiveBtn(String positiveBtnText, String color) {
        return setPositiveBtn(positiveBtnText, null, color);
    }

    public DialogBuilder setPositiveBtn(String positiveBtnText, OnClickListener positiveBtnOnclickListener, String color) {
        if (color != null) {
            this.positiveBtnPaint = Paint.valueOf(color);
        }
        return setPositiveBtn(positiveBtnText, positiveBtnOnclickListener);
    }

    public DialogBuilder setPositiveBtn(String positiveBtnText, OnClickListener positiveBtnOnclickListener) {
        positiveBtn = new JFXButton(positiveBtnText);
        positiveBtn.setDefaultButton(true);
        positiveBtn.setTextFill(positiveBtnPaint);
        positiveBtn.setOnAction(closeEvent -> {
            alert.hideWithAnimation();
            if (positiveBtnOnclickListener != null) {
                positiveBtnOnclickListener.onClick(); // 接口回调
            }
        });
        return this;
    }

    public JFXAlert<String> create() {
        alert = new JFXAlert<>((Stage) window);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);

        JFXDialogLayout layout = new JFXDialogLayout();
        layout.setMaxWidth(350); // 设置弹窗大小
        layout.setHeading(new Label(title));
        layout.setBody(new VBox(new Label(this.message)));

        //添加确定和取消按钮
        if (negativeBtn != null && positiveBtn != null) {
            layout.setActions(negativeBtn, positiveBtn);
        } else {
            if (negativeBtn != null) {
                layout.setActions(negativeBtn);
            } else if (positiveBtn != null) {
                layout.setActions(positiveBtn);
            }
        }

        alert.setContent(layout);
        Optional<String> input = alert.showAndWait();

        return alert;
    }


    public interface OnClickListener {
        void onClick();
    }
}

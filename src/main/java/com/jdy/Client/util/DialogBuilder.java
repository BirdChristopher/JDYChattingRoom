package com.jdy.Client.util;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.Optional;

/**
 * JFXDialog的封装类.
 *
 * 封装了一下JFXDialog，使用更方便，参考了一下网上的代码.
 *
 */
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

    /**
     * 设置标题.
     * @param title 标题
     * @return this
     */
    public DialogBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * 设置主要内容.
     * @param message 主要内容
     * @return this
     */
    public DialogBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 设置按钮.
     * @param positiveBtnText 按钮文字
     * @return this
     */
    public DialogBuilder setPositiveBtn(String positiveBtnText) {
        return setPositiveBtn(positiveBtnText, null, null);
    }

    /**
     * 设置按钮.
     * @param negativeBtnText 按钮文字
     * @return this
     */
    public DialogBuilder setNegativeBtn(String negativeBtnText) {
        return setNegativeBtn(negativeBtnText, null, null);
    }

    /**
     * 设置按钮.
     * @param negativeBtnText 按钮文字
     * @param color 按钮颜色
     * @return this
     */
    public DialogBuilder setNegativeBtn(String negativeBtnText, String color) {
        return setNegativeBtn(negativeBtnText, null, color);
    }

    /**
     * 设置按钮.
     * @param negativeBtnText 按钮文字
     * @param negativeBtnOnclickListener 按钮监听器
     * @param color 按钮颜色
     * @return this
     */
    public DialogBuilder setNegativeBtn(String negativeBtnText, OnClickListener negativeBtnOnclickListener, String color) {
        if (color != null) {
            this.negativeBtnPaint = Paint.valueOf(color);
        }
        return setNegativeBtn(negativeBtnText, negativeBtnOnclickListener);
    }

    /**
     * 设置按钮.
     * @param negativeBtnText 按钮文字
     * @param negativeBtnOnclickListener 按钮监听器
     * @return this
     */
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

    /**
     * 设置按钮.
     * @param positiveBtnText 按钮文字
     * @param color 按钮颜色
     * @return this
     */
    public DialogBuilder setPositiveBtn(String positiveBtnText, String color) {
        return setPositiveBtn(positiveBtnText, null, color);
    }

    /**
     * 设置按钮.
     * @param positiveBtnText 按钮文字
     * @param positiveBtnOnclickListener 按钮监听器
     * @param color 按钮颜色
     * @return this
     */
    public DialogBuilder setPositiveBtn(String positiveBtnText, OnClickListener positiveBtnOnclickListener, String color) {
        if (color != null) {
            this.positiveBtnPaint = Paint.valueOf(color);
        }
        return setPositiveBtn(positiveBtnText, positiveBtnOnclickListener);
    }

    /**
     * 设置按钮.
     * @param positiveBtnText 按钮文字
     * @param positiveBtnOnclickListener 按钮监听器
     * @return this
     */
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

    /**
     * 新建弹窗.
     * @return alert
     */
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

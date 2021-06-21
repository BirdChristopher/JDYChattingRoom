package com.jdy.Client.component.window;

import com.jdy.Client.util.ImageUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * 选择头像的窗口类.
 *
 * @author dh
 */
public class ImageSelectWindow extends Stage {
    private Scene scene;
    private VBox root;
    private GridPane gridPane;
    private ArrayList<Label> labelList;
    private Button confirmButton;
    private Button cancelButton;
    private HBox buttonPane;
    private int selectedNum;

    public ImageSelectWindow() {
        this.root = new VBox();
        this.gridPane = new GridPane();
        this.labelList = new ArrayList<>();
        this.confirmButton = new Button();
        this.cancelButton = new Button();
        this.buttonPane = new HBox();
        this.scene = new Scene(root, 720, 700);

        for (int i = 0; i < 16; ++i) {
            Label label = new Label();
            label.setGraphic(ImageUtil.circleImage(new Image("/image/avatar/" + i + ".jpg"), 50));
            label.setPadding(new Insets(20));
            labelList.add(label);
        }
        this.selectedNum = 0;

        initialize();
    }

    /**
     * 初始化样式，绑定事件.
     */
    private void initialize() {
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                Label label = labelList.get(i * 4 + j);
                gridPane.add(label, j, i);
                label.setOnMouseClicked(event -> {
                    reset();
                    label.setStyle("-fx-background-color: rgb(0,234,211,0.2);");
                    int selectedCol = GridPane.getColumnIndex(label);
                    int selectedRow = GridPane.getRowIndex(label);
                    selectedNum = selectedCol + 4 * selectedRow;
                });
            }
        }
        confirmButton.setText("确认");
        confirmButton.setFont(Font.font(18));
        cancelButton.setPrefSize(70, 40);
        cancelButton.setText("取消");
        confirmButton.setFont(Font.font(18));
        confirmButton.setPrefSize(70, 40);
        buttonPane.getChildren().addAll(cancelButton, confirmButton);
        buttonPane.setPadding(new Insets(8));
        buttonPane.setSpacing(10);
        buttonPane.setAlignment(Pos.CENTER_RIGHT);
        gridPane.setHgap(30);
        gridPane.setVgap(20);
        root.setPadding(new Insets(10, 35, 20, 35));
        root.setSpacing(8);
        root.getChildren().add(gridPane);
        root.getChildren().add(buttonPane);
        root.setStyle("-fx-background-color: #fff5b7");
        this.setScene(scene);
        this.setResizable(false);
        this.setTitle("请选择您喜欢的头像");

        cancelButton.setOnAction(event -> {
            this.close();
        });
    }

    /**
     * 重置视图选择状态.
     */
    private void reset() {
        for (int i = 0; i < 16; ++i) {
            labelList.get(i).setStyle("-fx-background-color: TRANSPARENT;");
        }
    }

    public int getSelectedNum() {
        return selectedNum;
    }

    public Button getConfirmButton() {
        return confirmButton;
    }

    /**
     * 关闭窗口时重置选择状态.
     */
    @Override
    public void close() {
        selectedNum = 0;
        reset();
        super.close();
    }
}

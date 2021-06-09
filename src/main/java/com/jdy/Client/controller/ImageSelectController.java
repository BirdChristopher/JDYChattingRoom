package com.jdy.Client.controller;

import com.jdy.Client.component.window.ImageSelectWindow;
import javafx.scene.image.Image;

public class ImageSelectController {
    private ImageSelectWindow window;
    private Image imageSelected;
    private String commander;

    public ImageSelectController() {
        window = new ImageSelectWindow();

        window.getConfirmButton().setOnAction(event -> {
            imageSelected = new Image("/image/avatar/" + window.getSelectedNum() + ".jpg");
            if ("register".equals(commander)) {
                ControllerFactory.getRegisterController().updateAvatar(imageSelected, window.getSelectedNum());
            } else if ("group".equals(commander)) {

            }
            window.close();
        });
    }

    public Image getImageSelected() {
        return imageSelected;
    }

    public void showWindow(String value) {
        commander = value;
        window.show();
    }

    public void closeWindow() {
        window.close();
    }
}

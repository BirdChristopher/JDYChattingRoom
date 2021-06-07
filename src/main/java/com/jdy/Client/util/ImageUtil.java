package com.jdy.Client.util;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;


public class ImageUtil {
    // 返回圆形图片
    public static Circle circleImage(Image image, double radius) {
        return new Circle(radius, new ImagePattern(image));
    }
}

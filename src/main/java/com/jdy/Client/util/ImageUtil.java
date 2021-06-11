package com.jdy.Client.util;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

/**
 * 图片处理类.
 *
 * 获取圆形图片.
 *
 * @author dh
 */
public class ImageUtil {
    /**
     * 获取圆形图片.
     * @param image 要处理的图片
     * @param radius 半径
     * @return 圆形图片
     */
    public static Circle circleImage(Image image, double radius) {
        return new Circle(radius, new ImagePattern(image));
    }
}

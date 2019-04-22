package ui;

import javax.swing.*;
import java.awt.*;

/**
 * @author yiner
 * @since 2019-04-22
 */
public class Main {
    public static void main(String[] args) {
        JFrame frame = new MainClient();
        frame.setSize(600, 500);
        frame.setMinimumSize(new Dimension(600, 500));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // 窗口居中显示
        int windowWidth = frame.getWidth(); // 获得窗口宽
        int windowHeight = frame.getHeight(); // 获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width; // 获取屏幕的宽
        int screenHeight = screenSize.height; // 获取屏幕的高
        frame.setLocation(screenWidth / 2 - windowWidth / 2,
                screenHeight / 2 - windowHeight / 2 - 40); // 设置位置居中

        frame.pack();
        frame.setVisible(true);
    }
}

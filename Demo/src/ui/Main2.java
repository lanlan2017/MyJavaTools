package ui;

import java.awt.*;

public class Main2 {
    public static void main(String[] args) {
        //
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // 获取图形设备
        GraphicsDevice[] gd = ge.getScreenDevices();
        System.out.println(gd.length);
        // 如果有两个屏幕
        if (gd.length == 2) {
            System.out.println("你有两个屏幕");
            try {
                Robot robot = new Robot(gd[1]);
                // robot.mouseMove();
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
    }
}

package ui;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(400, 400);
        jf.setDefaultCloseOperation(3);
        jf.setVisible(true);
        // 在主屏幕显示
        Main.showOnScreen(0, jf);//主屏显示

        JFrame jf2 = new JFrame();
        jf2.setSize(200, 400);
        jf2.setDefaultCloseOperation(3);
        jf2.setVisible(true);
        // 在副屏幕显示
        Main.showOnScreen(1, jf2);//副屏显示
    }


    /**
     * 指定显示屏幕相应内容
     * screen 显示器序号
     */
    public static void showOnScreen(int screen, JFrame frame) {
        // 获取图形环境
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        // 获取图形设备
        GraphicsDevice[] gd = ge.getScreenDevices();
        if (screen > -1 && screen < gd.length) {
            // 一个或多个屏幕
            frame.setLocation(gd[screen].getDefaultConfiguration().getBounds().x, frame.getY());
        } else if (gd.length > 0) {
            // 只有一个屏幕
            frame.setLocation(gd[0].getDefaultConfiguration().getBounds().x, frame.getY());
        } else {
            //未获取到屏幕信息
            throw new RuntimeException("No Screens Found");
        }
    }
}

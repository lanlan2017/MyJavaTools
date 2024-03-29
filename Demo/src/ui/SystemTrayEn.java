package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class SystemTrayEn {
    public static void main(String[] args) {
        // 应用主窗口
        JFrame frame = new JFrame();
        frame.setBounds(500, 500, 200, 200);
        frame.setVisible(true);
        initSystemTray(frame);
    }
    /**
     * 初始化系统托盘
     */
    private static void initSystemTray(JFrame frame) {
        // 判断系统是否支持托盘图标
        if (java.awt.SystemTray.isSupported()) {

            // // 获取托盘图标,图片请放在 当前包 下
            URL resource = SystemTray.class.getResource("/工具_16.png");
            // 创建图标
            assert resource != null;
            ImageIcon icon = new ImageIcon(resource);
            // 创建弹出式菜单
            PopupMenu pop = new PopupMenu();

            // String str = "显示主窗体";
            String str = "show main form";
            // 创建 显示菜单项
            MenuItem displayJFrameItem = new MenuItem(str);
            // 给 显示窗体菜单项 添加事件处理程序
            displayJFrameItem.addActionListener(e -> frame.setVisible(true));
            // 显示菜单项 添加到 弹出式菜单中
            pop.add(displayJFrameItem);

            // 创建 退出菜单项
            // String exitLabel = "退出";
            String exitLabel = "exit";
            MenuItem exitItem = new MenuItem(exitLabel);
            // 给 退出菜单项 添加事件监听器，单击时退出系统
            exitItem.addActionListener(e -> System.exit(0));
            // 添加 退出菜单项 到弹出框中
            pop.add(exitItem);

            // 创建托盘图标程序
            TrayIcon tray = new TrayIcon(icon.getImage(), "CommandsUI", pop);
            // 获得系统托盘对象
            java.awt.SystemTray systemTray = java.awt.SystemTray.getSystemTray();
            try {
                // 将托盘图标添加到系统托盘中
                systemTray.add(tray);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
    }
}

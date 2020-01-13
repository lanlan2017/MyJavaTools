package main;

import java.awt.EventQueue;
import ui.ScreenShotWindow;
import ui.toolbar.ToolsWindow;

public class ScreenShotOcr
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            // 实例化截屏窗体
            ScreenShotWindow.getInstance();
            // 实例化共具体窗体
            ToolsWindow.getInstance();
        });
    }
}
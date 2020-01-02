package main;

import java.awt.EventQueue;
import ui.ScreenShotWindow;
import ui.ToolsWindow;

public class ScreenShotOcr
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                // 实例化截屏窗体
                ScreenShotWindow.getInstance();
                // 实例化共具体窗体
                ToolsWindow.getInstance();
            }
        });
    }
}
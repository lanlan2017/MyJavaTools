package main;

import ui.ScreenShotWindow;
import ui.ToolsWindow;
import ui.setting.FontTools;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class ScreenShotOcr
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(() -> {
            // 实例化截屏窗体
            ScreenShotWindow.getInstance();
            // 实例化共具体窗体
            ToolsWindow.getInstance();
            // initGobalFont(FontTools.f2);
        });
    }
    // public static void initGobalFont(Font font) {
    //     FontUIResource fontResource = new FontUIResource(font);
    //     for(Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
    //         Object key = keys.nextElement();
    //         Object value = UIManager.get(key);
    //         if(value instanceof FontUIResource) {
    //             System.out.println(key);
    //             UIManager.put(key, fontResource);
    //         }
    //     }
    // }
}

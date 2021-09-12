package blue.ocr.main;

import blue.ocr.ui.ScreenShotWindow;
import blue.ocr.ui.ToolsWindow;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class ScreenShotOcr {
    public static void main(String[] args) {
        // 设置外观
        FlatLightLaf.setup();
        EventQueue.invokeLater(() -> {
            // 实例化截屏窗体
            ScreenShotWindow.getInstance();
            // 实例化共具体窗体
            ToolsWindow.getInstance();
            // 设置主题
            // FlatLightLaf.setup();
            // 给所有的组件都使用该主题
            // SwingUtilities.updateComponentTreeUI(ToolsWindow.getInstance());
        });
    }
}
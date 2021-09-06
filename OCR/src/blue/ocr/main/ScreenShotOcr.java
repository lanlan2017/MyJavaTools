package blue.ocr.main;

import blue.ocr.ui.ScreenShotWindow;
import blue.ocr.ui.ToolsWindow;

import java.awt.*;

public class ScreenShotOcr {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            // 实例化截屏窗体
            ScreenShotWindow.getInstance();
            // 实例化共具体窗体
            ToolsWindow.getInstance();
        });
    }
}
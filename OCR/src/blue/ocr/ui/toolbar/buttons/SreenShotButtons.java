package blue.ocr.ui.toolbar.buttons;

import blue.ocr.ui.ScreenShotWindow;
import blue.ocr.ui.ToolsWindow;

import javax.swing.*;

/**
 * 截屏和取消截屏按钮
 */
public class SreenShotButtons {

    private static SreenShotButtons instance = new SreenShotButtons();
    JButton cancelButton;
    JButton startButton;

    private SreenShotButtons() {
        cancelButton = new JButton("取消截屏");
        // cancelButton.setFont(FontTools.f1);
        cancelButton.addActionListener(e -> {
            // 不显示截屏窗口
            ScreenShotWindow.getInstance().setVisible(false);
            // 移动窗体到最左边
            ToolsWindow.defaultLocation();
        });
        startButton = new JButton("截屏");
        startButton.addActionListener(e -> {
            // 再次截屏
            ScreenShotWindow.getInstance().screenshotAgain();
            // 显示窗口
            ScreenShotWindow.getInstance().setVisible(true);
        });
    }

    public static SreenShotButtons getInstance() {
        return instance;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getStartButton() {
        return startButton;
    }

}

package ui.toolbar.buttons;

import ui.ScreenShotWindow;
import ui.toolbar.ToolsWindow;

import javax.swing.*;

/**
 * @author francis
 * create at 2020/1/13-15:35
 */
public class SreenShotButton {

    private static SreenShotButton instance = new SreenShotButton();
    JButton cancelButton;
    JButton startButton;

    private SreenShotButton() {
        cancelButton = new JButton("取消截屏");
        cancelButton.addActionListener(e -> {
            ScreenShotWindow.getInstance().setVisible(false);// 不显示截屏窗口
            // 移动窗体到最左边
            ToolsWindow.getInstance().setLocation(0, 0);
        });
        startButton = new JButton("截屏");
        startButton.addActionListener(e -> {
            // 再次截屏
            ScreenShotWindow.getInstance().screenshotAgain();
            // 显示窗口
            ScreenShotWindow.getInstance().setVisible(true);
        });
    }

    public static SreenShotButton getInstance() {
        return instance;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public JButton getStartButton() {
        return startButton;
    }

}

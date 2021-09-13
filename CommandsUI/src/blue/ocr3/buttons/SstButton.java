package blue.ocr3.buttons;

import blue.ocr3.ScreenShotWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SstButton extends ButtonKeyAction {
    private static SstButton instance;

    private SstButton() {
        this.button = new JButton("截屏");
        this.abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 再次截屏
                ScreenShotWindow.getInstance().screenshotAgain();
                // 显示窗口
                ScreenShotWindow.getInstance().setVisible(true);
            }
        };
        this.modifiers = KeyEvent.CTRL_DOWN_MASK;
        this.keyCode = KeyEvent.VK_W;
        setAction();
        setKeys();
    }

    private SstButton(JPanel fatherPanel) {
        this.button = new JButton("截屏");
        this.abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 再次截屏
                ScreenShotWindow.getInstance().screenshotAgain();
                // 显示窗口
                ScreenShotWindow.getInstance().setVisible(true);
            }
        };
        this.modifiers = KeyEvent.CTRL_DOWN_MASK;
        this.keyCode = KeyEvent.VK_W;
        this.fatherPanel = fatherPanel;
        setAction();
        setKeys();
    }

    public static SstButton getInstance(JPanel fatherPanel) {
        if (instance == null) {
            instance = new SstButton(fatherPanel);
        }
        return instance;
    }
}

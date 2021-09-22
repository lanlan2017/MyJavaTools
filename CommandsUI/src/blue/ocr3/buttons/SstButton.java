package blue.ocr3.buttons;

import blue.ocr3.ScreenShotWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SstButton extends ButtonKeyAction {
    private static SstButton instance;

    private SstButton() {
        this.button = new JButton("截屏");
        this.abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sstButtonAction();
            }
        };
        setAction();
    }

    public void sstButtonAction() {
        // 再次截屏
        ScreenShotWindow.getInstance().screenshotAgain();
        // 显示窗口
        ScreenShotWindow.getInstance().setVisible(true);
    }

    public static SstButton getInstance() {
        if (instance == null) {
            instance = new SstButton();
        }
        return instance;

    }
}

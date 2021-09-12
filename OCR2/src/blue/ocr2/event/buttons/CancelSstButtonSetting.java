package blue.ocr2.event.buttons;

import blue.ocr2.ScreenShotWindow;
import blue.ocr2.kuiajiejian.ShortcutKeyRegister;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CancelSstButtonSetting {

    private JButton cancelScreenshotButton;
    ShortcutKeyRegister keyRegister;

    public CancelSstButtonSetting(JButton cancelScreenshotButton, ShortcutKeyRegister keyRegister) {
        this.cancelScreenshotButton = cancelScreenshotButton;
        this.keyRegister = keyRegister;
        cancelSstAction();
    }

    private void cancelSstAction() {
        AbstractAction cancelSstAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 不显示截屏窗口
                ScreenShotWindow.getInstance().setVisible(false);
            }
        };
        // cancelScreenshotButton.addActionListener(cancelSstAction);
        keyRegister.keysToButton(cancelScreenshotButton, cancelSstAction, KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_E);
    }
}

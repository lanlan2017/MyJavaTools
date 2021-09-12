package blue.ocr2.event.buttons;

import blue.ocr2.ScreenShotWindow;
import blue.ocr2.kuiajiejian.ShortcutKeyRegister;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SstButtonSetting {
    JButton screenshotButton;
    ShortcutKeyRegister keyRegister;

    public SstButtonSetting(JButton screenshotButton, ShortcutKeyRegister keyRegister) {
        this.screenshotButton = screenshotButton;
        sstButtonAction(keyRegister);
    }

    private void sstButtonAction(ShortcutKeyRegister keyRegister) {
        // 截屏按钮事件处理程序
        AbstractAction sstButtonAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 再次截屏
                ScreenShotWindow.getInstance().screenshotAgain();
                // 显示窗口
                ScreenShotWindow.getInstance().setVisible(true);
            }
        };
        // screenshotButton.addActionListener(sstButtonAction);
        keyRegister.keysToButton(screenshotButton, sstButtonAction, KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_W);
    }
}

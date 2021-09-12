package blue.ocr2.event.buttons;

import blue.ocr2.kuiajiejian.ShortcutKeyRegister;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitButtonSetting {
    JButton exitButton;
    ShortcutKeyRegister keyRegister;

    public ExitButtonSetting(JButton exitButton, ShortcutKeyRegister keyRegister) {
        this.exitButton = exitButton;
        exitButtonAction(keyRegister);
    }

    public void exitButtonAction(ShortcutKeyRegister keyRegister) {
        /**
         * 退出按钮事件处理程序
         */
        AbstractAction exitButtonAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        keyRegister.keysToButton(exitButton, exitButtonAction, KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_Q);
    }
}

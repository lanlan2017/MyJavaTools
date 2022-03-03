package blue.ocr3.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * 退出按钮
 */
public class ExitButton extends ButtonKeyAction {
    private static ExitButton instance = new ExitButton();

    private ExitButton() {
        this.button = new JButton("退出");
        /**
         * 退出按钮事件处理程序
         */
        this.abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        this.modifiers = KeyEvent.ALT_DOWN_MASK;
        this.keyCode = KeyEvent.VK_Q;
        setAction();
    }

    private ExitButton(JPanel fatherPanel) {
        this.button = new JButton("退出");
        /**
         * 退出按钮事件处理程序
         */
        this.abstractAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        this.modifiers = KeyEvent.ALT_DOWN_MASK;
        this.keyCode = KeyEvent.VK_Q;
        this.fatherPanel = fatherPanel;
        setAction();
        setKeys();
    }

    public static ExitButton getInstance(JPanel fatherPanel) {
        if (instance == null) {
            new ExitButton(fatherPanel);
        } else {
            instance.fatherPanel = fatherPanel;
            instance.setKeys();
        }
        return instance;
    }
}

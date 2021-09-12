package blue.ocr3.buttons;

import blue.ocr3.OCR3Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * 退出按钮
 */
public class ExitButton {
    private static ExitButton instance = new ExitButton();
    private JPanel panel;
    private JButton exitButton;
    private AbstractAction exitButtonAction;

    public AbstractAction getExitButtonAction() {
        return exitButtonAction;
    }

    private ExitButton() {
        exitButton = new JButton("退出");
        // exitButton.addActionListener(e -> System.exit(0));
        /**
         * 退出按钮事件处理程序
         */
        exitButtonAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };
        // keyRegister.keysToButton(exitButton, exitButtonAction, KeyEvent.ALT_DOWN_MASK, KeyEvent.VK_Q);
        // 给按钮设置时间监听器
        // exitButton.addActionListener(exitButtonAction);
        // System.out.println("按钮文字="+button.getText());
        // System.out.println();
        // 使用按钮的文字作为key
        // String key = exitButton.getText();
        // // 讲key关联到action监听器
        // // rootPanel.getActionMap().put(key, actionListener);
        // OCR3Form.rootPanel.getActionMap().put(key, exitButtonAction);
        // // button.getActionMap().put(key, actionListener);
        // // 讲快捷键关联到key
        // // rootPanel.getInputMap().put(KeyStroke.getKeyStroke(keyCode, modifiers), key);
        // OCR3Form.rootPanel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.ALT_DOWN_MASK), key);
    }

    public static ExitButton getInstance() {
        return instance;
    }

    public void setKeys(JPanel fatharPanl) {
        // fatharPanl = fatharPanl;
        exitButton.addActionListener(exitButtonAction);
        String key = exitButton.getText();
        // // 讲key关联到action监听器
        // // rootPanel.getActionMap().put(key, actionListener);
        fatharPanl.getActionMap().put(key, exitButtonAction);
        // // button.getActionMap().put(key, actionListener);
        // // 讲快捷键关联到key
        // // rootPanel.getInputMap().put(KeyStroke.getKeyStroke(keyCode, modifiers), key);
        fatharPanl.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.ALT_DOWN_MASK),key);
    }

    public JButton getExitButton() {
        return exitButton;
    }
}

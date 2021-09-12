package blue.ocr3.kuiajiejian;

import blue.ocr3.buttons.ExitButton;

import javax.swing.*;

/**
 * 快捷键注册器。
 */
public final class ShortcutKeyRegister {
    /**
     * 父容器
     */
    JPanel rootPanel;

    // public ShortcutKeyRegister() {
    //     rootPanel = OCR2Form.getInstance().getRootPanel();
    // }

    public ShortcutKeyRegister(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    /**
     * 给按钮注册快捷键
     *
     * @param button         按钮
     * @param actionListener 按钮的事件处理程序
     * @param modifiers      功能键，ctrl,alt,shift等按键
     * @param keyCode        普通键 a-z，enter等。
     */
    public void keysToButton(JButton button, AbstractAction actionListener, int modifiers, int keyCode) {
        // 给按钮设置时间监听器
        button.addActionListener(actionListener);
        // System.out.println("按钮文字="+button.getText());
        // System.out.println("button.getActionCommand() = " + button.getActionCommand());
        // System.out.println("button.getUIClassID() = " + button.getUIClassID());
        // System.out.println("button.getName() = " + button.getName());
        // System.out.println("button.getText() = " + button.getText());
        // System.out.println();
        // 使用按钮的文字作为key
        String key = button.getText();
        // 讲key关联到action监听器
        rootPanel.getActionMap().put(key, actionListener);
        // button.getActionMap().put(key, actionListener);
        // 讲快捷键关联到key
        rootPanel.getInputMap().put(KeyStroke.getKeyStroke(keyCode, modifiers), key);
        // button.getInputMap().put(KeyStroke.getKeyStroke(keyCode, modifiers), key);
    }
    public void keysToButton(ExitButton exitButton, int modifiers, int keyCode) {
        // 给按钮设置时间监听器
        // button.addActionListener(actionListener);
        // System.out.println("按钮文字="+button.getText());
        // System.out.println("button.getActionCommand() = " + button.getActionCommand());
        // System.out.println("button.getUIClassID() = " + button.getUIClassID());
        // System.out.println("button.getName() = " + button.getName());
        // System.out.println("button.getText() = " + button.getText());
        // System.out.println();
        // 使用按钮的文字作为key
        String key = exitButton.getExitButton().getText();
        // 讲key关联到action监听器
        rootPanel.getActionMap().put(key, exitButton.getExitButtonAction());
        // button.getActionMap().put(key, actionListener);
        // 讲快捷键关联到key
        rootPanel.getInputMap().put(KeyStroke.getKeyStroke(keyCode, modifiers), key);
        // button.getInputMap().put(KeyStroke.getKeyStroke(keyCode, modifiers), key);
    }
}

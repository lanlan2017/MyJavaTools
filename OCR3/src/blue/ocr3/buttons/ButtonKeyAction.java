package blue.ocr3.buttons;

import javax.swing.*;

public abstract class ButtonKeyAction {
    /**
     * 按钮
     */
    protected JButton button;
    /**
     * 按钮的处理程序
     */
    protected AbstractAction abstractAction;
    /**
     * 快捷键，主键：ctrl,shift,alt等键
     */
    protected int modifiers;
    /**
     * 快捷键，辅助键：a-z等键，
     */
    protected int keyCode;
    /**
     * 按钮所在的面包，设置快捷键的时候用到
     */
    protected JPanel fatherPanel;

    /**
     * 设置快捷键
     */
    public void setKeys() {
        // 使用按钮的文本作为key
        String key = button.getText();
        // 讲key关联到action监听器
        fatherPanel.getActionMap().put(key, abstractAction);
        // fatherPanel.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK), key);
        // 讲快捷键关联到key
        fatherPanel.getInputMap().put(KeyStroke.getKeyStroke(keyCode, modifiers), key);
    }

    /**
     * 设置事件处理器
     */
    public void setAction() {
        button.addActionListener(abstractAction);
    }

    /**
     * 获取按钮对象
     * @return 按钮对象
     */
    public JButton getButton() {
        return button;
    }
}

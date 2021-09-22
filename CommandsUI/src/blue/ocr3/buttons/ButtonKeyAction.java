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

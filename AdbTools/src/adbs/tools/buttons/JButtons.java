package adbs.tools.buttons;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * JButton工具类
 */
public class JButtons {
    /**
     * 释放触发事件的按键的焦点。
     *
     * @param e 触发事件
     */
    public static void setFocusPainted(ActionEvent e) {
        AbstractButton openBtn = (AbstractButton) e.getSource();
        // 释放焦点
        openBtn.setFocusPainted(false);
    }
}

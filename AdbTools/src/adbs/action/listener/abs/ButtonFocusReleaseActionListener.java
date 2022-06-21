package adbs.action.listener.abs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 按钮焦点释放ActionListener
 */
public abstract class ButtonFocusReleaseActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // 处理事件
        actionEvent(e);

        AbstractButton openBtn = (AbstractButton) e.getSource();
        //设置外边距
        // Insets insets = new Insets(1, 1, 1, 1);
        // openBtn.setMargin(insets);

        // 释放焦点
        openBtn.setFocusPainted(false);
    }

    /**
     * ActionEvent事件处理方法
     *
     * @param e ActionEvent事件
     */
    protected abstract void actionEvent(ActionEvent e);
}

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
        Insets insets = new Insets(1, 1, 1, 1);
        openBtn.setMargin(insets);
        openBtn.setFocusPainted(false);
        // Color oldBackground = openBtn.getBackground();
        // openBtn.setFocusPainted(false);
        // openBtn.setBackground(Color.red);
        // openBtn.repaint();
        // // openBtn.updateUI();
        // // Robots.delay(5 * 1000);
        // openBtn.setBackground(oldBackground);
        // openBtn.updateUI();
        // openBtn.repaint();
        // 释放焦点
        // // 释放按钮的焦点
        // JButtons.setFocusPainted(e);
    }

    /**
     * ActionEvent事件处理方法
     *
     * @param e ActionEvent事件
     */
    protected abstract void actionEvent(ActionEvent e);
}

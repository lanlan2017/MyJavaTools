package adbs.test.auto.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class OtherJCheckBoxItemListener implements ItemListener {
    /**
     * 窗体
     */
    private JFrame frame;
    /**
     * 被控制的JPanel
     */
    private JPanel controlledJPanel;

    public OtherJCheckBoxItemListener(JFrame frame, JPanel controlledJPanel) {
        this.frame = frame;
        this.controlledJPanel = controlledJPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // 获取窗体的内容面板
        Container contentPane = frame.getContentPane();
        // 如果当前的状态是勾选状态
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 显示被控制的面板
            controlledJPanel.setVisible(true);
        } else {
            // 隐藏面板
            controlledJPanel.setVisible(false);
        }
        // 以最佳方式显示
        frame.pack();
    }
}

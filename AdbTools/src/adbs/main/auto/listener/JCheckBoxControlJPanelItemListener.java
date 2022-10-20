package adbs.main.auto.listener;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 多选框面板选项监听器
 */
public class JCheckBoxControlJPanelItemListener implements ItemListener {
    /**
     * 窗体
     */
    protected JFrame frame;
    /**
     * 被控制的JPanel
     */
    protected JPanel jPanel;

    public JCheckBoxControlJPanelItemListener(JFrame frame, JPanel jPanel) {
        this.frame = frame;
        this.jPanel = jPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // 如果当前的状态是勾选状态
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 显示被控制的面板
            jPanel.setVisible(true);
        } else {
            // 隐藏面板
            jPanel.setVisible(false);
        }
        // 以最佳方式显示
        frame.pack();
    }
}

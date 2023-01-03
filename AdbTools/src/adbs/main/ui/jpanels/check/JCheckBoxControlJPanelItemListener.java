package adbs.main.ui.jpanels.check;

import adbs.main.ui.jframe.JFramePack;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 多选框面板 的复选框选项 监听器
 */
public class JCheckBoxControlJPanelItemListener implements ItemListener {
    /**
     * 被控制的JPanel
     */
    protected JPanel jPanel;

    public JCheckBoxControlJPanelItemListener(JPanel jPanel) {
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
        // 更新窗体界面，以最佳大小显示窗体
        JFramePack.onJComponentActionEvent(e);
    }
}

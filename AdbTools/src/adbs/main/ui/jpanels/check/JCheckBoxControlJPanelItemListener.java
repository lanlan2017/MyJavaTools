package adbs.main.ui.jpanels.check;

import adbs.main.AdbTools;
import adbs.main.ui.jframe.JFramePack;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * 监听器：根据多选框的状态来显示或者因此JPanel
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
            if (jPanel.equals(AdbTools.getInstance().getAppPanels().getAppPanel())) {
                //                System.out.println("显示 签到面板");
                JFramePack.pack();
            } else {
                //                System.out.println("显示 其他面板");
            }
            after();
        } else {
            // 隐藏面板
            jPanel.setVisible(false);
        }
        // 更新窗体界面，以最佳大小显示窗体
        JFramePack.pack();
    }

    /**
     * 展开面包后的动作，如果需要的话，重写此方法
     */
    protected void after() {
    }

}

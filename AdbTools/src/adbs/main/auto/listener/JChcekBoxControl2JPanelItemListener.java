package adbs.main.auto.listener;

import javax.swing.*;
import java.awt.event.ItemEvent;

public class JChcekBoxControl2JPanelItemListener extends JCheckBoxControlJPanelItemListener {

    private JPanel jPanel2;

    public JChcekBoxControl2JPanelItemListener(JFrame frame, JPanel jPanel1, JPanel jPanel2) {
        super(frame, jPanel1);
        this.jPanel2 = jPanel2;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // super.itemStateChanged(e);

        // 如果当前的状态是勾选状态
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // 显示被控制的面板
            // jPanel2.setVisible(true);
            // 显示被控制的面板
            jPanel.setVisible(true);
        } else {
            // 隐藏面板
            jPanel2.setVisible(false);
            jPanel.setVisible(false);
        }
        // 以最佳方式显示
        frame.pack();

    }
}

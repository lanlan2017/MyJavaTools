package blue.commands.ui.event.radiobutton;

import blue.commands.ui.MainFrom;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButtonItemListener implements ItemListener {
    JFrame frame;
    JPanel ocrPanel;
    private int last;

    public RadioButtonItemListener(JFrame frame, JPanel ocrPanel) {
        this.frame = frame;
        this.ocrPanel = ocrPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        JSplitPane jSplitPane = MainFrom.getInstance().getJSplitPane();
        // 如果OCR面板可见的话
        if (ocrPanel.isVisible()) {
            // 获取分隔条的位置
            last = jSplitPane.getDividerLocation();
            // 把分隔条的位置设置为0
            // 隐藏分隔条左侧的面板
            jSplitPane.setDividerLocation(0);
        } else {
            // 调整分隔条到原来的位置
            // 显示分隔条左侧的面板
            jSplitPane.setDividerLocation(last);
        }
        // 显示或隐藏 OCR面板
        ocrPanel.setVisible(!ocrPanel.isVisible());
        // 以合适大小显示窗体
        frame.pack();
    }
}
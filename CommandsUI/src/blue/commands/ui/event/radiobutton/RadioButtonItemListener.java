package blue.commands.ui.event.radiobutton;

import blue.commands.ui.MainFrom;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class RadioButtonItemListener implements ItemListener {
    JFrame frame;
    JPanel ocrPanel;

    public RadioButtonItemListener(JFrame frame, JPanel ocrPanel) {
        this.frame = frame;
        this.ocrPanel = ocrPanel;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        // 显示或隐藏 OCR面板
        ocrPanel.setVisible(!ocrPanel.isVisible());
        // 以合适大小显示窗体
        frame.pack();
    }
}
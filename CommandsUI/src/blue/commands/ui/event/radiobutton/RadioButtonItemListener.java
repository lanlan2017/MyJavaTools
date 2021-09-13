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
        // 显示或隐藏OCR面板
        ocrPanel.setVisible(!ocrPanel.isVisible());
        // 隐藏输入文本域
        JTextArea inputTextArea = MainFrom.getInstance().getInputTextArea();
        inputTextArea.setText("");
        inputTextArea.setVisible(false);

        // 以合适大小显示窗体
        frame.pack();
    }
}
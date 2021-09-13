package blue.commands.ui.event.textfield;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class TextFieldFocusAdapter extends FocusAdapter {
    JFrame frame;
    JPanel scrollPaneFather;
    JTextArea textArea;

    public TextFieldFocusAdapter(JFrame frame, JPanel scrollPaneFather, JTextArea textArea) {
        this.frame = frame;
        this.scrollPaneFather = scrollPaneFather;
        this.textArea = textArea;
    }

    @Override
    public void focusGained(FocusEvent e) {
        // 如果文本域中有内容的话
        if (!"".equals(textArea.getText())) {
            // 显示文本域面板
            // scrollPane.setVisible(true);
            scrollPaneFather.setVisible(true);
            // 最小化显示组件
            frame.pack();
        }
        // 窗体不透明
        frame.setOpacity(1.0f);
    }
}
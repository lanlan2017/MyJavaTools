package blue.commands.ui.event.textarea;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextAreaMouseListener extends MouseAdapter {
    JFrame frame;
    JTextArea textArea;

    public TextAreaMouseListener(JFrame frame, JTextArea textArea) {
        this.frame = frame;
        this.textArea = textArea;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 如果按下的是鼠标右键,并且按下两次
        // 也就是右键双击
        if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2) {
            System.out.println("鼠标右键双击");
            // 情况内容
            textArea.setText("");
            // 设置行数
            textArea.setRows(0);
            // 调整窗体大小
            frame.pack();
        }
    }
}
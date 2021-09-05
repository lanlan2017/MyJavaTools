package com.blue.ui.event.textfield;

import com.blue.tool.ThreadAutoSetFrameOpacity;

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
            // 当文本框获得焦点的时候，关闭透明检查
            ThreadAutoSetFrameOpacity.isSetOpacity = false;
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
package com.blue.ui.event.panel;

import com.blue.tool.ThreadAutoSetFrameOpacity;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMouseListener extends MouseAdapter {
    JFrame frame;

    public PanelMouseListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // 当鼠标进入窗体时，不自动设置透明对
        ThreadAutoSetFrameOpacity.isSetOpacity = false;
        // 不透明
        frame.setOpacity(1.0f);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // 当鼠标离开窗体时，线程检查透明度
        ThreadAutoSetFrameOpacity.isSetOpacity = true;
    }
}
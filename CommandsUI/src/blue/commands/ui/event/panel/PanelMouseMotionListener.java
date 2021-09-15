package blue.commands.ui.event.panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class PanelMouseMotionListener implements MouseMotionListener {
    JFrame frame;
    JPanel panel;

    public PanelMouseMotionListener(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
    }

    private boolean isNearTop = false;
    private boolean isNearBottom = false;
    private boolean isNearLeft = false;
    private boolean isNearRight = false;
    private boolean drag = false;
    // 移动的位置
    private Point draggingAnchor = null;


    // 监听鼠标移动事件
    @Override
    public void mouseMoved(MouseEvent e) {
        // 精度，距离窗体边框多少距离时可以拖动来调整窗体的大小。
        int jingDu = 5;
        // 窗体的顶部带有OCR面板的按钮,减少精度，免得干扰到按钮
        if (Math.abs(e.getPoint().getY() - 0) <= jingDu-2) {
        // if (Math.abs(e.getPoint().getY() - 0) <= jingDu) {
            // 设置拖动光标
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
            isNearTop = true;
            // System.out.println("Cursor is near TOP");
        } else if (Math.abs(e.getPoint().getY() - frame.getSize().getHeight()) <= jingDu) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
            isNearBottom = true;
            // System.out.println("Cursor is near Bottom");
        } else if (Math.abs(e.getPoint().getX() - 0) <= jingDu) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
            isNearLeft = true;
            // System.out.println("Cursor is near left");
        } else if (Math.abs(e.getPoint().getX() - frame.getSize().getWidth()) <= jingDu) {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
            isNearRight = true;
            // System.out.println("Cursor is near right");
        } else {
            frame.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            draggingAnchor = new Point(e.getX() + panel.getX(), e.getY() + panel.getY());
            isNearTop = false;
            isNearBottom = false;
            isNearLeft = false;
            isNearRight = false;
            drag = true;
            // System.out.println("is near center");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // 获取窗体的大小
        Dimension dimension = frame.getSize();
        // 当鼠标指针在顶部的时候
        if (isNearTop) {
            // 设置高度减去鼠标移动后的坐标
            dimension.setSize(dimension.getWidth(), dimension.getHeight() - e.getY());
            // 设置窗体的大小
            frame.setSize(dimension);
            // 移动变大后的窗体到原来的坐标
            frame.setLocation(frame.getLocationOnScreen().x, frame.getLocationOnScreen().y + e.getY());
        }
        // 鼠标指针在底部时
        else if (isNearBottom) {
            dimension.setSize(dimension.getWidth(), e.getY());
            // 设置窗体的大小
            frame.setSize(dimension);
        }
        // 当鼠标指针在左边时
        else if (isNearLeft) {
            dimension.setSize(dimension.getWidth() - e.getX(), dimension.getHeight());
            frame.setSize(dimension);
            // 移动窗体的坐标
            frame.setLocation(frame.getLocationOnScreen().x + e.getX(), frame.getLocationOnScreen().y);
        }
        // 当鼠标指针在右边时
        else if (isNearRight) {
            dimension.setSize(e.getX(), dimension.getHeight());
            // 设置窗体的大小
            frame.setSize(dimension);
        }
        // 当鼠标指针在中间时
        else if (drag) {
            // 移动窗体的位置
            frame.setLocation(e.getLocationOnScreen().x - draggingAnchor.x, e.getLocationOnScreen().y - draggingAnchor.y);
        }
    }
}
package ui;

import javax.swing.*;
import java.awt.event.*;

/**
 * 弹出式菜单演示
 */
class JPopupMenuDemo1 {
    JPopupMenuDemo1() {
        final JFrame f = new JFrame("JPopupMenu 案例1");

        final JPopupMenu popupmenu = new JPopupMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        popupmenu.add(cut);
        popupmenu.add(copy);
        popupmenu.add(paste);

        f.addMouseListener(new MouseAdapter() {
            // 当在窗体上点击鼠标的时候
            public void mouseClicked(MouseEvent e) {
                // 右键点击时
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // 显示弹出菜单
                    popupmenu.show(f, e.getX(), e.getY());
                }
            }
        });
        f.add(popupmenu);
        f.setSize(300, 300);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        new JPopupMenuDemo1();
    }
}
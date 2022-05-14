package ui;

import javax.swing.*;
import java.awt.event.*;

/**
 * 弹出式菜单示例2，菜单项添加事件处理功能。
 */
class JPopupMenuDemo2_Action {
    JPopupMenuDemo2_Action() {
        final JFrame f = new JFrame("JPopupMenu案例-一点教程网");
        final JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(400, 100);
        final JPopupMenu popupmenu = new JPopupMenu("Edit");
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        popupmenu.add(cut);
        popupmenu.add(copy);
        popupmenu.add(paste);
        f.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                // 右键点击时
                if (e.getButton() == MouseEvent.BUTTON3) {
                    // 在窗体 上按下鼠标的坐标上 显示弹出式菜单
                    popupmenu.show(f, e.getX(), e.getY());
                }
            }
        });
        cut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("cut MenuItem clicked.");
            }
        });
        copy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("copy MenuItem clicked.");
            }
        });
        paste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                label.setText("paste MenuItem clicked.");
            }
        });
        f.add(label);
        f.add(popupmenu);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        new JPopupMenuDemo2_Action();
    }
}
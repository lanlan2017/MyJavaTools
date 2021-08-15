package ui.toolbar.buttons;

import ui.ToolsWindow;
import ui.toolbar.ToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * 移动标签
 */
public class MoveLabel {
    private static MoveLabel instance = new MoveLabel();
    JLabel label;
    // 鼠标按下的坐标
    Point mousePressedPoint = new Point();
    private boolean isHideToolBar = true;

    private MoveLabel() {
        label = new JLabel("移动");
        // 监听事件,移动窗体
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mousePressedPoint.x = e.getX();
                mousePressedPoint.y = e.getY();
                // if (isHideToolBar == true) {
                //     ToolBar.getInstance().getToolBar().removeAll();
                //     ToolsWindow.getInstance().pack();
                //     System.out.println("隐藏工具条");
                //     isHideToolBar = false;
                // } else {
                //     ToolBar.getInstance().addToolBarButtons();
                //     ToolsWindow.getInstance().pack();
                //     System.out.println("显示工具条");
                //     isHideToolBar = true;
                // }
                // // String outStr = "";
                // if (e.getButton() == e.BUTTON1) {
                //     // outStr = "左键";
                // } else if (e.getButton() == e.BUTTON3) {
                //     // outStr = "右键";
                // } else {
                //     // outStr = "中键";
                // }
                // if (e.getClickCount() == 2) {
                //     // outStr = outStr + "双击";
                // } else {
                //     // outStr = outStr + "点击";
                // }
                // // System.out.println(outStr);
                // 如果是右键双击的话
                if (e.getButton() == e.BUTTON3 && e.getClickCount() == 2) {
                    if (isHideToolBar == true) {
                        // 移除toolBal中的所有组件
                        ToolBar.getInstance().getToolBar().removeAll();
                        // 把提示文本写到标签中
                        label.setText("右键双击显示");
                        //
                        ToolsWindow.getInstance().pack();
                        System.out.println("隐藏工具条");
                        isHideToolBar = false;
                    } else {
                        // 修改标签的文本
                        label.setText("移动");
                        ToolBar.getInstance().repaintToolBar();
                        System.out.println("显示工具条");
                        isHideToolBar = true;
                    }
                }
            }
        });
        label.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                Point point = ToolsWindow.getInstance().getLocation();
                // 计算位置
                ToolsWindow.getInstance().setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
            }

        });
    }

    public static MoveLabel getInstance() {
        return instance;
    }

    public JLabel getLabel() {
        return label;
    }
}

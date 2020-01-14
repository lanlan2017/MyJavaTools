package ui.toolbar.buttons;

import ui.ToolsWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * @author francis
 * create at 2020/1/13-16:07
 */
public class MoveLabel {
    private static MoveLabel instance=new MoveLabel();
    JLabel label;
    // 鼠标按下的坐标
    Point mousePressedPoint = new Point();
    private MoveLabel(){
        label = new JLabel("移动");
        // 监听事件,移动窗体
        label.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mousePressedPoint.x = e.getX();
                mousePressedPoint.y = e.getY();
            }
        });
        label.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                //Point point = instance.getLocation();
                Point point = ToolsWindow.getInstance().getLocation();
                // 计算位置
                ToolsWindow.getInstance().setLocation(
                        point.x + e.getX() - (int) mousePressedPoint.getX(),
                        point.y + e.getY() - (int) mousePressedPoint.getY());
            }
        });
    }

    public static MoveLabel getInstance() {
        return instance;
    }

    public JLabel getLabel() {
        return label;
    }
    ///**
    // * 在工具条上添加`移动`标签,鼠标个按住该标签来移动按钮。
    // *
    // * @param toolBar 工具条
    // */
    ///**
    // * 在工具条上添加`移动`标签,鼠标个按住该标签来移动按钮。
    // *
    // * @param toolBar 工具条
    // */
    //private void addNotMoveLabel(JToolBar toolBar) {
    //    JLabel label = new JLabel("移动");
    //    // 监听事件,移动窗体
    //    label.addMouseListener(new MouseAdapter() {
    //        public void mousePressed(MouseEvent e) {
    //            mousePressedPoint.x = e.getX();
    //            mousePressedPoint.y = e.getY();
    //        }
    //    });
    //    label.addMouseMotionListener(new MouseMotionAdapter() {
    //        public void mouseDragged(MouseEvent e) {
    //            //Point point = instance.getLocation();
    //            Point point = ToolsWindow.getInstance().getLocation();
    //            // 计算位置
    //            ToolsWindow.getInstance().setLocation(
    //                    point.x + e.getX() - (int) mousePressedPoint.getX(),
    //                    point.y + e.getY() - (int) mousePressedPoint.getY());
    //        }
    //    });
    //    // 设置工具条不可拖动
    //    toolBar.add(label);
    //    toolBar.setFloatable(false);
    //}
}

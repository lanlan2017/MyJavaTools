package blue.ocr2.event.lable;

import com.sun.deploy.config.JREInfo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MoveLableSetting {
    private JFrame frame;
    JLabel moveLable;
    // 鼠标按下的坐标
    Point mousePressedPoint = new Point();

    public MoveLableSetting(JFrame frame, JLabel moveLable) {
        this.frame = frame;
        this.moveLable = moveLable;
        moveLableSetting();
    }

    private void moveLableSetting() {
        moveLable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                mousePressedPoint.x = e.getX();
                mousePressedPoint.y = e.getY();
            }
        });
        moveLable.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                // Point point = ToolsWindow.getInstance().getLocation();
                // Point point = ToolsWindow.getInstance().getLocation();
                Point point = frame.getLocation();
                // 计算位置
                // ToolsWindow.getInstance().setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
                frame.setLocation(point.x + e.getX() - (int) mousePressedPoint.getX(), point.y + e.getY() - (int) mousePressedPoint.getY());
            }
        });
    }
}

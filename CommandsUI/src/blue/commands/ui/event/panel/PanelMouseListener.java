package blue.commands.ui.event.panel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelMouseListener extends MouseAdapter {
    private Frame frame;
    public PanelMouseListener(Frame frame){
        this.frame=frame;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        // 如果按下的是鼠标右键,并且按下两次
        // 也就是右键双击
        if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2) {
            System.out.println("鼠标右键双击面板，最小化窗体");
            // 调整窗体大小
            frame.pack();
        }
    }
}

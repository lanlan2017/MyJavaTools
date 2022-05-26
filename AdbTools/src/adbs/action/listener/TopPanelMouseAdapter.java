package adbs.action.listener;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * 窗体内容面板 鼠标事件监听器
 */
public class TopPanelMouseAdapter extends MouseAdapter {
    private JFrame frame;

    public TopPanelMouseAdapter(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // 当右键双击时
        if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 2) {
            // 调整船体到最佳大小
            frame.pack();
        }
    }
}

package adbs.main.test;

import javax.swing.*;
import java.awt.event.*;

/**
 * 关闭窗体时弹窗询问是否确定关闭
 */
public class CloseConfirmation {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Close Confirmation Example");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(300, 200);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close?");
                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                }
            }
        });
        frame.setVisible(true);
    }
}
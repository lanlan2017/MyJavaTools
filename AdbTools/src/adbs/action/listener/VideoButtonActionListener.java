package adbs.action.listener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoButtonActionListener implements ActionListener {
    // private VideoButtonRunnable videoButtonRunnable;
    private JFrame frame;
    private JPanel inputPanel;
    private JTextField input;
    // private JButton stopButton;

    public VideoButtonActionListener(JFrame frame, JPanel inputPanel, JLabel output) {
        this.frame = frame;
        this.inputPanel = inputPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputPanel.setVisible(true);
        Component[] components = inputPanel.getComponents();
        Component[] newComp = new Component[components.length + 1];
        for (int i = 0; i < components.length; i++) {
            newComp[i] = components[i];
        }
        int count = inputPanel.getComponentCount();
        if (count > 0) {
            // 获取面板最后一个元素
            Component comp = inputPanel.getComponent(count - 1);
            if (comp instanceof JButton) {
                JButton btn = (JButton) comp;
                btn.setText("开始刷视频");
            }
            // 获取面板倒数第3个元素
            comp = inputPanel.getComponent(count - 2);
            if (comp instanceof JTextField) {
                JTextField input2 = (JTextField) comp;
                input2.setColumns(2);
                input2.setText(String.valueOf(14));
                input2.setVisible(true);
            }
            // 获取面板倒数第3个元素
            comp = inputPanel.getComponent(count - 3);
            if (comp instanceof JTextField) {
                JTextField input1 = (JTextField) comp;
                input1.setColumns(2);
                input1.setText(String.valueOf(7));
            }
        }
        frame.pack();
    }
}

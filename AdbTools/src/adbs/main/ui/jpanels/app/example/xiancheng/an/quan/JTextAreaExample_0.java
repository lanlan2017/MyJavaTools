package adbs.main.ui.jpanels.app.example.xiancheng.an.quan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 使用JTextArea的线程安全代码示例。
 * <p>
 * 情况一：在事件调度线程中操作JTextArea
 * <p>
 * <p>
 * 示例中，我们创建了一个包含JTextArea和JButton的JFrame。
 * 当用户点击按钮时，我们使用SwingUtilities.invokeLater()方法将操作放入事件调度线程中执行，
 * 以确保对JTextArea的修改操作是线程安全的。
 */
public class JTextAreaExample_0 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTextArea Example");
        JTextArea textArea = new JTextArea();
        JButton button = new JButton("Update TextArea");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        textArea.setText("Text updated in event dispatch thread.");
                    }
                });
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLayout(new java.awt.FlowLayout());
        frame.add(textArea);
        frame.add(button);
        frame.setVisible(true);
    }
}
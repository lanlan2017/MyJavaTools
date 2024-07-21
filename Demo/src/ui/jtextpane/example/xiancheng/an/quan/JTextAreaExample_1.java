package ui.jtextpane.example.xiancheng.an.quan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 情况二：使用同步块保护JTextArea的访问
 */
public class JTextAreaExample_1 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JTextArea Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        final JTextArea textArea = new JTextArea(20, 30);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);

        ReentrantLock lock = new ReentrantLock();
        JButton button = new JButton("Append Text");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                lock.lock(); // 获取锁  
                try {
                    textArea.append("Appended text\n");
                } finally {
                    lock.unlock(); // 释放锁  
                }
            }
        });
        frame.add(button, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}
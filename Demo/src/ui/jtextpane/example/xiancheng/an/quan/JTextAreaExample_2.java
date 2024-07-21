package ui.jtextpane.example.xiancheng.an.quan;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.ActionEvent;  
import java.awt.event.ActionListener;  
import javax.swing.SwingWorker;

/**
 * 示例中，我们使用SwingWorker类在后台执行耗时任务。
 * 当任务完成后，
 * 我们使用SwingUtilities.invokeLater()方法将更新JTextArea的操作放入事件调度线程中执行，
 * 以确保对JTextArea的修改操作是线程安全的。
 */
public class JTextAreaExample_2 {
    public static void main(String[] args) {  
        JFrame frame = new JFrame("JTextArea Example");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(400, 300);  
        frame.setLayout(new BorderLayout());  
  
        final JTextArea textArea = new JTextArea(20, 30);  
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);  
  
        JButton button = new JButton("Append Text");  
        button.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                new SwingWorker<Void, Void>() {  
                    @Override  
                    protected Void doInBackground() throws Exception {  
                        // 在后台执行耗时任务，更新JTextArea需要在EDT中执行  
                        SwingUtilities.invokeLater(new Runnable() {  
                            public void run() {  
                                textArea.append("Appended text\n");  
                            }  
                        });  
                        return null;  
                    }  
                }.execute();  
            }  
        });  
        frame.add(button, BorderLayout.SOUTH);  
  
        frame.setVisible(true);  
    }  
}
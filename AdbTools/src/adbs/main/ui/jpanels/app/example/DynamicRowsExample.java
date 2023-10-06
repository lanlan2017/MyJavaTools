package adbs.main.ui.jpanels.app.example;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;  
  
public class DynamicRowsExample extends JFrame {  
  
    private JTextArea textArea;  
    private JButton countButton;  
  
    public DynamicRowsExample() {  
        textArea = new JTextArea();  
        textArea.setColumns(20);  
        textArea.setRows(5);  
        textArea.setText("This is a test.\nThis is another test.\nAnd yet another test.");  
        JScrollPane scrollPane = new JScrollPane(textArea);  
  
        countButton = new JButton("Set Rows to Text Lines");  
        countButton.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                int lineCount = textArea.getLineCount();  
                textArea.setRows(lineCount);  
            }  
        });  
  
        JPanel panel = new JPanel();  
        panel.add(countButton);  
  
        getContentPane().add(scrollPane, BorderLayout.CENTER);  
        getContentPane().add(panel, BorderLayout.SOUTH);  
  
        pack();  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setVisible(true);  
    }  
  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                new DynamicRowsExample();  
            }  
        });  
    }  
}
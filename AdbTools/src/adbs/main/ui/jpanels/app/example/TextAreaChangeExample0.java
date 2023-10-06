package adbs.main.ui.jpanels.app.example;

import javax.swing.*;
import javax.swing.event.*;  
import java.awt.*;  
  
public class TextAreaChangeExample0 extends JFrame {
  
    private JTextArea textArea;  
    private JLabel statusLabel;  
  
    public TextAreaChangeExample0() {
        textArea = new JTextArea();  
        textArea.setColumns(20);  
        textArea.setRows(5);  
        textArea.setText("This is a test.\nThis is another test.\nAnd yet another test.");  
        JScrollPane scrollPane = new JScrollPane(textArea);  
  
        statusLabel = new JLabel("Text: unchanged");  
  
        textArea.getDocument().addDocumentListener(new DocumentListener() {  
            public void changedUpdate(DocumentEvent e) {  
                updateStatus("changed");  
            }  
            public void removeUpdate(DocumentEvent e) {  
                updateStatus("removed");  
            }  
            public void insertUpdate(DocumentEvent e) {  
                updateStatus("inserted");  
            }  
            private void updateStatus(String action) {  
                int lineCount = textArea.getLineCount();  
                int charCount = textArea.getText().length();  
                statusLabel.setText("Text: " + action + ", Lines: " + lineCount + ", Chars: " + charCount);  
            }  
        });  
  
        JPanel panel = new JPanel();  
        panel.add(statusLabel);  
  
        getContentPane().add(scrollPane, BorderLayout.CENTER);  
        getContentPane().add(panel, BorderLayout.SOUTH);  
  
        pack();  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setVisible(true);  
    }  
  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(new Runnable() {  
            public void run() {  
                new TextAreaChangeExample0();
            }  
        });  
    }  
}
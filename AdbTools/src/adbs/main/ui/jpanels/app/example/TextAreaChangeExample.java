package adbs.main.ui.jpanels.app.example;

import javax.swing.*;
import javax.swing.event.*;  
import java.awt.*;  
  
public class TextAreaChangeExample extends JFrame {  
  
    private JTextArea textArea;  
    private JLabel statusLabel;  
  
    public TextAreaChangeExample() {  
        textArea = new JTextArea();  
        textArea.setColumns(20);  
        textArea.setRows(5);  
        textArea.setText("This is a test.\nThis is another test.\nAnd yet another test.");  
        JScrollPane scrollPane = new JScrollPane(textArea);  
  
        statusLabel = new JLabel("Text: unchanged");  
  
        textArea.getDocument().addDocumentListener(new DocumentListener() {  
            public void changedUpdate(DocumentEvent e) {  
                updateStatus(e, "changed");  
            }  
            public void removeUpdate(DocumentEvent e) {  
                updateStatus(e, "removed");  
            }  
            public void insertUpdate(DocumentEvent e) {  
                updateStatus(e, "inserted");  
            }  
            private void updateStatus(DocumentEvent e, String action) {  
                // JTextArea sourceTextArea = (JTextArea)e.getDocument().getRenderer();
                JTextArea sourceTextArea = (JTextArea)e.getDocument();
                // e.get
                int lineCount = sourceTextArea.getLineCount();
                int charCount = sourceTextArea.getText().length();  
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
                new TextAreaChangeExample();  
            }  
        });  
    }  
}
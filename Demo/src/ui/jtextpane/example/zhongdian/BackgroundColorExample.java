package ui.jtextpane.example.zhongdian;

import javax.swing.*;

public class BackgroundColorExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Text Area with Background Color");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JEditorPane editorPane = new JEditorPane();
            editorPane.setContentType("text/html");
            editorPane.setEditable(false); // Set to true if you want it editable
            editorPane.setText("<html><body>" +
                    "<p>First line</p>" +
                    "<p style='background-color: yellow;'>Second line with yellow background</p>" +
                    "<p>Third line</p>" +
                    "</body></html>");
            
            JScrollPane scrollPane = new JScrollPane(editorPane);
            frame.add(scrollPane);
            frame.setSize(300, 200);
            frame.setVisible(true);
        });
    }
}
package ui.jtextpane.example.zhongdian;

import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;

public class TextPaneBackgroundColor {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("JTextPane with Background Color");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTextPane textPane = new JTextPane();
            StyledDocument doc = textPane.getStyledDocument();

            // Create a SimpleAttributeSet for styling
            SimpleAttributeSet set = new SimpleAttributeSet();
            StyleConstants.setBackground(set, Color.YELLOW);

            try {
                // Insert the first line without any special styling
                doc.insertString(doc.getLength(), "First line\n", null);
                // Insert the second line with the background color set
                doc.insertString(doc.getLength(), "Second line with yellow background\n", set);
                // Insert the third line without any special styling
                doc.insertString(doc.getLength(), "Third line\n", null);
            } catch (BadLocationException e) {
                e.printStackTrace();
            }

            JScrollPane scrollPane = new JScrollPane(textPane);
            frame.add(scrollPane);
            frame.setSize(300, 200);
            frame.setVisible(true);
        });
    }
}
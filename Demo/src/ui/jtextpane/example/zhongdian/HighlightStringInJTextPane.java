package ui.jtextpane.example.zhongdian;

import javax.swing.text.*;
import javax.swing.*;
import java.awt.*;
import java.util.regex.*;

public class HighlightStringInJTextPane {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JTextPane textPane = new JTextPane();
            JScrollPane scrollPane = new JScrollPane(textPane);
            JFrame frame = new JFrame("Highlight Text");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(scrollPane);
            frame.setSize(400, 300);
            frame.setVisible(true);

            String text = "This is a sample text with some words to highlight. The first word to highlight will be set with a pink background.";
            textPane.setText(text);

            // 设置样式
            SimpleAttributeSet attr = new SimpleAttributeSet();
            StyleConstants.setBackground(attr, Color.pink);

            // 查找并高亮显示第一个匹配的字符串
            try {
                Document doc = textPane.getDocument();
                String searchString = "highlight"; // 指定要查找的字符串
                Pattern pattern = Pattern.compile(searchString);
                Matcher matcher = pattern.matcher(doc.getText(0, doc.getLength()));
                if (matcher.find()) {
                    int start = matcher.start();
                    int end = matcher.end();
                    Highlighter hilite = textPane.getHighlighter();
                    hilite.addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(Color.pink));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
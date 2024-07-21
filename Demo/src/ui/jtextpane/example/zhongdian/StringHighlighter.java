package ui.jtextpane.example.zhongdian;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StringHighlighter extends JFrame {

    private JTextPane textPane;
    private JButton confirmButton;
    private JButton cancelButton;
    private Highlighter highlighter;

    public StringHighlighter() {
        super("Highlight String in JTextPane");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        createUI();
        setVisible(true);
    }

    private void createUI() {
        JPanel panel = new JPanel(new BorderLayout());
        getContentPane().add(panel);

        // 创建JTextPane
        textPane = new JTextPane();
        JScrollPane scrollPane = new JScrollPane(textPane);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 初始化Highlighter
        highlighter = textPane.getHighlighter();

        // 创建按钮面板
        JPanel buttonPanel = new JPanel();
        panel.add(buttonPanel, BorderLayout.SOUTH);

        // 创建按钮
        confirmButton = new JButton("确定");
        cancelButton = new JButton("取消");

        // 添加按钮到面板
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);

        // 设置文本
        textPane.setText("这里是一段文本，我们要找到并高亮显示的单词是 '高亮'。");

        // 添加按钮监听器
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                highlightString("高亮", Color.pink);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeHighlights();
            }
        });
    }

    private void highlightString(String searchString, Color color) {
        try {
            Document doc = textPane.getDocument();
            String text = doc.getText(0, doc.getLength());
            int index = text.indexOf(searchString);
            if (index != -1) {
                int end = index + searchString.length();
                highlighter.removeAllHighlights(); // 清除旧的高亮
                highlighter.addHighlight(index, end, new DefaultHighlighter.DefaultHighlightPainter(color));
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private void removeHighlights() {
        highlighter.removeAllHighlights();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StringHighlighter());
    }
}
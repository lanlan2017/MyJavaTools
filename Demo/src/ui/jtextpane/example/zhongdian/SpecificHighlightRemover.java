package ui.jtextpane.example.zhongdian;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class SpecificHighlightRemover extends JFrame {

    private JTextPane textPane;
    private JTextField inputField;
    private JButton confirmButton;
    private JButton cancelButton;
    private Highlighter highlighter;
    private List<Highlighter.Highlight> highlights = new ArrayList<>();

    public SpecificHighlightRemover() {
        super("Highlight and Remove Specific String");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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

        // 创建输入字段和按钮面板
        JPanel controlPanel = new JPanel();
        panel.add(controlPanel, BorderLayout.SOUTH);

        // 创建输入字段
        inputField = new JTextField(20);
        controlPanel.add(inputField);

        // 创建按钮
        confirmButton = new JButton("确定");
        cancelButton = new JButton("取消");

        // 添加按钮到面板
        controlPanel.add(confirmButton);
        controlPanel.add(cancelButton);

        // 设置文本
        textPane.setText("这里是一段文本，我们将根据输入框的内容来高亮显示相应的单词。");

        // 添加按钮监听器
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchString = inputField.getText();
                highlightString(searchString, Color.pink);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSpecificHighlights();
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
                Highlighter.Highlight highlight = (Highlighter.Highlight) highlighter.addHighlight(index, end, new DefaultHighlighter.DefaultHighlightPainter(color));
                highlights.add(highlight); // 记录高亮显示对象
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    private void removeSpecificHighlights() {
        String currentSearchString = inputField.getText();
        try {
            Document doc = textPane.getDocument();
            String text = doc.getText(0, doc.getLength());
            int index = text.indexOf(currentSearchString);
            if (index != -1) {
                int end = index + currentSearchString.length();
                for (Highlighter.Highlight h : highlights) {
                    if (h.getStartOffset() == index && h.getEndOffset() == end) {
                        highlighter.removeHighlight(h);
                        highlights.remove(h); // 移除记录的高亮显示对象
                    }
                }
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SpecificHighlightRemover());
    }
}
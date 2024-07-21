package ui.jtextpane.example.zhongdian;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Document;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MultipleHighlighter extends JFrame {

    private JTextPane textPane;
    private JTextField inputField;
    private JButton confirmButton;
    private JButton cancelButton;
    private Highlighter highlighter;
    private List<Highlighter.Highlight> highlights = new ArrayList<>();

    public MultipleHighlighter() {
        super("Multiple Highlights");
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
            while (index != -1) {
                int end = index + searchString.length();
                Highlighter.Highlight highlight = (Highlighter.Highlight) highlighter.addHighlight(index, end, new DefaultHighlighter.DefaultHighlightPainter(color));
//                Highlighter.Highlight highlight =  highlighter.addHighlight(index, end, new DefaultHighlighter.DefaultHighlightPainter(color));
                highlights.add(highlight); // 记录高亮显示对象
                index = text.indexOf(searchString, end);
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

//    private void removeSpecificHighlights() {
//        String currentSearchString = inputField.getText();
//        try {
//            Document doc = textPane.getDocument();
//            String text = doc.getText(0, doc.getLength());
//            int index = text.indexOf(currentSearchString);
//            while (index != -1) {
//                int end = index + currentSearchString.length();
//                for (Highlighter.Highlight h : highlights) {
//                    if (h.getStartOffset() == index && h.getEndOffset() == end) {
//                        highlighter.removeHighlight(h);
//                        highlights.remove(h); // 移除记录的高亮显示对象
//                    }
//                }
//                index = text.indexOf(currentSearchString, end);
//            }
//        } catch (BadLocationException ex) {
//            ex.printStackTrace();
//        }
//    }

    private void removeSpecificHighlights() {
        String currentSearchString = inputField.getText();
        try {
            Document doc = textPane.getDocument();
            String text = doc.getText(0, doc.getLength());
            int index = text.indexOf(currentSearchString);
            while (index != -1) {
                int end = index + currentSearchString.length();
                Iterator<Highlighter.Highlight> iterator = highlights.iterator();
                while (iterator.hasNext()) {
                    Highlighter.Highlight h = iterator.next();
                    if (h.getStartOffset() == index && h.getEndOffset() == end) {
                        highlighter.removeHighlight(h);
                        iterator.remove(); // 使用迭代器的remove方法
                    }
                }
                index = text.indexOf(currentSearchString, end);
            }
        } catch (BadLocationException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MultipleHighlighter());
    }
}
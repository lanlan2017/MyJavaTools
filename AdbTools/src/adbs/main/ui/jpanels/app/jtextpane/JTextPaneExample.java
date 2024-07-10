package adbs.main.ui.jpanels.app.jtextpane;

import javax.swing.*;
import javax.swing.text.*;  
import java.awt.*;  
  
public class JTextPaneExample extends JFrame {  
    public JTextPaneExample() {  
        // 创建 JTextPane  
        JTextPane textPane = new JTextPane();  
        textPane.setEditable(true);  
  
        // 创建一个简单的 HTML 文档  
        String html = "<html><body><h1>标题</h1><p>这是一个<b>加粗</b>的文本，以及一个<i>斜体</i>的文本。</p></body></html>";  
        textPane.setContentType("text/html");  
        textPane.setText(html);  
  
        // 或者，使用 StyledDocument 设置样式（不使用 HTML）  
        // StyledDocument doc = textPane.getStyledDocument();  
        // Style style = textPane.addStyle("StyleName", null);  
        // StyleConstants.setBold(style, true);  
        // textPane.setCharacterAttributes(style, true);  
  
        // 将 JTextPane 添加到 JFrame  
        add(new JScrollPane(textPane), BorderLayout.CENTER);  
  
        // 设置窗口属性  
        setTitle("JTextPane 示例");  
        setSize(400, 300);  
        setDefaultCloseOperation(EXIT_ON_CLOSE);  
        setLocationRelativeTo(null); // 窗口居中  
    }  
  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> {  
            JTextPaneExample frame = new JTextPaneExample();  
            frame.setVisible(true);  
        });  
    }  
}
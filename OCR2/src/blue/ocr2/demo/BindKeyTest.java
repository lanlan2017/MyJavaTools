package blue.ocr2.demo;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class BindKeyTest {
    JFrame jFrame = new JFrame("测试键盘绑定");
    JTextArea jTextArea = new JTextArea(5, 30);
    JButton jButton = new JButton("发送");
    JTextField jTextField = new JTextField(15);

    public void init() {
        jFrame.add(jTextArea);
        JPanel jp = new JPanel();
        jp.add(jTextField);
        jp.add(jButton);
        jFrame.add(jp, BorderLayout.SOUTH);
        // 发送消息的Action,Action是ActionListener的子接口
        Action sendMsg = new AbstractAction() {
            private static final long serialVersionUID = 2625520225836946219L;

            public void actionPerformed(ActionEvent e) {
                jTextArea.append(jTextField.getText() + "\n");
                jTextField.setText("");
            }
        };
        // 添加事件监听器
        jButton.addActionListener(sendMsg);

        // 将sendMsg Action关联到key "send"上面
        jTextField.getActionMap().put("send", sendMsg);
        // 将Ctrl+Enter键和"send"关联
        jTextField.getInputMap().put(KeyStroke.getKeyStroke('\n', java.awt.event.InputEvent.CTRL_DOWN_MASK), "send");

        jFrame.pack();
        jFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new BindKeyTest().init();
    }
}
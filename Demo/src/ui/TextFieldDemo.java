package ui;

import javax.swing.*;
import java.awt.event.*;

public class TextFieldDemo implements ActionListener {
    JTextField textField1, textField2, textField3;
    JButton add, minus, mul, div;

    TextFieldDemo() {
        JFrame f = new JFrame();
        textField1 = new JTextField();
        textField1.setBounds(50, 50, 150, 20);
        textField2 = new JTextField();
        textField2.setBounds(50, 100, 150, 20);
        textField3 = new JTextField();
        textField3.setBounds(50, 150, 150, 20);
        textField3.setEditable(false);
        add = new JButton("+");
        setBounds(30, 200, add);
        minus = new JButton("-");
        setBounds(80, 200, minus);
        mul = new JButton("*");
        setBounds(130, 200, mul);
        div = new JButton("/");
        setBounds(180, 200, div);

        // 添加事件监听器
        add.addActionListener(this);
        minus.addActionListener(this);
        mul.addActionListener(this);
        div.addActionListener(this);

        f.add(textField1);
        f.add(textField2);
        f.add(textField3);
        f.add(add);
        f.add(minus);
        f.add(mul);
        f.add(div);
        f.setSize(300, 300);
        f.setLayout(null);
        f.setVisible(true);
    }

    private void setBounds(int x, int y, JButton button) {
        int width = (int) button.getPreferredSize().getWidth();
        int height = (int) button.getPreferredSize().getHeight();
        button.setBounds(x, y, width, height);
    }

    public void actionPerformed(ActionEvent e) {
        String s1 = textField1.getText();
        String s2 = textField2.getText();
        int a = Integer.parseInt(s1);
        int b = Integer.parseInt(s2);
        int c = 0;
        if (e.getSource() == add) {
            c = a + b;
        } else if (e.getSource() == minus) {
            c = a - b;
        } else if (e.getSource() == mul) {
            c = a * b;
        } else if (e.getSource() == div) {
            c = a / b;
        }

        String result = String.valueOf(c);
        textField3.setText(result);
    }

    public static void main(String[] args) {
        new TextFieldDemo();
    }
}
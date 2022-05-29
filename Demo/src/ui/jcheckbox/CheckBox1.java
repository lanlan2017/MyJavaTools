package ui.jcheckbox;

import javax.swing.*;

public class CheckBox1 {
    CheckBox1() {
        JFrame f = new JFrame("JCheckBox案例");
        JCheckBox checkBox1 = new JCheckBox("C++");
        checkBox1.setBounds(100, 100, 100, 100);
        JCheckBox checkBox2 = new JCheckBox("Java", true);
        checkBox2.setBounds(100, 200, 100, 100);
        f.add(checkBox1);
        f.add(checkBox2);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        new CheckBox1();
    }
}
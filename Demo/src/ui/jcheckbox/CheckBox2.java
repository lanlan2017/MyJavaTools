package ui.jcheckbox;

import javax.swing.*;
import java.awt.event.*;

public class CheckBox2 {
    CheckBox2() {
        JFrame f = new JFrame("JCheckBox案例");
        final JLabel label = new JLabel();
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setSize(400, 100);
        JCheckBox checkbox1 = new JCheckBox("C++");
        checkbox1.setBounds(150, 100, 80, 80);
        JCheckBox checkbox2 = new JCheckBox("Java");
        checkbox2.setBounds(150, 150, 80, 80);
        f.add(checkbox1);
        f.add(checkbox2);
        f.add(label);
        checkbox1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int stateChange = e.getStateChange();
                System.out.println("stateChange = " + stateChange);
                label.setText("C++ Checkbox: " + (stateChange == ItemEvent.SELECTED ? "checked" : "unchecked"));
            }
        });
        checkbox2.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                int stateChange = e.getStateChange();
                System.out.println("stateChange = " + stateChange);
                label.setText("Java Checkbox: " + (stateChange == ItemEvent.SELECTED ? "checked" : "unchecked"));
            }
        });
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        new CheckBox2();
    }
}
package adbs.main.ui.jpanels.timeauto2.example;

import javax.swing.*;
import java.awt.*;  
import java.awt.event.*;  
  
public class RadioButtonExample extends JFrame implements ActionListener {  
  
    private JRadioButton radioButton1;  
    private JRadioButton radioButton2;  
    private JRadioButton radioButton3;  
    private ButtonGroup buttonGroup;  
  
    public RadioButtonExample() {  
        super("JRadioButton 示例");  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        setSize(300, 200);  
        setLayout(new FlowLayout());  
  
        radioButton1 = new JRadioButton("选项 1");  
        radioButton2 = new JRadioButton("选项 2");  
        radioButton3 = new JRadioButton("选项 3");  
  
        buttonGroup = new ButtonGroup();  
        buttonGroup.add(radioButton1);  
        buttonGroup.add(radioButton2);  
        buttonGroup.add(radioButton3);  
  
        radioButton1.addActionListener(this);  
        radioButton2.addActionListener(this);  
        radioButton3.addActionListener(this);  
  
        add(radioButton1);  
        add(radioButton2);  
        add(radioButton3);  
    }  
  
    public void actionPerformed(ActionEvent e) {  
        JRadioButton selectedButton = (JRadioButton) e.getSource();  
        System.out.println("用户选择了：" + selectedButton.getText());  
    }  
  
    public static void main(String[] args) {  
        RadioButtonExample example = new RadioButtonExample();  
        example.setVisible(true);  
    }  
}
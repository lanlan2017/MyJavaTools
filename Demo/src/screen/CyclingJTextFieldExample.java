package screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  
  
public class CyclingJTextFieldExample {  
  
    private static final int MAX_VALUE = 5;  
    private int currentValue = 0;  
  
    public static void main(String[] args) {  
        SwingUtilities.invokeLater(() -> {  
            new CyclingJTextFieldExample().createAndShowGUI();  
        });  
    }  
  
    private void createAndShowGUI() {  
        JFrame frame = new JFrame("Cycling JTextField Example");  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setSize(300, 200);  
  
        final JButton textField = new JButton(String.valueOf(currentValue));
//        textField.setColumns(2);
//        textField.setEditable(false); // 设置为非可编辑，以防止用户直接输入
  
        textField.addActionListener(new ActionListener() {  
            @Override  
            public void actionPerformed(ActionEvent e) {  
                currentValue = (currentValue + 1) % (MAX_VALUE + 1); // 更新值并循环  
                textField.setText(String.valueOf(currentValue));  
            }  
        });  
  
        frame.add(textField, BorderLayout.NORTH);
        frame.setVisible(true);  
    }  
}
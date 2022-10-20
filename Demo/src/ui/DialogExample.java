package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogExample {
    private static JDialog dialog;

    DialogExample() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        dialog = new JDialog(frame, "JDialog案例-一点教程网", true);
        dialog.setLayout(new FlowLayout());
        JButton b = new JButton("OK");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DialogExample.dialog.setVisible(false);
            }
        });
        dialog.add(new JLabel("Click button to continue."));
        dialog.add(b);
        dialog.setSize(300, 300);
        dialog.setVisible(true);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new DialogExample();
    }
} 
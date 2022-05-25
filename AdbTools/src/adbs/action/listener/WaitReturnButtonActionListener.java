package adbs.action.listener;

import adbs.action.runnable.WaitReturnButtonRunnable;
import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioBtAcListener;
import tools.thead.Threads;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitReturnButtonActionListener implements ActionListener {
    JFrame frame;
    JPanel inputPanel;
    JTextField input;
    JButton inputOkButton;

    public WaitReturnButtonActionListener(JFrame frame, JPanel inputPanel, JTextField input, JButton inputOkButton) {
        this.frame = frame;
        this.inputPanel = inputPanel;
        this.input = input;
        this.inputOkButton = inputOkButton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputPanel.setVisible(true);
        input.setText(String.valueOf(35));
        inputOkButton.setText("开始等待");
        frame.pack();
    }
}

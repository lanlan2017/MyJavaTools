package adbs.action.listener;

import adbs.action.runnable.VideoButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoButtonActionListener implements ActionListener {
    private VideoButtonRunnable runnable;

    public VideoButtonActionListener(JLabel output) {
        runnable = new VideoButtonRunnable(output);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Thread thread = new Thread(runnable);
        thread.start();
        // AdbCommands.runAbdCmd("adb -s 192.168.10.4:5555 shell input swipe 8 1650 8 700 300");
    }
}

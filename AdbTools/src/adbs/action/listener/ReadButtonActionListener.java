package adbs.action.listener;

import adbs.action.runnable.ReadButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadButtonActionListener implements ActionListener {
    private final ReadButtonRunnable target;

    public ReadButtonActionListener(JLabel output) {
        this.target = new ReadButtonRunnable(output);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ReadButtonRunnable.setStop(false);
        Thread thread = new Thread(target);
        thread.start();
    }
}

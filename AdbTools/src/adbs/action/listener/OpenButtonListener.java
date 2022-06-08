package adbs.action.listener;

import adbs.action.runnable.OpenButtonRunnable;
import adbs.buttons.JButtons;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenButtonListener implements ActionListener {
    private OpenButtonRunnable target;

    public OpenButtonListener() {
        target = new OpenButtonRunnable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButtons.setFocusPainted(e);
        new Thread(target).start();
    }
}

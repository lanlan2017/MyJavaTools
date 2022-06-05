package adbs.action.listener;

import adbs.action.runnable.OpenButtonRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenButtonListener implements ActionListener {
    private OpenButtonRunnable target;
    // private JFrame frame;
    //
    // public OpenButtonListener(JFrame frame) {
    //     target = new OpenButtonRunnable();
    //     this.frame = frame;
    // }
    public OpenButtonListener()
    {
        target = new OpenButtonRunnable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // if (e.getSource() instanceof JRadioButton) {
        //     System.out.println("是单按钮");
        //     JRadioButton select = (JRadioButton) e.getSource();
        //     frame.setTitle(select.getText());
        // }
        new Thread(target).start();
    }
}

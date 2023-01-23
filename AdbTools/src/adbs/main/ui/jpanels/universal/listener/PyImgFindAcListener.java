package adbs.main.ui.jpanels.universal.listener;

import adbs.main.ui.inout.InOutputModel;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PyImgFindAcListener implements ActionListener {
    private CloseableRunnable closeableRunnable;

    public PyImgFindAcListener(CloseableRunnable closeableRunnable, InOutputModel inOutputModel) {
        this.closeableRunnable = closeableRunnable;
        closeableRunnable.setInOutputModel(inOutputModel);
    }
    public PyImgFindAcListener(CloseableRunnable closeableRunnable) {
        this.closeableRunnable = closeableRunnable;

        // this.closeableRunnable.setOutput2(output2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(closeableRunnable).start();
    }
}

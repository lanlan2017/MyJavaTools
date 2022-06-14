package adbs.action.listener;

import adbs.action.model.InOutputModel;
import adbs.action.runnable.abs.CloseableRunnable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PyImgFindAcListener implements ActionListener {
    private CloseableRunnable closeableRunnable;

    public PyImgFindAcListener(CloseableRunnable closeableRunnable, InOutputModel inOutputModel) {
        this.closeableRunnable = closeableRunnable;
        closeableRunnable.setInOutputModel(inOutputModel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(closeableRunnable).start();
    }
}

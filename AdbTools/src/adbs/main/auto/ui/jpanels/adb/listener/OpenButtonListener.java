package adbs.main.auto.ui.jpanels.adb.listener;

import adbs.action.runnable.open.OpenButtonRunnable;

import java.awt.event.ActionEvent;

public class OpenButtonListener extends ButtonFocusReleaseActionListener {
    private OpenButtonRunnable openButtonRunnable;

    public OpenButtonListener() {
        openButtonRunnable = new OpenButtonRunnable();
    }

    @Override
    protected void actionEvent(ActionEvent e) {
        new Thread(openButtonRunnable).start();
    }
}

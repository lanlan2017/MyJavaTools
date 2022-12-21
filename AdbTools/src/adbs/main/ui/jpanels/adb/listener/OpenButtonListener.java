package adbs.main.ui.jpanels.adb.listener;

import adbs.main.ui.jpanels.adb.open.OpenButtonRunnable;

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

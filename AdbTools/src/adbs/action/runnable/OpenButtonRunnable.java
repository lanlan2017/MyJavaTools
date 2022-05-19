package adbs.action.runnable;

import adbs.cmd.AdbCommands;
import adbs.test.DeviceRadioButtonActionListener;
import adbs.ui.AdbTools;

public class OpenButtonRunnable implements Runnable {

    @Override
    public void run() {
        AdbTools.setIsRunning(this);
        String id = DeviceRadioButtonActionListener.getId();
        if (id != null && !"".equals(id)) {
            AdbCommands.runAbdCmd("scrcpy.exe -s " + id + " --turn-screen-off --stay-awake -m 768 --window-title 182 --always-on-top");
        }
    }
}

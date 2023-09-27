package adbs.main.ui.jpanels.auto.runnable;

import adbs.main.AdbTools;
import adbs.model.Device;

public class QieZiReadCoinCloseRun extends DefaultNewWebActivityCloseRunnable {

    private static QieZiReadCoinCloseRun instance;

    // public QieZiReadCoinCloseRun(Device device) {
    //     // this.device = device;
    //     setDevice(device);
    // }

    public static QieZiReadCoinCloseRun getInstance() {
        if (instance == null) {
            instance = new QieZiReadCoinCloseRun();
        }
        return instance;
    }

    // @Override
    // public void setBtnClose() {
    //     btnClose = Ratios.qieZiBtnClose;
    //     // setBtnClose(btnClose1);
    // }
    //
    // @Override
    // public void setBtnCoin() {
    //     // btnCoin = new ScreenPositionRatio(0.8388888888888889, 0.6197916666666666);
    //     btnCoin = Ratios.qieZiBtnReadCoin;
    // }
}

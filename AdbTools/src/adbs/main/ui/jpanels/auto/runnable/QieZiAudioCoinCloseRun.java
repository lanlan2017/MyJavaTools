package adbs.main.ui.jpanels.auto.runnable;

public class QieZiAudioCoinCloseRun extends DefaultNewWebActivityCloseRunnable {

    private static QieZiAudioCoinCloseRun instance;

    private QieZiAudioCoinCloseRun() {
        // // this.device = device;
        btnClose = Ratios.qieZiBtnClose;
        btnCoin = Ratios.qieZiAudioCoin;
        // setDevice(device);
    }

    public static QieZiAudioCoinCloseRun getInstance() {
        if (instance == null) {
            // instance = new QieZiAudioCoinCloseRun(AdbTools.getInstance().getDevice());
            instance = new QieZiAudioCoinCloseRun();
        }
        return instance;
    }
    //
    // @Override
    // public void setBtnClose() {
    //
    //     btnClose = Ratios.qieZiBtnClose;
    //     // setBtnClose(btnClose1);
    // }

    // @Override
    // public void setBtnCoin() {
    //     // btnCoin = new ScreenPositionRatio(0.8388888888888889, 0.6197916666666666);
    //     // btnCoin = Ratios.qieZiBtnReadCoin;
    //     btnCoin = Ratios.qieZiBtnAudioCoin;
    //
    // }
}

//package adbs.main.ui.jpanels.auto.runnable;
//
//import adbs.cmd.AdbCommands;
//import adbs.main.run.AdbGetPackage;
//import adbs.main.run.model.AppNames;
//import adbs.main.ui.jpanels.auto.AdbTap;
//import adbs.main.ui.jpanels.auto.ScreenPositionRatio;
//import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
//import adbs.model.Device;
//import adbs.tools.thread.ThreadSleep;
//
//public abstract class DefaultNewWebActivityCloseRunnable extends CloseableRunnable {
//    private Device device;
//
//    /**
//     * 关闭按钮 的 比率位置
//     */
//    protected ScreenPositionRatio btnClose;
//    /**
//     * 金币按钮 的 比率位置
//     */
//    protected ScreenPositionRatio btnCoin;
//
//
//    // @Override
//    // protected void setMsg() {
//    //     msg = "";
//    // }
//
//    public void setDevice(Device device) {
//        this.device = device;
//    }
//
//    public void setBtnClose(ScreenPositionRatio btnClose) {
//        this.btnClose = btnClose;
//    }
//
//    public void setBtnCoin(ScreenPositionRatio btnCoin) {
//        this.btnCoin = btnCoin;
//    }
//
//    @Override
//    protected void loopBody() {
//        System.out.println("xxxxxx device = " + device);
//        System.out.println("xxxxxx btnClose = " + btnClose);
//        System.out.println("xxxxxx btnCoin = " + btnCoin);
//        // adbTap_Wait(device, btnCloseQieZi);
//        // adbTap_Wait(device, readGoldCoin);
//
//        // String actName = AdbGetPackage.getActName();
//        // int endIndex = actName.indexOf("/");
//        // String actShortName = actName.substring(endIndex + 1);
//        // String packageName = actName.substring(0, endIndex);
//        // System.out.println("packageName = " + packageName);
//        // System.out.println("actShortName = " + actShortName);
//
//        AppNames appNames = AdbGetPackage.getAppNames();
//        System.out.println(appNames);
//        String actShortName = appNames.getActShortName();
//        switch (actShortName) {
//            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
//                adbTap_Wait(device, btnClose);
//                break;
//            case "":
//                break;
//            default:
//                AdbCommands.returnBtn(device);
//                break;
//        }
//        // 点击关闭按钮
//        // adbTap_Wait(device, btnClose);
//        if (stop) {
//            return;
//        }
//        // 点击金币按钮
//        adbTap_Wait(device, btnCoin);
//    }
//
//    private void adbTap_Wait(Device device, ScreenPositionRatio ratio) {
//        if (device != null) {
//            // 点击设备上的指定位置
//            AdbTap.tap(device, ratio);
//            // 等待3秒
//            ThreadSleep.seconds(3);
//        } else {
//            System.out.println("device = " + device);
//        }
//
//
//    }
//}

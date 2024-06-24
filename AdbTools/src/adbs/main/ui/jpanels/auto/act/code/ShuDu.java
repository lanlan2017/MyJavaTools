//package adbs.main.ui.jpanels.auto.act.code;
//
//import adbs.main.AdbTools;
//import adbs.main.ui.jpanels.auto.AdbTap;
//import adbs.main.ui.jpanels.auto.CoinsType;
//import adbs.main.ui.jpanels.auto.act.ActivityRun;
//import adbs.main.ui.jpanels.auto.act.WeiZhi;
//import adbs.model.Device;
//
//import javax.swing.*;
//
///**
// * 速读免费小说要执行的操作
// */
//public class ShuDu implements CoinsType {
//    static ActivityRun activityRun;
//    static boolean isAdjustedPosition;
//
//    /**
//     * 番茄畅听金币自动收取功能
//     *
//     * @param device
//     * @param packageName
//     * @param actShortName
//     */
//    public static void shuDuJinBi(ActivityRun activityRun, String coinType, Device device, String packageName, String actShortName) {
//        ShuDu.activityRun = activityRun;
//        // 如果是首次执行
//        if (!isAdjustedPosition) {
//            // JOptionPane.showMessageDialog(AdbTools.getInstance().getContentPane(), "请将去浏览按钮移动到开箱");
//            JPanel contentPane = AdbTools.getInstance().getContentPane();
//            String message = "把\"去浏览\"按钮移动到宝箱底部";
//            String title = "滑动屏幕";
//            int messageType = JOptionPane.PLAIN_MESSAGE;
//
//            int result = JOptionPane.showConfirmDialog(contentPane, message, title, messageType);
//            switch (result) {
//                case JOptionPane.YES_OPTION:
//                    // shuDuJinBi_(coinType, device, packageName, actShortName);
//                    shuDuJinBi_(coinType, device, actShortName);
//                    isAdjustedPosition = true;
//                    break;
//            }
//        } else {
//
//            shuDuJinBi_(coinType, device, actShortName);
//        }
//    }
//
//    private static void shuDuJinBi_(String coinType, Device device, String actShortName) {
//        System.out.println("速读免费小说");
//        switch (coinType) {
//            case strShuDuTingShu:
//                if (actShortName.equals("com.zhangyue.iReader.ui.activity.ActivityContainer")) {
//                    WeiZhi shuDuTingShu = new WeiZhi(890, 1463);
//                    // AdbTap.wait_tap(device, 5, shuDuTingShu);
//                    AdbTap.wait_tap(device, s3, shuDuTingShu);
//
//                    kanShiPinZuiGaoLing(device);
//                } else {
//                    guangGao(device, actShortName);
//                }
//
//                break;
//            case strShuDuYueDu:
//
//                if (actShortName.equals("com.zhangyue.iReader.ui.activity.ActivityContainer")) {
//
//                    WeiZhi yueDuJinBi = new WeiZhi(892, 900);
//                    // AdbTap.wait_tap(device, s5, yueDuJinBi);
//                    AdbTap.wait_tap(device, s3, yueDuJinBi);
//
//                    kanShiPinZuiGaoLing(device);
//                } else {
//                    guangGao(device, actShortName);
//                }
//                break;
//        }
//    }
//
//    private static void kanShiPinZuiGaoLing(Device device) {
//        WeiZhi kanShiPinZuiGaoLing = new WeiZhi(568, 1231);
//        // AdbTap.wait_tap(device, 5, kanShiPinZuiGaoLing);
//        AdbTap.wait_tap(device, s3, kanShiPinZuiGaoLing);
//    }
//
//    /**
//     * 在广告activity界面执行的操作
//     *
//     * @param device
//     * @param actShortName
//     */
//    private static void guangGao(Device device, String actShortName) {
//        switch (actShortName) {
//            case "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity":
//
//                WeiZhi closeBtn = new WeiZhi(987, 104);
//                AdbTap.wait_tap(device, s60, closeBtn);
//
//                break;
//            case "com.qq.e.ads.PortraitADActivity":
//                // WeiZhi closeBtn1 = new WeiZhi(981, 171);
//                WeiZhi closeBtn1 = new WeiZhi(983, 167);
//                AdbTap.wait_tap(device, s60, closeBtn1);
//                break;
//
//            default:
//
//                activityRun.stop(strShuDuTingShu);
//                break;
//        }
//    }
//
//}

package adbs.main.ui.jpanels.auto.act.code;

import adbs.main.AdbTools;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.auto.CoinsType;
import adbs.main.ui.jpanels.auto.act.ActDo;
import adbs.main.ui.jpanels.auto.act.WeiZhi;
import adbs.model.Device;

import javax.swing.*;

public class ShuDu implements CoinsType {
    static ActDo actDo;
    static boolean isAdjustedPosition;

    // public ShuDu(ActDo actDo) {
    //     this.actDo = actDo;
    // }


    /**
     * 番茄畅听金币自动收取功能
     *
     * @param device
     * @param packageName
     * @param actShortName
     */
    public static void shuDuJinBi(ActDo actDo, String coinType, Device device, String packageName, String actShortName) {
        ShuDu.actDo = actDo;
        if (!isAdjustedPosition) {

            // JOptionPane.showMessageDialog(AdbTools.getInstance().getContentPane(), "请将去浏览按钮移动到开箱");
            JPanel contentPane = AdbTools.getInstance().getContentPane();
            String message = "把\"去浏览\"按钮移动到宝箱底部";
            String title = "滑动屏幕";
            int messageType = JOptionPane.PLAIN_MESSAGE;

            int result = JOptionPane.showConfirmDialog(contentPane, message, title, messageType);
            switch (result) {
                case JOptionPane.YES_OPTION:
                    // shuDuJinBi_(coinType, device, packageName, actShortName);
                    shuDuJinBi_(coinType, device, actShortName);
                    isAdjustedPosition = true;
                    break;
            }
        } else {

            shuDuJinBi_(coinType, device, actShortName);
        }


        // shuDuJinBi_(coinType, device, actShortName);

    }

    private static void shuDuJinBi_(String coinType, Device device, String actShortName) {
        System.out.println("速读免费小说");
        // if (version == null) {
        //     version = AdbCommands.getPackageVersion(device, packageName);
        // }
        switch (coinType) {
            case strShuDuYueDu:
            case strShuDuTingShu:
                if (actShortName.equals("com.zhangyue.iReader.ui.activity.ActivityContainer")) {
                    WeiZhi shuDuTingShu = new WeiZhi(890, 1463);
                    AdbTap.wait_tap(device, 5, shuDuTingShu);

                    WeiZhi kanShiPinZuiGaoLing = new WeiZhi(568, 1231);
                    AdbTap.wait_tap(device, 5, kanShiPinZuiGaoLing);
                } else {
                    guangGao(device, actShortName);
                }

                break;

            // case strShuDuYueDu:
            //     if (actShortName.equals("com.zhangyue.iReader.ui.activity.ActivityContainer")) {
            //
            //     } else {
            //         guangGao(device, actShortName);
            //     }
            //
            //     break;

            // default:
            //     actDo.stop(strFQCT);
            //     break;

        }
    }

    private static void guangGao(Device device, String actShortName) {
        switch (actShortName) {
            // case "com.zhangyue.iReader.ui.activity.ActivityContainer":
            //
            //     WeiZhi shuDuTingShu = new WeiZhi(890, 1463);
            //
            //     AdbTap.wait_tap(device, 5, shuDuTingShu);
            //
            //
            //     WeiZhi kanShiPinZuiGaoLing = new WeiZhi(568, 1231);
            //     AdbTap.wait_tap(device, 5, kanShiPinZuiGaoLing);
            //
            //     break;
            case "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity":

                WeiZhi closeBtn = new WeiZhi(987, 104);
                AdbTap.wait_tap(device, s60, closeBtn);

                break;
            case "com.qq.e.ads.PortraitADActivity":
                WeiZhi closeBtn1 = new WeiZhi(981, 171);

                AdbTap.wait_tap(device, s60, closeBtn1);
                break;

            default:

                actDo.stop(strShuDuTingShu);
                break;
        }
    }

}

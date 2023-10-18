package adbs.main.ui.jpanels.auto.act.code;

import adbs.cmd.AdbCommands;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.auto.CoinsType;
import adbs.main.ui.jpanels.auto.act.ActDo;
import adbs.main.ui.jpanels.auto.act.WeiZhi;
import adbs.model.Device;

public class FQMFXS implements CoinsType {

    private static ActDo actDo;
    private static String version;
    private static boolean yijingShangHua;

    // public FQMFXS(ActDo actDo) {
    //     this.actDo = actDo;
    // }

    /**
     * 番茄免费小说金币自动收取功能
     *
     * @param device
     * @param packageName
     * @param actShortName
     */
    public static void actFQMFXS(ActDo actDo, String coinType, Device device, String packageName, String actShortName) {
        FQMFXS.actDo = actDo;
        System.out.println("番茄小说");
        // if (actDo.version == null) {
        //     actDo.version = AdbCommands.getPackageVersion(device, packageName);
        // }
        if (version == null) {
            version = AdbCommands.getPackageVersion(device, packageName);
        }
        switch (coinType) {
            case strFQMFXSYueDu:
                switch (actDo.version) {
                    case "5.6.7.32":
                        actFQMFXS_V_5_6_7_32_YueDu(device, actShortName);
                        break;
                    case "5.8.9.32":
                        System.out.println("番茄免费小说v" + version + "未适配");

                        break;
                    case "5.9.5.32":
                    case "5.9.1.32":
                        actFQMFXS_V_5_9_1_32(device, actShortName);
                        break;

                    default:

                        System.out.println("番茄免费小说v" + version + "未适配");
                        break;

                }
                break;

            case strFQMFXSTingShu:

                actFQMFXSTingShuByVersion(device, actShortName);

                break;
        }

    }


    private static void actFQMFXS_V_5_6_7_32_YueDu(Device device, String actShortName) {
        switch (actShortName) {
            case ".pages.main.MainFragmentActivity":
                WeiZhi yueDuJinBi = new WeiZhi(921, 1103);
                // wait_tap(device, 5, yueDuJinBi);
                AdbTap.wait_tap(device, 5, yueDuJinBi);

                wait_tap_FQCloseBtn(device);

                break;
            default:
                // stop(strFQMFXSYueDu);
                actDo.stop(strFQMFXSYueDu);
                break;
        }
    }

    private static void actFQMFXS_V_5_6_7_32_TingShu(Device device, String actShortName) {
        switch (actShortName) {
            case ".pages.main.MainFragmentActivity":
                WeiZhi tingShuJinBi = new WeiZhi(918, 1534);
                // wait_tap(device, 5, tingShuJinBi);
                AdbTap.wait_tap(device, 5, tingShuJinBi);

                wait_tap_FQCloseBtn(device);
                break;
            default:
                // stop(strFQMFXSTingShu);
                // stop(coinType);
                // actDo.stop(coinType);
                actDo.stop(strFQMFXSTingShu);
                break;
        }
    }


    private static void actFQMFXS_V_5_9_1_32(Device device, String actShortName) {
        if (!yijingShangHua) {
            System.out.println("开始上划");
            // 先小幅度上划6次让听书金币按钮出现
            AdbCommands.swipeBotton2TopOnRightXiaoXiaoDe(device, 6);
            yijingShangHua = true;
        }
        switch (actShortName) {
            case ".pages.main.MainFragmentActivity":
                // WeiZhi tingShuBtn = new WeiZhi(915, 1517);
                // wait_tap(device, 5, tingShuBtn);
                WeiZhi yueDuBtn = new WeiZhi(925, 901);
                // wait_tap(device, 5, yueDuBtn);
                AdbTap.wait_tap(device, 5, yueDuBtn);

                wait_tap_FQCloseBtn(device);

                break;
            default:
                // stop(strFQMFXSTingShu);
                actDo.stop(strFQMFXSTingShu);
                break;
        }
    }

    private static void wait_tap_FQCloseBtn(Device device) {
        WeiZhi closeBtn = new WeiZhi(538, 1638);
        // wait_tap(device, 5, closeBtn);
        AdbTap.wait_tap(device, 5, closeBtn);
    }


    /**
     * 收取不同版本的番茄免费小说听书金币
     *
     * @param device
     * @param actShortName
     */
    private static void actFQMFXSTingShuByVersion(Device device, String actShortName) {
        switch (version) {
            case "5.6.7.32":
                actFQMFXS_V_5_6_7_32_TingShu(device, actShortName);
                break;
            case "5.8.9.32":
                actFQMFXS_V_5_8_9_32_TingShu(device, actShortName);
                break;
            case "5.9.1.32":
            case "5.9.5.32":
                actFQMFXS_V_5_9_1_32_TingShu(device, actShortName);
                // actFQMFXS_V
                break;
            default:
                System.out.println("番茄免费小说v" + version + "未适配");
                break;
        }
    }


    private static void actFQMFXS_V_5_8_9_32_TingShu(Device device, String actShortName) {
        switch (actShortName) {
            case ".pages.main.MainFragmentActivity":
                WeiZhi tingShuJinBi = new WeiZhi(874, 1991);
                // wait_tap(device, 5, tingShuJinBi);
                AdbTap.wait_tap(device, 5, tingShuJinBi);

                wait_tap_FQCloseBtn(device);
                break;

            default:
                actDo.stop(strFQMFXSYueDu);
                break;
        }
        return;
    }


    private static void actFQMFXS_V_5_9_1_32_TingShu(Device device, String actShortName) {
        if (!yijingShangHua) {
            System.out.println("开始上划");
            // 先小幅度上划6次让听书金币按钮出现
            AdbCommands.swipeBotton2TopOnRightXiaoXiaoDe(device, 6);
            yijingShangHua = true;
        }
        switch (actShortName) {
            case ".pages.main.MainFragmentActivity":
                WeiZhi tingShuBtn = new WeiZhi(915, 1517);
                // wait_tap(device, 5, tingShuBtn);
                AdbTap.wait_tap(device, 5, tingShuBtn);

                wait_tap_FQCloseBtn(device);

                break;
            default:
                actDo.stop(strFQMFXSTingShu);
                break;
        }
    }


}

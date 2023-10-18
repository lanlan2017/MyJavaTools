package adbs.main.ui.jpanels.auto.act.code;

import adbs.cmd.AdbCommands;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.auto.CoinsType;
import adbs.main.ui.jpanels.auto.act.ActivityRun;
import adbs.main.ui.jpanels.auto.act.WeiZhi;
import adbs.model.Device;

/**
 * 熊猫免费小说
 * 茄子免费小说
 * 星空免费小说
 * 自动收金币
 */
public class XMQZXK1080_1920 implements CoinsType {
    static ActivityRun activityRun;

    public XMQZXK1080_1920(ActivityRun activityRun) {
        this.activityRun = activityRun;
    }

    /**
     * 茄子免费小说，熊猫免费小说，星空免费小说 1080*1920像素的手机金币收取功能
     *
     * @param device       要操作的设备
     * @param actShortName 当前activity短名称
     */
    public static  void shouJinBi(ActivityRun activityRun, String coinType, Device device, String actShortName) {

        XMQZXK1080_1920.activityRun = activityRun;
        switch (coinType) {
            case strYueDuJinBi:
                // actDoYueDuJinBi(device, actShortName);
                yueDu_1080_1920(device, actShortName);
                break;
            case strTingShuJinBi:
                // actDoTingShuJinBi(device, actShortName);
                // actDoTingShuJinBi(device, actShortName);
                tingShu_1080_1920(device, actShortName);
                break;

            case strTingShuZaiLing:
                // actDoTingShuZaiLing(device, actShortName);
                tingShuZL1080_1920(device, actShortName);
                break;
            case strYueDuZaiLing:
                // actDoYueDuZaiLing(device, actShortName);
                yueDuZL1080_1920(device, actShortName);
                break;
            case strTingShuHongBao:
                // actDoTingShuHongBao(device, actShortName);
                tingShuHB1080_1920(device, actShortName);
                break;

            default:
                System.out.println("金币类型" + coinType + "未适配");
                break;
        }
    }


    private static void yueDu_1080_1920(Device device, String actShortName) {
        switch (actShortName) {
            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                // WeiZhi closeBtn = new WeiZhi(881, 942);
                WeiZhi closeBtn = new WeiZhi(880, 819);
                // wait_tap(device, s3, closeBtn);
                AdbTap.wait_tap(device, s3, closeBtn);

                // WeiZhi yueDuJinBi = new WeiZhi(915, 1180);
                WeiZhi yueDuJinBi = new WeiZhi(898, 1181);
                // wait_tap(device, s3, yueDuJinBi);
                AdbTap.wait_tap(device, s3, yueDuJinBi);

                break;

            default:
                System.out.println("其他act界面，应该停止");
                // stop();
                // stop(strYueDuJinBi);
                activityRun.stop(strYueDuJinBi);
                break;
        }
    }


    /**
     * 听书金币操作
     *
     * @param device
     * @param actShortName
     */
    private static void tingShu_1080_1920(Device device, String actShortName) {
        switch (actShortName) {
            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                // WeiZhi closeBtn = new WeiZhi(881, 942);
                WeiZhi closeBtn = new WeiZhi(880, 819);
                // wait_tap(device, s3, closeBtn);
                AdbTap.wait_tap(device, s3, closeBtn);

                // WeiZhi tingShuJinBi = new WeiZhi(905, 1665);
                WeiZhi tingShuJinBi = new WeiZhi(820, 1658);
                // wait_tap(device, s3, tingShuJinBi);
                AdbTap.wait_tap(device, s3, tingShuJinBi);
                break;

            default:
                System.out.println("其他act界面，应该停止");
                // stop();
                // stop(strTingShuJinBi);
                activityRun.stop(strTingShuJinBi);
                break;
        }
    }


    /**
     * 听书再领1080*1920
     *
     * @param device
     * @param actShortName
     */

    private static void tingShuZL1080_1920(Device device, String actShortName) {
        // addArrActName(actShortName);
        activityRun.addArrActName(actShortName);
        if (actShortName.equals("com.kmxs.reader.webview.ui.DefaultNewWebActivity")) {
            // tingShuZaiLing(device);
            int audioX = 896;
            int audioY = 1602;
            WeiZhi tingShuZaiLing = new WeiZhi(audioX, audioY);
            // wait_tap(device, s3, audioX, audioY);
            // wait_tap(device, s3, tingShuZaiLing);
            AdbTap.wait_tap(device, s3, tingShuZaiLing);
        } else {
            // actDoGuangGao(device, actShortName);
            guangGao_1080_1920(device, actShortName);
        }
        // isEndTest(strTingShuZaiLing);
        activityRun.isEndTest(strTingShuZaiLing);
    }


    /**
     * 广告界面操作1080*1920
     *
     * @param device
     * @param actShortName
     */
    private static void guangGao_1080_1920(Device device, String actShortName) {
        switch (actShortName) {
            case "com.qq.e.ads.PortraitADActivity":
                // wait_tap(device, _50s, new WeiZhi(983, 165));
                AdbTap.wait_tap(device, s50, new WeiZhi(983, 165));
                break;

            case "com.baidu.mobads.sdk.api.MobRewardVideoActivity":
                // wait_tap(device, _35s, new WeiZhi(989, 111));
                // wait_tap(device, _40s, new WeiZhi(991, 64));
                AdbTap.wait_tap(device, s35, new WeiZhi(991, 64));
                break;


            case "com.kmxs.mobad.core.ssp.rewardvideo.RewardVideoDspActivity":

                // wait_tap(device, _40s, new WeiZhi(975, 104));
                AdbTap.wait_tap(device, s35, new WeiZhi(975, 104));
                break;

            case "com.baidu.mobads.sdk.api.AppActivity":
                AdbCommands.returnBtn(device);
                break;

            // case "":
            //     break;


        }
    }


    /**
     * 阅读再领操作 1080*1920
     *
     * @param device
     * @param actShortName
     */

    private static void yueDuZL1080_1920(Device device, String actShortName) {
        // 记录打开的act短名称
        activityRun.addArrActName(actShortName);
        if (actShortName.equals("com.kmxs.reader.webview.ui.DefaultNewWebActivity")) {
            // wait_tap(device, s3, new WeiZhi(894, 1133));
            AdbTap.wait_tap(device, s3, new WeiZhi(894, 1133));
        } else {
            // actDoGuangGao(device, actShortName);
            guangGao_1080_1920(device, actShortName);
        }
        activityRun.isEndTest(strYueDuZaiLing);

    }


    /**
     * 听书红包操作1080*1920
     *
     * @param device
     * @param actShortName
     */
    private static void tingShuHB1080_1920(Device device, String actShortName) {
        activityRun.addArrActName(actShortName);
        if ("com.qimao.qmreader.commonvoice.CommonVoiceActivityV2".equals(actShortName)) {
            // wait_tap(device, s3, new WeiZhi(952, 161));
            // wait_tap(device, s3, new WeiZhi(951, 138));
            // wait_tap(device, s3, new WeiZhi(951, 138));
            AdbTap.wait_tap(device, s3, new WeiZhi(951, 138));
        } else {
            // actDoGuangGao(device, actShortName);
            guangGao_1080_1920(device, actShortName);
        }
        activityRun.isEndTest(strTingShuZaiLing);
    }


}

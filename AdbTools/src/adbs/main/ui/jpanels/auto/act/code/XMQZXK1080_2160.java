package adbs.main.ui.jpanels.auto.act.code;

import adbs.cmd.AdbCommands;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.auto.CoinsType;
import adbs.main.ui.jpanels.auto.act.ActivityRun;
import adbs.main.ui.jpanels.auto.act.WeiZhi;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;

/**
 * 熊猫免费小说
 * 茄子免费小说
 * 星空免费小说
 * 自动收金币
 */
public class XMQZXK1080_2160 implements CoinsType {

    static ActivityRun activityRun;

    // public XingMaoQieZiXingKong_1080_2160(ActDo actDo) {
    //     this.actDo = actDo;
    // }

    /**
     * 茄子免费小说，熊猫免费小说，星空免费小说 1080*2160像素的手机金币收取功能
     *
     * @param device       要操作的设备
     * @param actShortName 当前activity短名称
     * @param coinType
     */
    public static void shouJinBi(ActivityRun activityRun, String coinType, Device device, String actShortName) {
        XMQZXK1080_2160.activityRun = activityRun;
        switch (coinType) {
            case strYueDuJinBi:
                actDoYueDuJinBi(device, actShortName);
                break;
            case strTingShuJinBi:
                tingShuJinBi(device, actShortName);
                break;
            case strTingShuZaiLing:
                tingShuZaiLing(device, actShortName);
                break;
            case strYueDuZaiLing:
                yueDuZaiLing(device, actShortName);
                break;
            case strTingShuHongBao:
                tingShuHongBao(device, actShortName);
                break;
        }
    }

    /**
     * 阅读金币操作
     *
     * @param device
     * @param actShortName
     */

    private static void actDoYueDuJinBi(Device device, String actShortName) {
        switch (actShortName) {
            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                WeiZhi closeBtn = new WeiZhi(881, 942);
                // wait_tap(device, 5, closeBtn);
                AdbTap.wait_tap(device, s3, closeBtn);

                WeiZhi yueDuJinBi = new WeiZhi(915, 1180);
                // wait_tap(device, 5, yueDuJinBi);
                // AdbTap.wait_tap(device, 5, yueDuJinBi);
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
     * 广告界面操作1080*2160
     *
     * @param device
     * @param actShortName
     */
    private static void guangGao(Device device, String actShortName) {
        // _40s = 35;
        switch (actShortName) {
            // case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
            //     // System.out.println("金币模式：" + strYueDuZaiLing);
            //     wait_tap(device, 5, new WeiZhi(888, 1201));
            //     break;
            case "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity":
                ThreadSleep.seconds(2);
                System.out.println("右侧向上滑动");
                AdbCommands.swipeBotton2TopOnRight(device);

                int closeGuangGaoX = 979;
                int closeGuangGaoY = 97;
                WeiZhi closeGuangGao = new WeiZhi(closeGuangGaoX, closeGuangGaoY);
                // wait_tap(device, 5, closeGuangGaoX, closeGuangGaoY);
                // wait_tap(device, _35s, closeGuangGaoX, closeGuangGaoY);
                // wait_tap(device, _40s, closeGuangGao);
                AdbTap.wait_tap(device, s40, closeGuangGao);
                break;

            case "com.qq.e.ads.PortraitADActivity":
                // wait_tap(device, _50s, new WeiZhi(979, 161));
                AdbTap.wait_tap(device, s50, new WeiZhi(979, 161));
                break;

            case "com.qimao.qmreader.commonvoice.CommonVoiceActivityV2":
                //
                // wait_tap(device, s3, new WeiZhi(952, 161));
                AdbTap.wait_tap(device, s3, new WeiZhi(952, 161));
                break;

            case "com.kwad.sdk.api.proxy.app.KsRewardVideoActivity":
                // wait_tap(device, _40s, new WeiZhi(989, 121));
                AdbTap.wait_tap(device, s40, new WeiZhi(989, 121));
                break;

            case "com.baidu.mobads.sdk.api.MobRewardVideoActivity":
                // wait_tap(device, _35s, new WeiZhi(989, 111));
                // wait_tap(device, _50s, new WeiZhi(998, 70));
                AdbTap.wait_tap(device, s50, new WeiZhi(998, 70));
                break;


            case "com.kmxs.mobad.core.ssp.rewardvideo.RewardVideoDspActivity":
                AdbTap.wait_tap(device, s35, new WeiZhi(975, 106));

                break;

            // case "":
            //     break;


        }
    }


    /**
     * 听书金币操作
     *
     * @param device
     * @param actShortName
     */
    private static void tingShuJinBi(Device device, String actShortName) {
        switch (actShortName) {
            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                WeiZhi closeBtn = new WeiZhi(881, 942);
                // wait_tap(device, s3, closeBtn);
                AdbTap.wait_tap(device, s3, closeBtn);

                WeiZhi tingShuJinBi = new WeiZhi(905, 1665);
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
     * 听书再领操作1080*2160
     *
     * @param device
     * @param actShortName
     */

    private static void tingShuZaiLing(Device device, String actShortName) {
        // addArrActName(actShortName);
        activityRun.addArrActName(actShortName);
        if (actShortName.equals("com.kmxs.reader.webview.ui.DefaultNewWebActivity")) {
            // tingShuZaiLing(device);
            int audioX = 884;
            int audioY = 1651;
            WeiZhi tingShuZaiLing = new WeiZhi(audioX, audioY);
            // wait_tap(device, s3, audioX, audioY);
            // wait_tap(device, s3, tingShuZaiLing);
            AdbTap.wait_tap(device, s3, tingShuZaiLing);
        } else {
            guangGao(device, actShortName);
        }
        // isEndTest(strTingShuZaiLing);
        activityRun.isEndTest(strTingShuZaiLing);
    }


    /**
     * 阅读再领操作
     *
     * @param device
     * @param actShortName
     */

    private static void yueDuZaiLing(Device device, String actShortName) {
        // 记录打开的act短名称
        // addArrActName(actShortName);
        activityRun.addArrActName(actShortName);
        if (actShortName.equals("com.kmxs.reader.webview.ui.DefaultNewWebActivity")) {
            // wait_tap(device, s3, new WeiZhi(888, 1201));
            AdbTap.wait_tap(device, s3, new WeiZhi(888, 1201));
        } else {
            guangGao(device, actShortName);
        }
        // isEndTest(strYueDuZaiLing);
        activityRun.isEndTest(strYueDuZaiLing);

    }


    /**
     * 听书红包操作
     *
     * @param device
     * @param actShortName
     */
    private static void tingShuHongBao(Device device, String actShortName) {
        // addArrActName(actShortName);
        activityRun.addArrActName(actShortName);
        if ("com.qimao.qmreader.commonvoice.CommonVoiceActivityV2".equals(actShortName)) {
            // wait_tap(device, s3, new WeiZhi(952, 161));
            // wait_tap(device, s3, new WeiZhi(952, 161));
            AdbTap.wait_tap(device, s3, new WeiZhi(952, 161));
        } else {
            guangGao(device, actShortName);
        }
        // isEndTest(strTingShuZaiLing);
        activityRun.isEndTest(strTingShuZaiLing);
    }

}

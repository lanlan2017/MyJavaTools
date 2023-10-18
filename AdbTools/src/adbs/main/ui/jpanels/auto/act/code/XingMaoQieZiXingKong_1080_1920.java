package adbs.main.ui.jpanels.auto.act.code;

import adbs.cmd.AdbCommands;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.auto.CoinsType;
import adbs.main.ui.jpanels.auto.act.ActDo;
import adbs.main.ui.jpanels.auto.act.WeiZhi;
import adbs.model.Device;

public class XingMaoQieZiXingKong_1080_1920 implements CoinsType {
    ActDo actDo;

    public XingMaoQieZiXingKong_1080_1920(ActDo actDo) {
        this.actDo = actDo;
    }

    /**
     * 茄子免费小说，熊猫免费小说，星空免费小说 1080*1920像素的手机金币收取功能
     *
     * @param device       要操作的设备
     * @param actShortName 当前activity短名称
     */
    public void _XM_QZ_XK_1080_1920(String coinType, Device device, String actShortName) {
        switch (coinType) {
            case strYueDuJinBi:
                // actDoYueDuJinBi(device, actShortName);
                yueDuJinBi_1080_1920(device, actShortName);
                break;
            case strTingShuJinBi:
                // actDoTingShuJinBi(device, actShortName);
                // actDoTingShuJinBi(device, actShortName);
                actDoTingShuJinBi_1080_1920(device, actShortName);
                break;

            case strTingShuZaiLing:
                // actDoTingShuZaiLing(device, actShortName);
                actDoTingShuZaiLing_1080_1920(device, actShortName);
                break;
            case strYueDuZaiLing:
                // actDoYueDuZaiLing(device, actShortName);
                actDoYueDuZaiLing1080_1920(device, actShortName);
                break;
            case strTingShuHongBao:
                // actDoTingShuHongBao(device, actShortName);
                actDoTingShuHongBao_1080_1920(device, actShortName);
                break;

            default:
                System.out.println("金币类型" + coinType + "未适配");
                break;
        }
    }


    private void yueDuJinBi_1080_1920(Device device, String actShortName) {
        switch (actShortName) {
            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                // WeiZhi closeBtn = new WeiZhi(881, 942);
                WeiZhi closeBtn = new WeiZhi(880, 819);
                // wait_tap(device, 5, closeBtn);
                AdbTap.wait_tap(device, 5, closeBtn);

                // WeiZhi yueDuJinBi = new WeiZhi(915, 1180);
                WeiZhi yueDuJinBi = new WeiZhi(898, 1181);
                // wait_tap(device, 5, yueDuJinBi);
                AdbTap.wait_tap(device, 5, yueDuJinBi);

                break;

            default:
                System.out.println("其他act界面，应该停止");
                // stop();
                // stop(strYueDuJinBi);
                actDo.stop(strYueDuJinBi);
                break;
        }
    }


    /**
     * 听书金币操作
     *
     * @param device
     * @param actShortName
     */
    private void actDoTingShuJinBi_1080_1920(Device device, String actShortName) {
        switch (actShortName) {
            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                // WeiZhi closeBtn = new WeiZhi(881, 942);
                WeiZhi closeBtn = new WeiZhi(880, 819);
                // wait_tap(device, 5, closeBtn);
                AdbTap.wait_tap(device, 5, closeBtn);

                // WeiZhi tingShuJinBi = new WeiZhi(905, 1665);
                WeiZhi tingShuJinBi = new WeiZhi(820, 1658);
                // wait_tap(device, 5, tingShuJinBi);
                AdbTap.wait_tap(device, 5, tingShuJinBi);
                break;

            default:
                System.out.println("其他act界面，应该停止");
                // stop();
                // stop(strTingShuJinBi);
                actDo.stop(strTingShuJinBi);
                break;
        }
    }


    /**
     * 听书再领1080*1920
     *
     * @param device
     * @param actShortName
     */

    private void actDoTingShuZaiLing_1080_1920(Device device, String actShortName) {
        // addArrActName(actShortName);
        actDo.addArrActName(actShortName);
        if (actShortName.equals("com.kmxs.reader.webview.ui.DefaultNewWebActivity")) {
            // tingShuZaiLing(device);
            int audioX = 896;
            int audioY = 1602;
            WeiZhi tingShuZaiLing = new WeiZhi(audioX, audioY);
            // wait_tap(device, 5, audioX, audioY);
            // wait_tap(device, 5, tingShuZaiLing);
            AdbTap.wait_tap(device, 5, tingShuZaiLing);
        } else {
            // actDoGuangGao(device, actShortName);
            actDoGuangGao_1080_1920(device, actShortName);
        }
        // isEndTest(strTingShuZaiLing);
        actDo.isEndTest(strTingShuZaiLing);
    }


    /**
     * 广告界面操作1080*1920
     *
     * @param device
     * @param actShortName
     */
    private void actDoGuangGao_1080_1920(Device device, String actShortName) {
        switch (actShortName) {
            // case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
            //     // System.out.println("金币模式：" + strYueDuZaiLing);
            //     wait_tap(device, 5, new WeiZhi(888, 1201));
            //     break;
            // case "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity":
            //     ThreadSleep.seconds(2);
            //     System.out.println("右侧向上滑动");
            //     AdbCommands.swipeBotton2TopOnRight(device);
            //
            //     int closeGuangGaoX = 979;
            //     int closeGuangGaoY = 97;
            //     WeiZhi closeGuangGao = new WeiZhi(closeGuangGaoX, closeGuangGaoY);
            //     // wait_tap(device, 5, closeGuangGaoX, closeGuangGaoY);
            //     // wait_tap(device, _35s, closeGuangGaoX, closeGuangGaoY);
            //     wait_tap(device, _35s, closeGuangGao);
            //     break;

            case "com.qq.e.ads.PortraitADActivity":
                // wait_tap(device, _50s, new WeiZhi(983, 165));
                AdbTap.wait_tap(device, s50, new WeiZhi(983, 165));
                break;

            // case "com.qimao.qmreader.commonvoice.CommonVoiceActivityV2":
            //     //
            //     wait_tap(device, 5, new WeiZhi(952, 161));
            //     break;

            // case "com.kwad.sdk.api.proxy.app.KsRewardVideoActivity":
            //     wait_tap(device, _35s, new WeiZhi(989, 121));
            //     break;

            case "com.baidu.mobads.sdk.api.MobRewardVideoActivity":
                // wait_tap(device, _35s, new WeiZhi(989, 111));
                // wait_tap(device, _40s, new WeiZhi(991, 64));
                AdbTap.wait_tap(device, s40, new WeiZhi(991, 64));
                break;


            case "com.kmxs.mobad.core.ssp.rewardvideo.RewardVideoDspActivity":

                // wait_tap(device, _40s, new WeiZhi(975, 104));
                AdbTap.wait_tap(device, s40, new WeiZhi(975, 104));
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

    private void actDoYueDuZaiLing1080_1920(Device device, String actShortName) {
        // 记录打开的act短名称
        actDo.addArrActName(actShortName);
        if (actShortName.equals("com.kmxs.reader.webview.ui.DefaultNewWebActivity")) {
            // wait_tap(device, 5, new WeiZhi(894, 1133));
            AdbTap.wait_tap(device, 5, new WeiZhi(894, 1133));
        } else {
            // actDoGuangGao(device, actShortName);
            actDoGuangGao_1080_1920(device, actShortName);
        }
        actDo.isEndTest(strYueDuZaiLing);

    }


    /**
     * 听书红包操作1080*1920
     *
     * @param device
     * @param actShortName
     */
    private void actDoTingShuHongBao_1080_1920(Device device, String actShortName) {
        actDo.addArrActName(actShortName);
        if ("com.qimao.qmreader.commonvoice.CommonVoiceActivityV2".equals(actShortName)) {
            // wait_tap(device, 5, new WeiZhi(952, 161));
            // wait_tap(device, 5, new WeiZhi(951, 138));
            // wait_tap(device, 5, new WeiZhi(951, 138));
            AdbTap.wait_tap(device, 5, new WeiZhi(951, 138));
        } else {
            // actDoGuangGao(device, actShortName);
            actDoGuangGao_1080_1920(device, actShortName);
        }
        actDo.isEndTest(strTingShuZaiLing);
    }


}

package adbs.main.ui.jpanels.auto.act;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.model.AppNames;
import adbs.main.ui.jpanels.auto.AdbTap;
import adbs.main.ui.jpanels.auto.CoinsType;
import adbs.main.ui.jpanels.universal.runnable.CloseableRunnable;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;

/**
 * 根据activity自动进行操作
 */
public class ActDo extends CloseableRunnable implements CoinsType {
    /**
     * 用来记录当前的activity短名称，当整个数组中的短名称都一样时，说明操作无效，应该则停止线程结束操作
     */
    private static String[] arrActName = new String[3];

    private static ActDo runYueDuZaiLing = new ActDo(strYueDuZaiLing);
    private static ActDo runTingShuZaiLing = new ActDo(strTingShuZaiLing);
    private static ActDo runYueDuJinBi = new ActDo(strYueDuJinBi);
    private static ActDo runTingShuJinBi = new ActDo(strTingShuJinBi);
    private static ActDo runTingShuHongBao = new ActDo(strTingShuHongBao);
    private static ActDo runFQMFXSTingShu = new ActDo(strFQMFXSTingShu);
    private static ActDo runFQMFXSYueDu = new ActDo(strFQMFXSYueDu);
    private static ActDo runFQCT = new ActDo(strFQCT);


    private String coinType;
    private int times;
    private String packageNameVersion;
    boolean yijingShangHua = false;
    private String version;

    public ActDo(String coinType) {
        this.coinType = coinType;
        // switch (coinType) {
        //     // case str
        // }
    }

    // public static ActDo getRunYueDuZaiLing() {
    //     return runYueDuZaiLing;
    // }
    //
    // public static ActDo getRunTingShuZaiLing() {
    //     return runTingShuZaiLing;
    // }
    //
    // public static ActDo getRunYueDuJinBi() {
    //     return runYueDuJinBi;
    // }
    //
    // public static ActDo getRunTingShuJinBi() {
    //     return runTingShuJinBi;
    // }
    // private static boolean stop = false;

    public static ActDo getRun(String coinType) {
        System.out.println("在GetRun中");
        ActDo actDo = null;
        switch (coinType) {
            case strYueDuJinBi:
                // actDo = getRunYueDuJinBi();
                actDo = runYueDuJinBi;
                break;
            case strTingShuJinBi:
                // actDo = getRunTingShuJinBi();
                actDo = runTingShuJinBi;
                break;
            case strYueDuZaiLing:
                // actDo = getRunYueDuZaiLing();
                actDo = runYueDuZaiLing;

                break;
            case strTingShuZaiLing:
                // actDo = getRunTingShuZaiLing();
                actDo = runTingShuZaiLing;
                break;
            case strTingShuHongBao:
                // actDo = getRunTingShuHongBao();
                actDo = runTingShuHongBao;
                break;

            case strFQMFXSTingShu:
                actDo = runFQMFXSTingShu;
                break;

            case strFQMFXSYueDu:
                actDo = runFQMFXSYueDu;
                break;
            case strFQCT:
                actDo = runFQCT;
                break;

        }
        return actDo;
    }

    // public static ActDo getRunTingShuHongBao() {
    //     return runTingShuHongBao;
    // }

    @Override
    protected void setMsg() {

    }

    // @Override
    public void stop(String message) {
        // ActDo.stop = true;
        stop();
        // String message = "自动Act停止了";
        message = message + " 停止了";
        JOptionPane.showMessageDialog(AdbTools.getInstance().getContentPane(), message);
    }

    /**
     * 判断是否应该停止操作。
     *
     * @return 当连续点击三次，当前的activity都没有改变改变的时候，就应该停止了。
     */
    private boolean isEnd() {
        // System.out.println("arrActName[0] = " + arrActName[0]);

        int i = 1;
        for (; i < arrActName.length; i++) {
            // System.out.println("arrActName[" + i + "] = " + arrActName[i]);
            // 如果找到不相等的元素
            if (!arrActName[0].equals(arrActName[i])) {
                // 停止搜索
                break;
            }
        }

        // System.out.println("i = " + i);
        // System.out.println("arrActName.length = " + arrActName.length);

        // boolean b =

        return i == arrActName.length;
    }


    @Override
    protected void loopBody() {
        autoActDo();
    }

    public static void main(String[] args) {

        // autoActDo();

        // runTingShuZaiLing = new ActDo(strTingShuZaiLing);

        // new Thread(runTingShuZaiLing).start();
        // new Thread(runYueDuZaiLing).start();

        // runYueDuJinBi = new ActDo(strYueDuJinBi);
        // new Thread(runYueDuJinBi).start();
        // runTingShuJinBi = new ActDo(strTingShuJinBi);
        // new Thread(runTingShuJinBi).start();
        // runTingShuHongBao = new ActDo(strTingShuHongBao);
        // new Thread(runTingShuHongBao).start();


        // runFQMFXSTingShu = new ActDo(strFQMFXSTingShu);
        // new Thread(runFQMFXSTingShu).start();
        // runFQMFXSYueDu = new ActDo(strFQMFXSYueDu);
        // new Thread(runFQMFXSYueDu).start();

        AdbCommands.hideNavigationBar(AdbTools.getInstance().getDevice());
        // runFQCT = new ActDo(strFQCT);
        new Thread(runFQCT).start();


    }

    @Override
    protected void beforeLoop() {
        super.beforeLoop();
        // zaiYunXingZhiQian();
        stop = false;
        // Arrays.fill(arrActName, "");
        for (int i = 0; i < arrActName.length; i++) {
            arrActName[i] = "" + i;
        }
        times = 0;
    }

    /**
     * 根据activity自动操作
     */
    private void autoActDo() {
        // zaiYunXingZhiQian();

        AdbTools adbTools = AdbTools.getInstance();
        Device device = adbTools.getDevice();
        int width = device.getWidth();
        int height = device.getHeight();

        System.out.println("width = " + width);
        System.out.println("height = " + height);

        while (!stop) {

            AppNames appNames = AdbGetPackage.getAppNames();
            // System.out.println("appNames = " + appNames);
            System.out.println(appNames);

            String packageName = appNames.getPackageName();
            String actShortName = appNames.getActShortName();


            // System.out.println("packageName = " + packageName);
            // System.out.println("actShortName = " + actShortName);
            switch (packageName) {
                // 熊猫免费小说
                // 茄子免费小说
                // 星空免费小说
                case "com.xm.freader":
                case "com.qz.freader":
                case "com.xk.qreader":
                    if (width == 1080 && height == 2160) {
                        shouJinBi_XM_QZ_XK_1080_2160(device, actShortName);
                    }

                    break;
                case "com.dragon.read":
                    if (width == 1080 && height == 2160) {
                        // 番茄免费小说收金币
                        actFQMFXS(device, packageName, actShortName);
                    }

                    break;
                case "com.xs.fm":
                    if (width == 1080 && height == 2160) {
                        actFQCT(device, packageName, actShortName);
                    }


                    break;
                case "com.huawei.fastapp":
                case "com.huawei.hwid":
                default:
                    // 误操作，关闭其他APP，回到本APP
                    AdbCommands.killOtherApp(device);
                    break;
            }


            System.out.println("循环 等待5秒");
            System.out.println();
            ThreadSleep.seconds(5);
        }

        System.out.println("自动操作停止...");
    }


    // private String getPackageVersion(Device device, String packageName) {
    //     String code = "adb -s " + device.getSerial() + " shell dumpsys package " + packageName + " |findstr versionName";
    //     // String version = AdbCommands.runAbdCmd(code).trim();
    //     String version = AdbCommands.runAbdCmd(code);
    //     System.out.println("version = " + version);
    //     String versionsFlag = "versionName=";
    //     if (version.contains(versionsFlag)) {
    //         version = version.substring(version.indexOf(versionsFlag) + versionsFlag.length());
    //         System.out.println("version = " + version);
    //
    //     }
    //     return version;
    // }

    // /**
    //  * 点击任务键，然后向上滑动，杀死右边的APP，接着点击屏幕中点，启动应用。
    //  *
    //  * @param device
    //  */
    // private void killOtherApp(Device device) {
    //     ThreadSleep.seconds(3);
    //     AdbCommands.taskBtn(device);
    //     ThreadSleep.seconds(3);
    //     AdbCommands.swipeBotton2TopOnRight(device);
    //     ThreadSleep.seconds(3);
    //     AdbTap.tapCenterPosition(device);
    //     ThreadSleep.seconds(3);
    // }
    //

    /**
     * 判断是否应该停止
     *
     * @param strYueDuZaiLing
     */
    private void isEndTest(String strYueDuZaiLing) {
        if (isEnd()) {
            System.out.println("判断： 听书再领 结束了！");
            stop(strYueDuZaiLing);
        }
        times++;
    }

    /**
     * 茄子免费小说，熊猫免费小说，星空免费小说 1080*2160像素的手机金币收取功能
     *
     * @param device       要操作的设备
     * @param actShortName 当前activity短名称
     */
    private void shouJinBi_XM_QZ_XK_1080_2160(Device device, String actShortName) {

        switch (coinType) {
            case strYueDuJinBi:
                actDoYueDuJinBi(device, actShortName);
                break;
            case strTingShuJinBi:
                actDoTingShuJinBi(device, actShortName);
                break;
            case strTingShuZaiLing:
                actDoTingShuZaiLing(device, actShortName);
                break;
            case strYueDuZaiLing:
                actDoYueDuZaiLing(device, actShortName);
                break;
            case strTingShuHongBao:
                actDoTingShuHongBao(device, actShortName);
                break;

        }


        // old(device, actShortName);
    }

    /**
     * 番茄免费小说金币自动收取功能
     *
     * @param device
     * @param packageName
     * @param actShortName
     */
    private void actFQMFXS(Device device, String packageName, String actShortName) {
        System.out.println("番茄小说");
        if (version == null) {
            version = AdbCommands.getPackageVersion(device, packageName);
        }
        switch (coinType) {
            case strFQMFXSYueDu:
                switch (version) {
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

    /**
     * 番茄畅听金币自动收取功能
     *
     * @param device
     * @param packageName
     * @param actShortName
     */
    private void actFQCT(Device device, String packageName, String actShortName) {
        System.out.println("番茄畅听");
        if (version == null) {
            version = AdbCommands.getPackageVersion(device, packageName);
        }
        switch (coinType) {
            case strFQCT:
                switch (version) {
                    case "5.1.3.32":
                        System.out.println("xxxxxxxxxxxxxx");
                        actFQCT_v_5_1_3_32(device, actShortName);
                        break;
                    case "5.0.2.32":
                        actFQCT_v_5_1_3_32(device, actShortName);
                        // actFQCT_v_5_0_2_32(device, actShortName);
                        break;
                    default:
                        System.out.println("番茄畅听v" + version + "未适配");
                        break;

                }
                break;
            default:
                stop(strFQCT);
                break;

        }

    }

    // /**
    //  * 番茄畅听v5.0.2.32版本，自动收金币
    //  *
    //  * @param device
    //  * @param actShortName
    //  */
    // private void actFQCT_v_5_0_2_32(Device device, String actShortName) {
    //     if (!yijingShangHua) {
    //         System.out.println("开始上划");
    //         // 先小幅度上划6次让听书金币按钮出现
    //         // AdbCommands.swipeBotton2TopOnRightXiaoXiaoDe(device, 5);
    //         AdbCommands.swipeBotton2TopOnRightXiaoXiaoDe(device, 5);
    //         yijingShangHua = true;
    //     }
    //     switch (actShortName) {
    //         case "com.dragon.read.pages.main.MainFragmentActivity":
    //             WeiZhi tingShuJinBi = new WeiZhi(921, 1228);
    //             wait_tap(device, 5, tingShuJinBi);
    //             wait_tap_FQCloseBtn(device);
    //             break;
    //         default:
    //             stop(strFQCT);
    //             break;
    //     }
    // }

    private void actFQCT_v_5_1_3_32(Device device, String actShortName) {
        if (!yijingShangHua) {
            System.out.println("开始上划");
            // 先小幅度上划6次让听书金币按钮出现
            AdbCommands.swipeBotton2TopOnRightXiaoXiaoDe(device, 5);
            yijingShangHua = true;
        }
        switch (actShortName) {
            case "com.dragon.read.pages.main.MainFragmentActivity":
                // WeiZhi tingShuBtn = new WeiZhi(915, 1517);
                // wait_tap(device, 5, tingShuBtn);
                //
                // WeiZhi closeBtn = new WeiZhi(538, 1638);
                // wait_tap(device, 5, closeBtn);
                System.out.println("主界面");
                // WeiZhi tingShuJinBi = new WeiZhi(918, 1224);
                WeiZhi tingShuJinBi = new WeiZhi(921, 1228);
                wait_tap(device, 5, tingShuJinBi);
                // //
                wait_tap_FQCloseBtn(device);
                break;
            default:
                stop(strFQCT);
                break;
        }
    }


    private void actFQMFXS_V_5_9_1_32(Device device, String actShortName) {
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
                wait_tap(device, 5, yueDuBtn);

                wait_tap_FQCloseBtn(device);

                break;
            default:
                stop(strFQMFXSTingShu);
                break;
        }
    }

    /**
     * 收取不同版本的番茄免费小说听书金币
     *
     * @param device
     * @param actShortName
     */
    private void actFQMFXSTingShuByVersion(Device device, String actShortName) {
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

    private void actFQMFXS_V_5_9_1_32_TingShu(Device device, String actShortName) {
        if (!yijingShangHua) {
            System.out.println("开始上划");
            // 先小幅度上划6次让听书金币按钮出现
            AdbCommands.swipeBotton2TopOnRightXiaoXiaoDe(device, 6);
            yijingShangHua = true;
        }
        switch (actShortName) {
            case ".pages.main.MainFragmentActivity":
                WeiZhi tingShuBtn = new WeiZhi(915, 1517);
                wait_tap(device, 5, tingShuBtn);

                wait_tap_FQCloseBtn(device);

                break;
            default:
                stop(strFQMFXSTingShu);
                break;
        }
    }



    // private void shouJinBiFQMFXS_V_5_8_9_32(Device device, String actShortName) {
    //     switch (coinType) {
    //         case strFQMFXSTingShu:
    //             actFQMFXS_V_5_8_9_32_TingShu(device, actShortName);
    //
    //
    //     }
    // }

    private void actFQMFXS_V_5_8_9_32_TingShu(Device device, String actShortName) {
        switch (actShortName) {
            case ".pages.main.MainFragmentActivity":
                WeiZhi tingShuJinBi = new WeiZhi(874, 1991);
                wait_tap(device, 5, tingShuJinBi);

                wait_tap_FQCloseBtn(device);
                break;

            default:
                stop(strFQMFXSYueDu);
                break;
        }
        return;
    }

    // private void shouJinBiFQMFXS_V_5_6_7_32(Device device, String actShortName) {
    //     switch (coinType) {
    //         case strFQMFXSTingShu:
    //             actFQMFXS_V_5_6_7_32_TingShu(device, actShortName);
    //             break;
    //         case strFQMFXSYueDu:
    //             actFQMFXS_V_5_6_7_32_YueDu(device, actShortName);
    //             break;
    //     }
    // }

    private void actFQMFXS_V_5_6_7_32_YueDu(Device device, String actShortName) {
        switch (actShortName) {
            case ".pages.main.MainFragmentActivity":
                WeiZhi yueDuJinBi = new WeiZhi(921, 1103);
                wait_tap(device, 5, yueDuJinBi);

                wait_tap_FQCloseBtn(device);

                break;
            default:
                stop(strFQMFXSYueDu);
                break;
        }
    }

    private void actFQMFXS_V_5_6_7_32_TingShu(Device device, String actShortName) {
        switch (actShortName) {
            case ".pages.main.MainFragmentActivity":
                WeiZhi tingShuJinBi = new WeiZhi(918, 1534);
                wait_tap(device, 5, tingShuJinBi);

                wait_tap_FQCloseBtn(device);
                break;
            default:
                // stop(strFQMFXSTingShu);
                stop(coinType);
                break;
        }
    }

    /**
     * 听书金币操作
     *
     * @param device
     * @param actShortName
     */
    private void actDoTingShuJinBi(Device device, String actShortName) {
        switch (actShortName) {
            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                WeiZhi closeBtn = new WeiZhi(881, 942);
                wait_tap(device, 5, closeBtn);

                WeiZhi tingShuJinBi = new WeiZhi(905, 1665);
                wait_tap(device, 5, tingShuJinBi);
                break;

            default:
                System.out.println("其他act界面，应该停止");
                // stop();
                stop(strTingShuJinBi);
                break;
        }
    }

    /**
     * 阅读金币操作
     *
     * @param device
     * @param actShortName
     */

    private void actDoYueDuJinBi(Device device, String actShortName) {
        switch (actShortName) {
            case "com.kmxs.reader.webview.ui.DefaultNewWebActivity":
                WeiZhi closeBtn = new WeiZhi(881, 942);
                wait_tap(device, 5, closeBtn);

                WeiZhi yueDuJinBi = new WeiZhi(915, 1180);
                wait_tap(device, 5, yueDuJinBi);

                break;

            default:
                System.out.println("其他act界面，应该停止");
                // stop();
                stop(strYueDuJinBi);
                break;
        }
    }

    /**
     * 广告界面操作
     *
     * @param device
     * @param actShortName
     */
    private void actDoGuangGao(Device device, String actShortName) {
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
                // wait_tap(device, 35, closeGuangGaoX, closeGuangGaoY);
                wait_tap(device, 35, closeGuangGao);
                break;

            case "com.qq.e.ads.PortraitADActivity":
                wait_tap(device, 35, new WeiZhi(979, 161));
                break;

            case "com.qimao.qmreader.commonvoice.CommonVoiceActivityV2":
                //
                wait_tap(device, 5, new WeiZhi(952, 161));
                break;

            case "com.kwad.sdk.api.proxy.app.KsRewardVideoActivity":
                wait_tap(device, 35, new WeiZhi(989, 121));
                break;

            case "com.baidu.mobads.sdk.api.MobRewardVideoActivity":
                // wait_tap(device, 35, new WeiZhi(989, 111));
                wait_tap(device, 35, new WeiZhi(998, 70));
                break;


            // case "":
            //     break;


        }
    }

    /**
     * 阅读再领操作
     *
     * @param device
     * @param actShortName
     */

    private void actDoYueDuZaiLing(Device device, String actShortName) {
        // 记录打开的act短名称
        arrActName[times % arrActName.length] = actShortName;
        if (actShortName.equals("com.kmxs.reader.webview.ui.DefaultNewWebActivity")) {
            wait_tap(device, 5, new WeiZhi(888, 1201));
        } else {
            actDoGuangGao(device, actShortName);
        }
        isEndTest(strYueDuZaiLing);

    }

    /**
     * 听书再领操作
     *
     * @param device
     * @param actShortName
     */
    private void actDoTingShuZaiLing(Device device, String actShortName) {
        arrActName[times % arrActName.length] = actShortName;
        if (actShortName.equals("com.kmxs.reader.webview.ui.DefaultNewWebActivity")) {
            // tingShuZaiLing(device);
            int audioX = 884;
            int audioY = 1651;
            WeiZhi tingShuZaiLing = new WeiZhi(audioX, audioY);
            // wait_tap(device, 5, audioX, audioY);
            wait_tap(device, 5, tingShuZaiLing);
        } else {
            actDoGuangGao(device, actShortName);
        }
        isEndTest(strTingShuZaiLing);
    }

    /**
     * 听书红包操作
     *
     * @param device
     * @param actShortName
     */
    private void actDoTingShuHongBao(Device device, String actShortName) {
        arrActName[times % arrActName.length] = actShortName;
        if ("com.qimao.qmreader.commonvoice.CommonVoiceActivityV2".equals(actShortName)) {
            // wait_tap(device, 5, new WeiZhi(952, 161));
            wait_tap(device, 5, new WeiZhi(952, 161));
        } else {
            actDoGuangGao(device, actShortName);
        }
        isEndTest(strTingShuZaiLing);
    }

    private void wait_tap_FQCloseBtn(Device device) {
        WeiZhi closeBtn = new WeiZhi(538, 1638);
        wait_tap(device, 5, closeBtn);
    }

    private static void wait_tap(Device device, int seconds, WeiZhi tingShuZaiLing) {
        wait_tap(device, seconds, tingShuZaiLing.getX(), tingShuZaiLing.getY());
    }

    private static void wait_tap(Device device, int seconds, int x, int y) {
        System.out.print("操作 等待" + seconds + "秒,");
        ThreadSleep.seconds(seconds);
        // ThreadSleep.seconds(35);
        System.out.print("点击:" + x + "," + y);
        System.out.println();
        AdbTap.tap(device, x, y);
        // System.out.println();
    }
}

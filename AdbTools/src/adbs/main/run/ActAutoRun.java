package adbs.main.run;

import adbs.main.AdbTools;
import adbs.main.run.model.AppNames;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.tools.thread.ThreadSleep;

import javax.swing.*;

public class ActAutoRun implements Runnable {

    private AdbTools adbTools;
    private TimingPanels2 timingPanels2;
    private static boolean stop;
    private UniversalPanels universalPanels;

    @Override
    public void run() {
        adbTools = AdbTools.getInstance();
        timingPanels2 = adbTools.getTimingPanels2();
        universalPanels = adbTools.getUniversalPanels();
        stop = false;
        FixedQueue<AppNames> fixedQueue = new FixedQueue<>(1);
        while (!stop) {
            // 获取当前act
            AppNames appNames = AdbGetPackage.getAppNames();
            AppNames offer = fixedQueue.offer(appNames);
            boolean equals = appNames.equals(offer);
            // System.out.println("offer.equals(appNames) = " + equals);
            // 当act改变时，说明用户切换了界面。
            if (!equals) {
                actChange(appNames, offer);
            }
            _wait();
        }
    }

    /**
     * 当activity改变时，要执行的操作
     *
     * @param appNames 当前的APP
     * @param offer    之前的APP
     */
    private void actChange(AppNames appNames, AppNames offer) {
        System.out.println("   offer = " + offer);
        System.out.println("appNames = " + appNames);
        if (offer != null) {
            String offerPackageName = offer.getPackageName();
            String packageName = appNames.getPackageName();
            // System.out.println("offerPackageName = " + offerPackageName);
            // System.out.println("packageName = " + packageName);
            System.out.println("offerPackageName = " + offerPackageName);
            System.out.println("packageName = " + packageName);
            if (packageName.equals(offerPackageName)) {
                // 在同一个APP内
                System.out.println("在同一个APP内");
                actChange(appNames);
            } else {
                // 新的APP不是任务APP
                if (isSystemApp(packageName)) {
                    appChange(offerPackageName);
                }
            }
        } else {
            // 在用户切换界面时，执行动作
            actChange(appNames);
        }
    }

    /**
     * 从一个APP跳转到另一个APP是执行操作
     *
     * @param offerPackageName
     */
    private void appChange(String offerPackageName) {
        // 打开了新的APP
        System.out.println("打开了新的APP");
        switch (offerPackageName) {
            // 原来的应用是趣头条
            case "com.jifen.qukan":
                // timingPanels2.w180s();
                // break;
                //    今日头条极速版
                // com.ss.android.article.lite
            case "com.ss.android.article.lite":
                //  今日头条
            case "com.ss.android.article.news":
                //    西瓜视频
            case "com.ss.android.article.video":
                //    番茄免费小说
            case "com.dragon.read":
            case "com.xs.fm":
            case "com.ss.android.ugc.aweme.lite":
                // 番茄畅听
                timingPanels2.w180s();
                break;

            case "com.baidu.searchbox.lite":
                // 百度极速版
            case "com.kuaishou.nebula":
                timingPanels2.w95s();
                break;
        }
    }

    /**
     * 判断现在的APP是否是系统应用
     *
     * @param packageName
     * @return
     */
    private boolean isSystemApp(String packageName) {
        boolean b;
        switch (packageName) {
            // 任务视图程序
            case "com.huawei.appmarket":
            case "com.huawei.android.launcher":
            case "com.android.systemui":
            case "com.byyoung.setting":
            case "com.coloros.recents":
            case "com.miui.home":
            case "com.miui.securitycenter":
                //    应用市场
                // 搞机工具箱
            //    小米安全检查?
                b = false;
                break;
            default:
                b = true;
                break;
        }
        return b;

        // return
        //         // 不是安卓应用
        //         packageName.startsWith("com.android.") &&
        //                 // 不是MIUI应用
        //                 packageName.startsWith("com.miui.") &&
        //                 // 不是华为应用
        //                 packageName.startsWith("com.huawei.") &&
        //                 // 不是OPPO应用
        //                 packageName.startsWith("com.coloros.") &&
        //                 // 不是搞机工具箱
        //                 packageName.equals("com.byyoung.setting");

    }

    private void _wait() {
        System.out.println("等待一段时间...");
        // ThreadSleep.seconds(30);
        // ThreadSleep.seconds(5);
        ThreadSleep.seconds(8);
        // ThreadSleep.seconds(10);
        // ThreadSleep.seconds(15);
        // ThreadSleep.seconds(20);
    }

    /**
     * 同一个App的不同activity改变时
     *
     * @param appNames
     */
    private void actChange(AppNames appNames) {
        String packageName = appNames.getPackageName();
        String actShortName = appNames.getActShortName();
        System.out.println("\n界面改变，给出建议...");
        System.out.println("    packageName = " + packageName);
        System.out.println("    actShortName = " + actShortName);
        switch (packageName) {
            // case "com.android.dialer/.DialtactsActivity"
            case "com.android.dialer":
            case "com.android.contacts":
                // case "com android dialer":
                dianHua(actShortName);
                break;
            // 趣头条
            case "com.jifen.qukan":
                quTouTiao(actShortName);
                break;
            // 点淘
            case "com.taobao.live":
                dianTao(actShortName);
                break;
            //  淘宝
            case "com.taobao.taobao":
                taobao(actShortName);
                break;
            case "com.ss.android.article.video":
                xiGuaShiPin(actShortName);
                break;
        }
    }

    private void xiGuaShiPin(String actShortName) {
        switch (actShortName) {
            case ".activity.SplashActivity":
                // timingPanels2.vw();
                // vidioBtn();
                universalPanels.vidioBtnDoClick();
                break;
            default:
                break;
        }
    }


    private void taobao(String actShortName) {
        switch (actShortName) {
            // 主界面
            case "com.taobao.tao.TBMainActivity":
                // 显示刷视频系列按钮
                timingPanels2.vw();
                break;
            //    签到红包界面
            case "com.taobao.themis.container.app.TMSActivity":
                timingPanels2.s();
                break;
            // 浏览领金币界面
            case "com.taobao.browser.BrowserActivity":
            case "com.taobao.browser.exbrowser.BrowserUpperActivity":
                // timingPanels2.s();
                timingPanels2.s35s();
                break;
        }
    }

    private void dianTao(String actShortName) {
        switch (actShortName) {
            case ".h5.BrowserActivity":
            case ".pha.PHAContainerActivity":
                // timingPanels2.auto("s_95");
                timingPanels2.s();
                // break;
                // timingPanels2.s();
                break;
            // case ".h5.BrowserUpperActivity":
            case ".h5.BrowserUpperActivity":
                timingPanels2.s35s();
                break;
            case ".TaoLiveVideoActivity":
                timingPanels2.vw();
                break;
            case "com.qq.e.ads.PortraitADActivity":
            case "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity":
                // timingPanels2.auto("w_65s");
                timingPanels2.w65s();
                break;
        }
    }

    /**
     * 趣头条APP
     *
     * @param actShortName
     */
    private void quTouTiao(String actShortName) {
        System.out.println("趣头条APP");
        switch (actShortName) {
            // 文章界面
            case ".content.newsdetail.news.NewsDetailNewActivity":
                System.out.println("文章界面");
                System.out.println();
                universalPanels.getBrowseButton().doClick();
                break;
            //    广告界面
            case "com.iclicash.advlib.ui.front.InciteADActivity":
            case "com.qq.e.ads.PortraitADActivity":
                System.out.println("广告界面");
                // timingPanels2.getjComboBox().setSelectedIndex(0);
                // timingPanels2.auto("w_65s");
                timingPanels2.w65s();
                break;

        }
    }

    /**
     * 电话APP
     *
     * @param actShortName
     */
    private void dianHua(String actShortName) {
        System.out.println("电话");
        switch (actShortName) {
            case ".DialtactsActivity":
            case ".activities.DialtactsActivity":
                System.out.println("来了啊");
                // JComboBox<String> stringJComboBox = adbTools.getTimingPanels2().getjComboBox();
                JComboBox<String> stringJComboBox = timingPanels2.getjComboBox();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // stringJComboBox.setSelectedIndex(0);
                        // // timingPanels2.getBtn180s().doClick();
                        // timingPanels2.getBtn35s().doClick();
                        timingPanels2.auto("w_180s");

                    }
                });

                break;
        }
    }
}

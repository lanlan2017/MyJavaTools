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
                executeAction(appNames);
            } else {
                // 新的APP不是任务APP
                if (isNotTaskApp(packageName)) {
                    // 打开了新的APP
                    System.out.println("打开了新的APP");
                    switch (offerPackageName) {
                        // 原来的应用是趣头条
                        case "com.jifen.qukan":
                            timingPanels2.w180s();
                            break;
                    }
                }
            }
        } else {
            // 在用户切换界面时，执行动作
            executeAction(appNames);
        }
    }

    /**
     * 判断现在的APP是不是任务视图APP
     *
     * @param packageName
     * @return
     */
    private boolean isNotTaskApp(String packageName) {
        boolean b;
        switch (packageName) {
            case "com.huawei.android.launcher":
            case "com.coloros.recents":
            case "com.miui.home":
            case "com.android.systemui":
                b = false;
                break;
            default:
                b = true;
                break;
        }
        return b;
        // return !packageName.equals("com.huawei.android.launcher")&&!packageName.equals("com.coloros.recents")&&!packageName.equals("com.miui.home");
    }

    private void _wait() {
        System.out.println("等待一段时间...");
        // ThreadSleep.seconds(30);
        // ThreadSleep.seconds(5);
        ThreadSleep.seconds(10);
        // ThreadSleep.seconds(15);
        // ThreadSleep.seconds(20);
    }

    private void executeAction(AppNames appNames) {
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
        }
    }

    private void dianTao(String actShortName) {
        switch (actShortName) {
            case ".h5.BrowserActivity":
            case ".h5.BrowserUpperActivity":
            case ".pha.PHAContainerActivity":
                // timingPanels2.auto("s_95");
                timingPanels2.s();
                // break;
                // timingPanels2.s();
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

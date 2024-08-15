package adbs.main.run;

import adbs.main.AdbTools;
import adbs.main.run.act.DianTao;
import adbs.main.run.model.ActivityInfo;
import adbs.main.run.model.FrameTitle;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.tools.thread.ThreadSleep;
import config.AdbToolsProperties;

import javax.swing.*;
import java.util.HashSet;

public class ActAutoRun implements Runnable {

    private AdbTools adbTools;
    private TimingPanels2 timingPanels2;
    private UniversalPanels universalPanels;

    private static boolean stop;


    private HashSet<String> wait20M_Act;
    private final HashSet<String> wait11s_Act;
    private HashSet<String> wait15s_Act;
    private final HashSet<String> wait20s_Act;
    private HashSet<String> wait3M_Act;
    private HashSet<String> wait95sApp;
    private HashSet<String> wait180sApp;


    public ActAutoRun() {
        //设置从哪些APP离开后需要等待180秒钟
        initWait180sApp();
        //射中从哪些APP离开后需要等待95秒钟
        initWait95sApp();

        wait11s_Act = initWait11sAct();
        // 设置在哪些Activity界面钟，线程等待间隔为15秒
        initWait15sAct();
        // 设置在哪些Activity界面钟，线程等待间隔为30秒
        wait20s_Act = initWait30sAct();
        //设置在哪些Activity界面中，线程等待间隔为3分钟
        initWait3M_Act();
        // 设置在哪些Activity界面中，线程等待间隔为1小时
        initWait1HAct();


    }

    /**
     * 从哪些APP离开之后需要等待95秒。
     */
    private void initWait95sApp() {
        wait95sApp = new HashSet<>();
        // 百度极速版
        wait95sApp.add("com.baidu.searchbox.lite");
        // 快手极速版
        wait95sApp.add("com.kuaishou.nebula");
        // 快手
        wait95sApp.add("com.smile.gifmaker");
        // 悟空浏览器
        wait95sApp.add("com.cat.readall");
    }

    /**
     * 确定从哪些APP离开后需要等待180秒
     */
    private void initWait180sApp() {
        wait180sApp = new HashSet<>();
        // 趣头条
        wait180sApp.add("com.jifen.qukan");
        //今日头条极速版
        wait180sApp.add("com.ss.android.article.lite");
        // 今日头条
        wait180sApp.add("com.ss.android.article.news");
        // 西瓜视频
        wait180sApp.add("com.ss.android.article.video");
        // 番茄免费小说
        wait180sApp.add("com.dragon.read");
        //番茄畅听
        wait180sApp.add("com.xs.fm");
        //番茄畅听音乐版
        wait180sApp.add("com.xs.fm.lite");
        // 抖音
        wait180sApp.add("com.ss.android.ugc.aweme");
        //抖音极速版
        wait180sApp.add("com.ss.android.ugc.aweme.lite");
        //抖音火山版
        wait180sApp.add("com.ss.android.ugc.live");
        //红果免费短剧
        wait180sApp.add("com.phoenix.read");
    }

    private HashSet<String> initWait11sAct() {
        HashSet<String> wait11s_Act = new HashSet<>();
        // 点淘
        wait11s_Act.add("com.taobao.live/.h5.BrowserUpperActivity");
        // 点淘省钱特辑，
        wait11s_Act.add("com.taobao.live/.h5.BrowserActivity");
        //        番茄畅听，音乐播放界面
        wait11s_Act.add("com.xs.fm.lite/com.dragon.read.pages.main.MainFragmentActivity");
        return wait11s_Act;

    }

    /**
     * 设置检查频率为15秒的Activity
     */
    private void initWait15sAct() {
        // HashSet<String> wait30S = new HashSet<>();
        wait15s_Act = new HashSet<>();
        // 直播界面
        wait15s_Act.add("com.taobao.live/.TaoLiveVideoActivity");
        // //
        // wait15s_Act.add("com.taobao.live/.h5.BrowserUpperActivity");
        // //省钱特辑，
        // wait15s_Act.add("com.taobao.live/.h5.BrowserActivity");
    }

    private HashSet<String> initWait30sAct() {
        // HashSet<String> wait30S = new HashSet<>();
        HashSet<String> wait30s_Act = new HashSet<>();
        // 华为桌面
        wait30s_Act.add("com.huawei.android.launcher/.unihome.UniHomeLauncher");
        return wait30s_Act;
    }

    /**
     * 在哪些Activity中，线程等待间隔为3分钟
     */
    private void initWait3M_Act() {
        wait3M_Act = new HashSet<>();
        // 红果免费短剧，短剧播放界面
        wait3M_Act.add("com.phoenix.read/com.ss.android.excitingvideo.ExcitingVideoActivity");
        //番茄畅听音乐版，音频播放界面
        wait3M_Act.add("com.xs.fm.lite/com.dragon.read.reader.speech.page.AudioPlayActivity");
        //番茄畅听音乐版，全屏歌词显示界面
        wait3M_Act.add("com.xs.fm.lite/com.dragon.read.music.lyric.FullScreenLyricActivity");
    }

    /**
     * 在哪些Activity中，线程等待间隔时间为1小时
     */
    private void initWait1HAct() {
        // 需要等待1个小时的Activity
        // HashSet<String> wait1H_Act = new HashSet<>();
        wait20M_Act = new HashSet<>();
        wait20M_Act.add("com.kuaishou.kgx.novel/com.kuaishou.novel.read.ReaderActivityV2");
        // return wait1H_Act;
    }

    @Override
    public void run() {
        adbTools = AdbTools.getInstance();
        timingPanels2 = adbTools.getTimingPanels2();
        universalPanels = adbTools.getUniversalPanels();
        stop = false;
        // 现在的Activity信息
        ActivityInfo act;
        // 之前的Activity信息
        //
        ActivityInfo beforeAct = null;
        while (!stop) {
            // 获取当前act
            act = AdbGetPackage.getActivityInfo();

            boolean equals = act.equals(beforeAct);
            // System.out.println("beforeAct.equals(appNames) = " + equals);
            // 当act改变时，说明用户切换了界面。
            if (!equals) {
                actChange(beforeAct, act);
            }
            // 根据当前的Activity来决定要等待多久
            _wait(act);
            //记录下上次的Activity详细信息
            beforeAct = act;
        }
    }

    /**
     * 当activity改变时，要执行的操作
     *
     * @param act    当前的APP
     * @param before 之前的APP
     */
    private void actChange(ActivityInfo before, ActivityInfo act) {
        // System.out.println("   before = " + before);
        // System.out.println("appNames = " + appNames);
        if (before != null) {
            // 之前应用的包名
            String offerPackageName = before.getPackageName();
            // 现在应用的包名
            String packageName = act.getPackageName();
            // System.out.println("offerPackageName = " + offerPackageName);
            // System.out.println("packageName = " + packageName);
            // System.out.println("offerPackageName = " + offerPackageName);
            // System.out.println("packageName = " + packageName);
            //如果现在的包名和之前的包名相同
            if (packageName.equals(offerPackageName)) {
                // 在同一个APP内
                // System.out.println("在同一个APP内");
                actChangeInSameApp(before, act);
            } else {
                // 从一个APP跳转到另一个APP
                // 新的APP不是任务APP
                if (!isSystemApp(packageName)) {
                    System.out.println("新打开的APP不是系统应用");
                    appChange(before, act);
                }
                // 根据当前的Activity更新应用标题
                updateTitle(act);


            }
        } else {
            // 在用户切换界面时，执行动作
            // actChange(act);
        }
    }

    private void updateTitle(ActivityInfo act) {
        String currentPackageName = act.getPackageName();
        String currentAppName = AdbToolsProperties.moneyApkPro.getProperty(currentPackageName);
        System.out.println("currentAppName = " + currentAppName);
        // 如果不相等，说明是可赚钱的APP
        if (!currentPackageName.equals(currentAppName)) {
            updateFormTitle(currentAppName);
        } else {
            //                    相等，说明是其他应用，
            FrameTitle frameTitle = FrameTitle.getFrameTitle();
            String appName = frameTitle.getAppName();
            if (!appName.startsWith("_")) {
                appName = "_" + appName;
                updateFormTitle(appName);
            }
        }
    }

    /**
     * 从一个APP跳转到另一个APP是执行操作
     */
    //    private void appChange(String offerPackageName) {
    private void appChange(ActivityInfo before, ActivityInfo current) {

        String beforePackageName = before.getPackageName();
        //        String currentPackageName = current.getPackageName();
        //        String currentAppName = AdbToolsProperties.moneyApkPro.getProperty(currentPackageName);
        //        System.out.println("currentAppName = " + currentAppName);
        //        updateFormTitle(currentAppName);


        if (wait180sApp.contains(beforePackageName)) {
//            String title = "应用跳转";
//            String message = "要等待180秒？";
            //            AdbTools.getInstance().showDialogOk(title, message, new ActionListener() {
            //                @Override
            //                public void actionPerformed(ActionEvent e) {
            //                    timingPanels2.w180s();
            //                }
            //            });
            //             timingPanels2.w180s();
            timingPanels2.w180s();
        } else if (wait95sApp.contains(beforePackageName)) {
//            // timingPanels2.w95s();
//            String title = "应用跳转";
//            String message = "要等待95秒？";
//            AdbTools.getInstance().showDialogOk(title, message, new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    timingPanels2.w95s();
//                    // timingPanels2.w180s();
//                }
//            });

            timingPanels2.w95s();

        }

    }

    /**
     * 更新窗体标题
     *
     * @param appName APP名称
     */
    private void updateFormTitle(String appName) {
        JFrame frame = adbTools.getFrame();
        FrameTitle frameTitle = FrameTitle.getFrameTitle();
        String appName1 = frameTitle.getAppName();
        // 如果应用名和原来的不同
        if (!appName1.equals(appName)) {
            System.out.println("应用名改变了更新窗体标题");
            //更新应用名
            frameTitle.setAppName(appName);
            // 设置窗体标题
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    frame.setTitle(frameTitle.toString());
                }
            });
        }
    }

    /**
     * 判断现在的APP是否是系统应用
     */
    private boolean isSystemApp(String packageName) {
        HashSet<String> systemApp = new HashSet<>();
        // 任务视图程序
        systemApp.add("com.android.systemui");
        // 安卓设置
        systemApp.add("com.android.settings");
        // 安卓短信
        systemApp.add("com.android.mms");
        // 搞机工具箱
        systemApp.add("com.byyoung.setting");
        // 华为应用市场
        systemApp.add("com.huawei.appmarket");
        // 华为桌面
        systemApp.add("com.huawei.android.launcher");
        // 华为运动健康
        systemApp.add("com.huawei.health");
        // OPPO任务视图
        systemApp.add("com.coloros.recents");
        // OPPO桌面
        systemApp.add("com.oppo.launcher");
        // OPPO手机管家
        systemApp.add("com.coloros.safecenter");
        // 小米桌面
        systemApp.add("com.miui.home");
        // 小米应用市场
        systemApp.add("com.xiaomi.market");
        // 小米安装器
        systemApp.add("com.miui.packageinstaller");
        // 小米安全中心
        systemApp.add("com.miui.securitycenter");
        // 运动健康OPPO
        systemApp.add("com.free.pedometer");
        // 魅族桌面
        systemApp.add("com.meizu.flyme.launcher");
        // 魅族运动
        systemApp.add("com.meizu.net.pedometer");
        // 魅族应用商店
        systemApp.add("com.meizu.mstore");

        return systemApp.contains(packageName);
    }

    private void _wait(ActivityInfo activityInfo) {
        String actLongName = activityInfo.getActLongName();
        if (wait20M_Act.contains(actLongName)) {
            _wait(60 * 60);
        } else if (wait3M_Act.contains(actLongName)) {
            _wait(3 * 60);
        } else if (wait20s_Act.contains(actLongName)) {
            _wait(20);
        } else if (wait15s_Act.contains(actLongName)) {
            _wait(15);
        } else if (wait11s_Act.contains(actLongName)) {
            _wait(11);
        } else {
            // _wait(9);
            _wait(9);
            // _wait(8);
            // _wait(7);
        }
    }

    private void _wait(int seconds) {
        // System.out.println("等待一段时间...");
        // ThreadSleep.seconds(30);
        // ThreadSleep.seconds(5);
        System.out.println("act 等待：" + seconds);
        ThreadSleep.seconds(seconds);
        // ThreadSleep.seconds(8);
        // ThreadSleep.seconds(10);
        // ThreadSleep.seconds(15);
        // ThreadSleep.seconds(20);
    }

    /**
     * 同一个App的不同activity改变时
     */
    private void actChangeInSameApp(ActivityInfo before, ActivityInfo current) {
        String packageCurrent = current.getPackageName();
        //现在的Activit名称
        String actShorCurrent = current.getActShortName();
        //之前的的Activit名称
        String actShortBefore = before.getActShortName();
        // System.out.println("act改变： " + actShortBefore + " -> " + actShorCurrent);
        System.out.println("act改变： " + before + " -> " + current);
        System.out.println("act改变： \"" + actShortBefore + "\",\"" + actShorCurrent + "\"");
        // System.out.println("\n界面改变，给出建议...");
        // System.out.println("    packageCurrent = " + packageCurrent);
        // System.out.println("    actShorCurrent = " + actShorCurrent);
        switch (packageCurrent) {
            // case "com.android.dialer/.DialtactsActivity"
            case "com.android.dialer":
            case "com.android.contacts":
                // case "com android dialer":
                dianHua(actShorCurrent);
                break;
            // 趣头条
            case "com.jifen.qukan":
                quTouTiao(actShorCurrent);
                break;
            // 点淘
            case "com.taobao.live":
                // dianTao(actShortBefore, actShorCurrent);
                DianTao.dianTao(actShortBefore, actShorCurrent);
                break;
            // 淘宝
            case "com.taobao.taobao":
                taobao(actShorCurrent);
                break;
            case "com.ss.android.article.video":
                xiGuaShiPin(actShorCurrent);
                break;
            case "com.xunmeng.pinduoduo":
                pinDuoDuo(actShorCurrent);
                break;
            case "com.kuaishou.kgx.novel":
                switch (actShorCurrent) {
                    case ".ui.activity.HomeActivity":
                    case "com.kuaishou.novel.read.ReaderActivityV2":
                        timingPanels2.rw();
                        break;
                }
                break;
        }
    }

    private void pinDuoDuo(String actShortName) {
        switch (actShortName) {
            // case ".ui.activity.HomeActivity":
            case ".ui.activity.HomeActivity":
                timingPanels2.vw();
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
            // 签到红包界面
            case "com.taobao.themis.container.app.TMSActivity":
            case "com.taobao.browser.BrowserActivity":
                timingPanels2.s();
                break;
            // 浏览领金币界面
            case "com.taobao.browser.exbrowser.BrowserUpperActivity":
                timingPanels2.s();
                // timingPanels2.s35s();
                break;
        }
    }
    //
    // private void dianTao(String actBefore, String act) {
    // switch (actBefore) {
    // // 之前是元宝中心
    // case ".pha.PHAContainerActivity":
    // switch (act) {
    // // 现在是精品推荐
    // case ".h5.BrowserUpperActivity":
    // timingPanels2.s();
    // break;
    // // 现在是直播界面
    // case ".TaoLiveVideoActivity":
    // // timingPanels2.vw();
    // timingPanels2.vw180s();
    // break;
    // //现在是字节的广告界面
    // case "com.bytedance.sdk.openadsdk.stub.activity.Stub_Standard_Portrait_Activity":
    // timingPanels2.w65s();
    // break;
    // }
    // break;
    // }
    //
    // }

    /**
     * 趣头条APP
     *
     * @param actShortName
     */
    private void quTouTiao(String actShortName) {
        // System.out.println("趣头条APP");
        switch (actShortName) {
            // 文章界面
            case ".content.newsdetail.news.NewsDetailNewActivity":
                // System.out.println("文章界面");
                // System.out.println();
                universalPanels.getBrowseButton().doClick();
                break;
            // 广告界面
            case "com.iclicash.advlib.ui.front.InciteADActivity":
            case "com.qq.e.ads.PortraitADActivity":
                // System.out.println("广告界面");
                // timingPanels2.getjComboBox().setSelectedIndex(0);
                // timingPanels2.auto("w_65s");
                // timingPanels2.w65s();
                timingPanels2.w();
                break;

        }
    }

    /**
     * 电话APP
     *
     * @param actShortName
     */
    private void dianHua(String actShortName) {
        // System.out.println("电话");
        switch (actShortName) {
            case ".DialtactsActivity":
            case ".activities.DialtactsActivity":
                // System.out.println("来了啊");
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

package adbs.main.run.act;

import adbs.main.AdbTools;
import adbs.main.run.AdbGetPackage;
import adbs.main.run.AdbShellPmListPackages_3;
import adbs.main.run.act.model.ActivityInfo;
import adbs.main.run.act.model.FrameTitle;
import adbs.main.run.signinlog.FileUtil;
import adbs.main.run.signinlog.LoginRecords;
import adbs.main.ui.jpanels.act.ActSignedInPanels;
import adbs.main.ui.jpanels.act.reminder.AlarmUtils_Second;
import adbs.main.ui.jpanels.app.AppSignedInPanels;
import adbs.main.ui.jpanels.timeauto2.TimingPanels2;
import adbs.main.ui.jpanels.tools.ToolsJPanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.tools.thread.ThreadSleep;
import config.AdbToolsProperties;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.util.*;

public class ActAutoRun extends ActWait implements Runnable {

    private AdbTools adbTools;
    private TimingPanels2 timingPanels2;
    private UniversalPanels universalPanels;

    private static volatile boolean stop;

    // --------------------- 前台APP线程的 变量 开始   ----------------------------
    private static volatile boolean nextDay = false;
    /**
     * 是否所有的APP都签到完毕。
     */
    private static volatile boolean isAllAppOpened;
    private ToolsJPanels toolsJPanels;
    /**
     * 保存今日签到的app名称的列表
     * <p>
     * 今日打开的APP
     */
    private final ArrayList<String> appOpened = new ArrayList<>();
    /**
     * 是否停止签到检查
     */
    private volatile boolean stopAppCheck;
    private JPanel universalPanel;
    private Color background;
    /**
     * 签到记录文件
     */
    private final LoginRecords loginRecords = new LoginRecords();

    private AppSignedInPanels appSignedInPanels;
    /**
     * 保存所有可赚钱的APP名称的列表
     */
    private static ArrayList<String> apps;

    /**
     * 是否停止等待，直接进行下一步的签到检查。
     */
    private static volatile boolean stopWait;
    public static final String appNameEndFlag = " ";
    private ActivityInfo beforeAct;
    //    /**
    //     * 设备师傅已经离线
    //     */
    //    private boolean liXian;
    /**
     * 获取Activity信息的adb.exe命令支持出错的次数
     */
    private int adbErorrTimes;
    private boolean isfrist;
    // private TaoBaoChange taoBaoChange;
    // private DianTaoChange dianTaoChange;
    // private FanQieMianFeiXiaoShuo fanQieMianFeiXiaoShuo;

    // --------------------- 前台APP线程的 变量 结束   ----------------------------


    public ActAutoRun() {
    }


    @Override
    public void run() {
        // adbTools = AdbTools.getInstance();
        // timingPanels2 = adbTools.getTimingPanels2();
        // universalPanels = adbTools.getUniversalPanels();
        stop = false;
        // 现在的Activity信息
        ActivityInfo act;
        // 上一轮查询时的Activity信息
        beforeAct = null;
        before();
        isfrist = true;
        while (!stop) {
            // 获取当前act
            act = AdbGetPackage.getActivityInfo();
            //            System.out.println("act.getActLongName() = " + act.getActLongName());

            boolean equals = act.equals(beforeAct);
            // System.out.println("beforeAct.equals(appNames) = " + equals);
            // 当act改变时，说明用户切换了界面。
            if (!equals) {
                actChange(beforeAct, act);
            }

            if (isAllAppOpened) {
                check_(act.getPackageName());
            }
            //            if (isfrist && !"".equals(act.getPackageName())) {
            if (isfrist && !act.isAdbError()) {
                System.out.println("首次更新签到列表");
                //                System.out.println("act.getActLongName() = " + act.getActLongName());
                //                System.out.println("act.getPackageName() = " + act.getPackageName());
                check_(act.getPackageName());
                updateTitle(act);

                isfrist = false;
            }

            //记录下上次的Activity详细信息
            beforeAct = act;

            // 如果刚好进入第2天
            if (isNextDay()) {
                // 清空前一天的签到设置
                nextDaySetting();
            }

            // 根据当前的Activity来决定要等待多久
            _wait(act);
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
            // 程序启动的时候before为null
            // 在用户切换界面时，执行动作
            // actChange(act);
            // 如果此时的act改变，也就行一次签到检查
            String packageName = act.getPackageName();
            check_(packageName);

        }
    }

    private void updateTitle(ActivityInfo act) {
        String currentPackageName = act.getPackageName();
        String currentAppName = AdbGetPackage.getAppName(currentPackageName);
        System.out.println("currentAppName = " + currentAppName);
        // 如果不相等，说明是可赚钱的APP
        if (!currentPackageName.equals(currentAppName)) {
            updateFormTitle(currentAppName);
        } else {
            // 相等，说明是其他应用，
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
    private void appChange(ActivityInfo before, ActivityInfo current) {
        String beforePN = before.getPackageName();
        String currentPN = current.getPackageName();
        appChangeAuto(beforePN, currentPN);

        ActSignedInPanels.setAppName(AdbToolsProperties.moneyApkPro.getProperty(currentPN));
        //String packageName = current.getPackageName();
        //String packageName = currentPN;
        //extracted(packageName);
        check_(currentPN);
    }

    private void appChangeAuto(String beforePN, String currentPN) {
        //之前的APP需要等待180秒
        if (wait180sApp.contains(beforePN)) {
            //之前的是头条 180秒 系列
            if (touTiao180sApp.contains(beforePN)) {
                //现在不是头条应用，不是头条 180s，也不是头条95秒
                if (!isTouTiaoApp(currentPN)) {
                    System.out.println("头条 180s 应用 跳转到 非头条应用 ---> 等待180秒");
                    //                    timingPanels2.w180sDialog();
                    timingPanels2.w180s();
                } else {
                    System.out.println("头条 180s 应用 跳转到 头条应用   ---> 无需操作");
                }
            } else {
                System.out.println("之前的应用不是头条应用，---> 等待180秒 ");
                //                timingPanels2.w180sDialog();
                timingPanels2.w180s();
                //                timingPanels2.w180sDialog();
            }
        } else if (wait95sApp.contains(beforePN)) {
            // 之前是 头条 95秒 系列
            if (touTiao95sApp.contains(beforePN)) {
                if (!isTouTiaoApp(currentPN)) {
                    System.out.println("头条 95s 应用，跳转到 非头条 应用 ---> 等待95秒");
                    //                timingPanels2.w95s();
                    //                    timingPanels2.w95sDialog();
                    timingPanels2.w95s();
                } else {
                    System.out.println("头条 95s 应用，跳转到 头条 应用   ---> 无需操作 ");
                }
            }
            // 之前的是 快手 95秒 系列
            else if (kuaiShou95sApp.contains(beforePN)) {
                //现在 不是快手95秒系列
                if (!kuaiShou95sApp.contains(currentPN)) {
                    System.out.println("快手 95s 应用，跳转到 非快手 95s 应用  ---> 等待95秒");
                    //                timingPanels2.w95s();
                    timingPanels2.w95s();
                    //                    timingPanels2.w95sDialog();
                } else {
                    System.out.println("快手 95s 应用，跳转到 快手 95s 应用    ---> 无需操作");
                }
            } else {
                System.out.println("其他 95s 应用 跳转到 新应用 ---> 等待95秒");
                timingPanels2.w95s();
            }
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
        // 安卓安装器
        systemApp.add("com.android.packageinstaller");
        // 搞机工具箱
        systemApp.add("com.byyoung.setting");

        // 华为应用市场
        systemApp.add("com.huawei.appmarket");
        // 华为手机管家
        systemApp.add("com.huawei.systemmanager");
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

        //        adbErorrTimes = 0;
        //如果包名是空字符串，说明adb命令执行出错了，一般来说是设备掉线，或者设备暂时无法响应。
        //        if ("".equals(actLongName)) {
        if (activityInfo.isAdbError()) {
            System.out.println("获取Activity 失败，失败计数器加一");
            if (adbErorrTimes < 10) {
                adbErorrTimes++;
            }
        } else {
            //            System.out.println("成功获取Activity");
            adbErorrTimes = 0;
        }
        //        lixian = false;
        //        if (liXian) {
        //如果执行adb命令连续出错超过5次，则认为设备已经离线了
        if (adbErorrTimes > 5) {
            //
            String message = "设备已经离线，10分钟后再检测";
            System.out.println(message);
            //            AdbTools.getInstance().showDialogOk(message);
            _wait(10 * 60);
        }


        if (wait1H_Act.contains(actLongName)) {
            _wait(60 * 60);
        } else if (wait3M_Act.contains(actLongName)) {
            _wait(3 * 60);
        } else if (wait20s_Act.contains(actLongName)) {
            _wait(20);
        } else if (wait13s_Act.contains(actLongName)) {
            _wait(13);
        } else if (wait11s_Act.contains(actLongName)) {
            _wait(11);
        } else {
            // _wait(9);
            _wait(9);
            // _wait(8);
            // _wait(7);
        }
    }

    /**
     * 等待当前线程指定秒数
     *
     * @param seconds 要等待的秒数
     */
    private void _wait(int seconds) {
        System.out.println("act 等待：" + seconds);
        stopWait = false;
        int s2 = 2;
        int count = 0;
        while (!stopWait) {
            // System.out.println("stopWait = " + stopWait);
            // 等待5秒
            ThreadSleep.seconds(s2);
            // System.out.println("签到线程等待中:" + count);
            count += s2;
            if (count >= seconds) {
                // stopWait = true;
                break;
            }
        }
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
        //        System.out.println("act改变： " + before + " -> " + current);
        System.out.println("act改变： \"" + actShortBefore + "\", \"" + actShorCurrent + "\"");
        // System.out.println("\n界面改变，给出建议...");
        // System.out.println("    packageCurrent = " + packageCurrent);
        // System.out.println("    actShorCurrent = " + actShorCurrent);
        switch (packageCurrent) {
            case "com.taobao.live":
                // 点淘
                DianTao.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
            case "com.taobao.taobao":
                // 淘宝
                TaoBao.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
            case "com.taobao.litetao":
                //陶特
                TaoTe.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
            case "com.qiyi.video.lite":
                // 爱奇艺极速版
                AiQiYi.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
            case "com.dragon.read":
                // 番茄免费小说
                FanQieMianFeiXiaoShuo.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
            case "com.ss.android.ugc.aweme.lite":
                //抖音极速版
                DouYinJiSuBan.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
            //今日头条极速版
            case "com.ss.android.article.lite":
                JinRiTouTiaoJiSuBan.getInstance().onChange(actShortBefore, actShorCurrent);
                break;

            case "com.ss.android.article.video":
                //西瓜视频
                XiGuaShiPin.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
            case "com.ximalaya.ting.lite":
                // 喜马拉雅极速版
                XiMaLaYaJiSuBan.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
            case "com.kuaishou.kgx.novel":
                // 快手免费小说
                KuaiShouMianFeiXiaoShuo.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
            case "com.xunmeng.pinduoduo":
                // 拼多多
                PinDuoDuo.getInstance().onChange(actShortBefore, actShorCurrent);
                break;
        }
    }


    // --------------------- 前台APP线程 方法 开始  ----------------------------

    private void before() {
        // 更新操作的面板
        updatePanels();
        // 更新应用列表
        updatePackages_3_money();
        // 如果有旧的记录的话
        readLoginRecords();
    }

    /**
     * 读取签到记录文件
     */
    private void readLoginRecords() {
        String loginRecordsTxt = AdbTools.getInstance().getDevice().getLoginRecordsTxt();
        // System.out.println("loginRecordsTxt = " + loginRecordsTxt);
        // 读取文件中的签到记录
        LoginRecords loginRecords_old = new LoginRecords(loginRecordsTxt);
        //
        if (loginRecords.equals(loginRecords_old)) {
            // System.out.println("有历史记录");
            String appOpenedStr = loginRecords_old.getAppOpened();
            // System.out.println("appOpenedStr = " + appOpenedStr);
            // 去除方括号
            String trimmedAppsStr = appOpenedStr.substring(1, appOpenedStr.length() - 1);
            if (trimmedAppsStr.contains(", ")) {
                System.out.println("有两个以上的签到记录");
                System.out.println("trimmedAppsStr = |" + trimmedAppsStr + "|");
                // 去除方括号之后还有其他元素
                // 分割字符串
                String[] split = trimmedAppsStr.split(", ");
                // 转换为ArrayList
                ArrayList<String> appOpened = new ArrayList<>(Arrays.asList(split));
                this.appOpened.addAll(appOpened);

                // 把文件中保存的已经签到的APP名称显示在签到列表中
                updateSignedInApp();
                if (appOpened.size() == apps.size()) {
                    // 签到完成设置
                    allAppChecked();
                }
            } else if (!"".equals(trimmedAppsStr)) {
                System.out.println("只有一个签到记录");
                System.out.println("trimmedAppsStr = |" + trimmedAppsStr + "|");
                this.appOpened.add(trimmedAppsStr);
                updateSignedInApp();
            }

            showNotOpenApp();
            // showOpenedApp();

        }
    }

    public static void updatePackages_3_money() {
        // ForegroundAppRun.apps = null;
        ActAutoRun.apps = null;
        System.out.println("更新可赚钱APP列表");
        // ForegroundAppRun.apps = new AdbShellPmListPackages_3().getPackages_3_money();
        ActAutoRun.apps = new AdbShellPmListPackages_3().getPackages_3_money();
        System.out.println("可赚钱APP列表: " + apps);
        extracted();
    }

    /**
     * 给指定的APP添加提醒任务
     */
    private static void extracted() {
        //在指定的时间提醒用户
        Iterator<String> iterator = apps.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if ("点淘".equals(next)) {
                dianTaoTasks();
                break;
            }
        }
    }

    private static void dianTaoTasks() {
        Map<String, Runnable> tasks = new HashMap<>();
        tasks.put("13:00:00", () -> AdbTools.getInstance().beepDialog("点淘 开始午睡"));
        tasks.put("16:00:00", () -> AdbTools.getInstance().beepDialog("点淘 结束 午睡"));
        tasks.put("18:00:00", () -> AdbTools.getInstance().beepDialog("点淘 睡觉 气泡更新"));
        tasks.put("20:00:00", () -> AdbTools.getInstance().beepDialog("点淘 开始 第二次签到"));
        tasks.put("21:00:00", () -> AdbTools.getInstance().beepDialog("点淘 开始 晚睡"));
        tasks.forEach((s, runnable) -> AlarmUtils_Second.scheduleDailyReminder(s, runnable));
    }


    private void updatePanels() {

        adbTools = AdbTools.getInstance();
        universalPanels = adbTools.getUniversalPanels();
        universalPanel = universalPanels.getUniversalPanel();
        appSignedInPanels = AdbTools.getInstance().getAppPanels();
        toolsJPanels = AdbTools.getInstance().getToolsJPanels();
        background = universalPanel.getBackground();
        timingPanels2 = adbTools.getTimingPanels2();
    }


    private void check_(String packageName) {
        if (packageName != null && !"".equals(packageName)) {
            check(packageName);
        }
        //        isfrist = false;
    }

    private void check(String packageName) {
        // 更新签到记录表
        //        updateAppOpened(packageName);
        // 如果还没停止签到检查的话
        if (!stopAppCheck) {
            //            updateAppOpened(packageName);
            // 如果用户勾选了所有应用都打开了
            if (isAllAppOpened) {
                // 清空签到记录表
                clearCheckInForm();
                // 把所有的APP都填到签到记录表中
                copyAllAppsIntoCheckInForm();
                allAppChecked();

            } else {
                // 如果没有签到完毕
                updateAppOpened(packageName);

                if (appOpened.size() == apps.size()) {
                    // 签到完成设置
                    allAppChecked();
                }
            }
            // 打印已经打开的APP
            showOpenedApp();
            // 打印没打开的APP
            showNotOpenApp();
        }

    }

    private void allAppChecked() {

        // // 已签到完成，停止签到检查
        // stopAppCheck = true;
        // 把apk的名称放到列表中
        // System.out.println("已打开:" + apkOpenedToday);
        System.out.println("所有的apk签到完成!");
        // // 改变背景色，表示签到完成
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                universalPanel.setBackground(Color.pink);
            }
        });
        // 不再进行签到检查
        stopAppCheck = true;
    }


    //    /**
    //     * 重签之后的操作
    //     */
    //    private void nextDaySetting() {
    //        // 更新签到记录
    //        clearCheckInRecords();
    //        // 第二天结束了
    //        nextDay = false;
    //        // stopAppCheck = false;
    //        isAllAppOpened = false;
    //        isfrist = true;
    ////        // 卸载无用APP
    ////        toolsJPanels.getBtnUninstallAll().doClick();
    ////        // 打开手机管家
    ////        adbTools.getAdbJPanels().getBtnMobileButler().doClick();
    ////        // 停止线程，防止反复触发
    ////        //        ThreadSleep.minutes(1);
    ////        //        adbTools.getDevice().getSerial();
    ////        //        AdbCommands.home(adbTools.getDevice());
    ////        adbTools.getDevice().homeBtn();
    //        beforeAct = null;
    //        // ThreadSleep.minutes(4.0);
    //        //        _wait(40);
    //    }


    /**
     * 停止等待
     */
    public static void stopWait() {
        stopWait = true;
    }


    /**
     * 清空签到记录
     */
    private void clearCheckInRecords() {
        // 在凌晨的时候，移除所有apk的打开记录
        // if (isNextDay() && apkOpenedToday.size() > 0) {
        if (appOpened.size() > 0) {
            // 清空签到记录表
            clearCheckInForm();
            // 开启签到检查
            stopAppCheck = false;

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    // 恢复原来的背景色，表示还没签到完成
                    universalPanel.setBackground(background);
                    JTextPane signedIn = appSignedInPanels.getSignedIn();
                    showNotOpenApp();
                    // 清空签到记录表
                    signedIn.setText("");
                    // 清空已打开APP记录对象变量
                    loginRecords.setAppOpened("[]");
                    // 已经是第二天，更新日期
                    loginRecords.updateDate();
                    System.out.println();
                    System.out.println("loginRecords = " + loginRecords);
                    System.out.println();
                    // 把清空后的值写文件
                    String loginRecordsTxt = AdbTools.getInstance().getDevice().getLoginRecordsTxt();
                    FileUtil.writeStringToFile(loginRecords.toString(), loginRecordsTxt);
                    // showOpenedApp();
                }
            });


        }
    }


    /**
     * 显示没打开的金币应用名称
     */
    private void showNotOpenApp() {
        StringBuffer sb = new StringBuffer();
        // System.out.print("未打开:");
        for (int i = 0, size = apps.size(), count = 0; i < size; i++) {
            String apkName = apps.get(i);
            // 在所有的apk名称列表中查找 已打开的apk名称
            int index = Collections.binarySearch(appOpened, apkName);
            // 小于零，说明 该apk名称 不再已打开的apk名称列表里吗
            // 如果该apk没打开过
            if (index < 0) {
                // // 最后一个逗号不
                // if (count > 0) {
                // System.out.print(", ");
                // }
                count++;
                // 输出这个没打开过的apk
                // System.out.print(apkName);
                sb.append(apkName).append("\n");
            }
        }
        // 在事件调度线程中操作JTextArea以确保线程安全
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                appSignedInPanels.getNotOpened().setText(sb.toString().trim());
            }
        });
    }


    /**
     * 清空签到记录表
     */
    private void clearCheckInForm() {
        System.out.println("清空签到记录表");
        // 清空签到记录表
        Iterator<String> iOpened = appOpened.iterator();
        while (iOpened.hasNext()) {
            iOpened.next();
            iOpened.remove();
        }
    }


    /**
     * 判断 是否已经到了第2天
     *
     * @return 如果到了第2天，返回true，如果不是，返回false
     */
    private boolean isNextDay() {
        return nextDay;
    }


    /**
     * 显示已经打开的金币应用
     */
    private void showOpenedApp() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                boolean isChange = updateSignedInApp();
                if (isChange) {
                    String loginRecordsTxt = AdbTools.getInstance().getDevice().getLoginRecordsTxt();
                    loginRecords.setApps(apps.toString());
                    String appOpenedOld = loginRecords.getAppOpened();
                    String appOpenedStr = appOpened.toString();
                    if (!appOpenedStr.equals(appOpenedOld)) {
                        loginRecords.setAppOpened(appOpenedStr);
                        System.out.println("loginRecordsTxt = " + loginRecordsTxt);
                        FileUtil.writeStringToFile(loginRecords.toString(), loginRecordsTxt);
                        System.out.println("签到消息已经写入文件");
                    }
                }

            }

        });
    }

    private boolean updateSignedInApp() {
        JTextPane signedInApp = appSignedInPanels.getSignedIn();
        StyledDocument doc = signedInApp.getStyledDocument();
        // 获取签到列表的所有字符串
        String text = signedInApp.getText();
        boolean isChange = false;
        for (String s : appOpened) {
            // 如果签到记录里没有这个记录
            if (!text.contains(s + appNameEndFlag)) {
                // try {
                String newAppName = s + appNameEndFlag + "\n";
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        // 把这条记录写到签到列表末尾
                        try {
                            doc.insertString(doc.getLength(), newAppName, null);
                        } catch (BadLocationException e) {
                            e.printStackTrace();
                        }
                    }
                });

                isChange = true;
            }
        }
        return isChange;
    }

    /**
     * 如果是金币应用则添加到记录表中
     * 查找
     *
     * @param packageName apk的包名
     * @return APP的应用名
     */
    private void updateAppOpened(String packageName) {
        String appName = AdbGetPackage.getAppName(packageName);
        System.out.println("appName = " + appName);
        // 如果返回的value 不等于原来的key,说明配置文件中有这个值
        if (!appName.equals(packageName)) {
            // 在已打开过的apk名称列表中查找 当前apk名
            int i = Collections.binarySearch(appOpened, appName);
            // System.out.println("i = " + i);
            // 如果没找到
            if (i < 0) {
                // 把这个赚金币应用 添加到签到记录表中
                appOpened.add(appName);
                // 排序签到记录表，否则下次二分查找会有问题
                Collections.sort(appOpened);
            }
        }
    }

    private void copyAllAppsIntoCheckInForm() {
        System.out.println("所有的应用都复制到签到记录表中");
        // 把所有的APP都复制到签到记录表中。
        Iterator<String> iApps = apps.iterator();
        while (iApps.hasNext()) {
            String next = iApps.next();
            appOpened.add(next);
        }
    }

    public static void onNextDay() {
        nextDay = true;
    }


    /**
     * 重签之后的操作
     */
    private void nextDaySetting() {
        // 更新签到记录
        clearCheckInRecords();
        // 第二天结束了
        nextDay = false;
        // stopAppCheck = false;
        isAllAppOpened = false;
        isfrist = true;
        //        // 卸载无用APP
        //        toolsJPanels.getBtnUninstallAll().doClick();
        //        // 打开手机管家
        //        adbTools.getAdbJPanels().getBtnMobileButler().doClick();
        //        // 停止线程，防止反复触发
        //        //        ThreadSleep.minutes(1);
        //        //        adbTools.getDevice().getSerial();
        //        //        AdbCommands.home(adbTools.getDevice());
        //        adbTools.getDevice().homeBtn();
        beforeAct = null;
        // ThreadSleep.minutes(4.0);
        //        _wait(40);
    }

    public static void allAppOpened() {
        isAllAppOpened = true;
    }
    // --------------------- 前台APP线程 方法 结束  ----------------------------

}

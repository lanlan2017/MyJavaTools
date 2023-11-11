package adbs.main.run;

import adbs.cmd.CmdRun;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.app.AppPanels;
import adbs.main.ui.jpanels.check.CheckJPanels;
import adbs.main.ui.jpanels.tools.ToolsJPanels;
import adbs.main.ui.jpanels.universal.UniversalPanels;
import adbs.tools.thread.ThreadSleep;
import config.AdbToolsProperties;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ForegroundAppRun implements Runnable {

    /**
     * 保存今日签到的app名称的列表
     * <p>
     * 今日打开的APP
     */
    private ArrayList<String> appOpened = new ArrayList<>();
    /**
     * 保存所有可赚钱的APP名称的列表
     */
    private static ArrayList<String> apps;

    private static boolean nextDay = false;
    private AppPanels appPanels;
    private ToolsJPanels toolsJPanels;


    public static void updatePackages_3_money() {
        ForegroundAppRun.apps = null;
        System.out.println("更新可赚钱APP列表");
        ForegroundAppRun.apps = new AdbShellPmListPackages_3().getPackages_3_money();
        System.out.println("可赚钱APP列表: " + apps);
    }

    private static boolean stop;
    /**
     * 是否停止等待，直接进行下一步的签到检查。
     */
    private static boolean stopWait;
    /**
     * 是否所有的APP都签到完毕。
     */
    private static boolean isAllAppOpened;

    public static void allAppOpened() {
        ForegroundAppRun.isAllAppOpened = true;
    }

    /**
     * 是否停止签到检查
     */
    private boolean stopAppCheck;
    private Color background;
    private JPanel universalPanel;
    private UniversalPanels universalPanels;
    private AdbTools adbTools;

    public static void setStop(boolean stop) {
        ForegroundAppRun.stop = stop;
        // ForegroundAppRun.stopWait = true;
    }


    @Override
    public void run() {
        // 等待4秒
        ThreadSleep.seconds(4);
        // 更新操作的面板
        updatePanels();
        updatePackages_3_money();
        while (!stop) {
            body();
        }
    }

    private void updatePanels() {
        adbTools = AdbTools.getInstance();
        universalPanels = adbTools.getUniversalPanels();
        universalPanel = universalPanels.getUniversalPanel();
        appPanels = AdbTools.getInstance().getAppPanels();
        toolsJPanels = AdbTools.getInstance().getToolsJPanels();
        background = universalPanel.getBackground();
    }

    private void body() {
        String serial = adbTools.getDevice().getSerial();
        // String topActivityCommand = getTopActivityCommand(serial);
        String topActivityCommand = AdbGetPackage.getTopActivityCommand(serial);
        // System.out.println("ActivityCommand =" + topActivityCommand);
        String run = CmdRun.run(topActivityCommand).trim();
        System.out.println(run);
        // String run = AdbCommands.runAbdCmd(topActivityCommand).trim();
        // String run = CmdRun.run(topActivityCommand).trim();
        // 如果命令结果中有反斜杠，说明有包名
        if (run.contains("/")) {
            //mResumedActivity: ActivityRecord{7fbc105 u0 com.huawei.health/.MainActivity t1573}
            // String actName = AdbGetPackage.getActName(run);
            // System.out.print("act名 =" + actName + " ");
            // run = getPackageName(run);
            run = AdbGetPackage.getPackageName(run);
            // System.out.println("包名 = " + run);
            // System.out.print("包名 = " + run + " ");
            // 获取包名对应的应用名
            String appName = isGoldCoinAppAddIntoAppRecord(run);
            // updateFormTitle
            // 在窗体标题上显示当前打开的APP的名称
            updateFormTitle(appName);

            // 如果还没停止签到检查的话
            if (!stopAppCheck) {
                // 如果用户勾选了所有应用都打开了
                if (isAllAppOpened) {
                    // 清空签到记录表
                    clearCheckInForm();
                    // 把所有的APP都填到签到记录表中
                    copyAllAppsIntoCheckInForm();
                    afterOpeningAllAPKs();
                    stopAppCheck = true;
                }
                // 如果已签到列表和应用列表的长度一样，则说明所有APP都签到完毕了
                else if (appOpened.size() == apps.size()) {
                    // 签到完成设置
                    afterOpeningAllAPKs();
                    stopAppCheck = true;
                }
                // 隐藏面板，免得有问题
                if (appPanels.getAppPanel().isVisible()) {
                    // appPanels.getAppPanel().setVisible(false);
                    AdbTools.getInstance().getCheckJPanels().getSignInCheckBox().doClick();
                    AdbTools.getInstance().getFrame().pack();
                }
                // System.out.println();
                // 打印已经打开的APP
                showOpenedApp();
                // 打印没打开的APP
                showNotOpenApp();
            }

        }
        // 等待一定的时间
        wait_();
        // 如果刚好进入第2天
        if (isNextDay()) {
            // 清空前一天的签到设置
            nextDaySetting();
        }
    }

    /**
     * 重新签到设置
     */
    private void nextDaySetting() {
        // 更新签到记录
        clearCheckInRecords();
        nextDay = false;
        // stopAppCheck = false;
        isAllAppOpened = false;
        // toolsJPanels = AdbTools.getInstance().getToolsJPanels();
        // 卸载无用APP
        toolsJPanels.getBtnUninstallAll().doClick();
        // 打开手机管家
        adbTools.getAdbJPanels().getBtnMobileButler().doClick();
    }

    private void copyAllAppsIntoCheckInForm() {
        // 把所有的APP都复制到签到记录表中。
        Iterator<String> iApps = apps.iterator();
        while (iApps.hasNext()) {
            String next = iApps.next();
            appOpened.add(next);
        }
    }

    /**
     * 清空签到记录表
     */
    private void clearCheckInForm() {
        // 清空签到记录表
        Iterator<String> iOpened = appOpened.iterator();
        while (iOpened.hasNext()) {
            iOpened.next();
            iOpened.remove();
        }
    }

    /**
     * 显示已经打开的金币应用
     */
    private void showOpenedApp() {
        // 把apk的名称放到列表中
        // System.out.println("已打开:" + apkOpenedToday);
        StringBuffer sb = new StringBuffer();
        for (String s : appOpened) {
            sb.append(s).append("\n");
        }
        // appPanels = AdbTools.getInstance().getAppPanels();
        JTextArea signedInApp = appPanels.getSignedIn();
        signedInApp.setText(sb.toString().trim());
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
                //     System.out.print(", ");
                // }
                count++;
                // 输出这个没打开过的apk
                // System.out.print(apkName);
                sb.append(apkName).append("\n");
            }
        }
        // AdbTools.getInstance().getAppPanels().getNotOpened().setText(sb.toString().trim());
        appPanels.getNotOpened().setText(sb.toString().trim());
    }

    /**
     * 如果是金币应用则添加到记录表中
     * 查找
     *
     * @param packageName apk的包名
     * @return APP的应用名
     */
    private String isGoldCoinAppAddIntoAppRecord(String packageName) {
        // 获取配置文件中的值
        // String appName = AdbToolsProperties.propertiesTools.getProperty(packageName);
        String appName = AdbToolsProperties.moneyApkPro.getProperty(packageName);

        // 如果返回的value 不等于原来的key,说明配置文件中有这个值
        if (!appName.equals(packageName)) {
            // 如果保存可赚钱apk名称的配置文件中找到这个apk
            // Collections.binarySearch(apkOpenedToday,  , );

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
        } else {
            appName = "非赚钱apk";
        }
        // 输出应用的名称
        // System.out.println("应用 = " + appName);
        return appName;
    }

    /**
     * 更新窗体标题
     *
     * @param appName APP名称
     */
    private void updateFormTitle(String appName) {
        JFrame frame = adbTools.getFrame();
        String title = frame.getTitle();
        // 如果标题中有下划线，这表明标题中有旧的应用名称
        String delimiter2 = "|";
        if (title.contains(delimiter2)) {
            // 获取下划线在标题中的下标
            int nameFlagIndex = title.lastIndexOf(delimiter2) + 1;
            // 截取下旧的应用名称
            String oldAppName = title.substring(nameFlagIndex);
            // System.out.println("oldAppName =" + oldAppName+"|");

            // 如果当然的APP名称与标题中的APP名称不一样
            if (!appName.equals(oldAppName)) {
                // 从标题中截取下标题标记以前的字符串，包括标题标记
                String prefix = title.substring(0, nameFlagIndex);
                // 新的标题=应用名前面的字符串+新的应用名
                title = prefix + appName;
                frame.setTitle(title);
            }

        }
        // 如果标题以百分号结尾的话，说明还没有应用名称
        else if (title.endsWith("%")) {
            // title=title+run;
            // 标题=原来的标题+下划线+应用名
            title = title + delimiter2 + appName;
            frame.setTitle(title);
        }
    }

    /**
     * 签到完成设置
     */
    private void afterOpeningAllAPKs() {
        // // 已签到完成，停止签到检查
        // stopAppCheck = true;
        // 把apk的名称放到列表中
        // System.out.println("已打开:" + apkOpenedToday);
        System.out.println("所有的apk签到完成!");
        // // 改变背景色，表示签到完成
        universalPanel.setBackground(Color.pink);
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
            // 恢复原来的背景色，表示还没签到完成
            universalPanel.setBackground(background);
        }
    }

    /**
     * 停止等待
     */
    public static void stopWait() {
        ForegroundAppRun.stopWait = true;
    }


    private void wait_() {
        stopWait = false;
        // int seconds = 2;
        int s5 = 5;
        int s2 = 2;
        int endWait = 40;
        int count = 0;
        if (IsTest.isTest()) {
            // 测试时使用 5秒钟
            ThreadSleep.seconds(s5);
        } else {
            while (!stopWait) {
                // System.out.println("stopWait = " + stopWait);
                // 等待5秒
                ThreadSleep.seconds(s2);
                // System.out.println("签到线程等待中:" + count);
                count += s2;
                if (count >= endWait) {
                    // stopWait = true;
                    break;
                }
            }
        }

    }


    /**
     * 判断 是否已经到了第2天
     *
     * @return 如果到了第2天，返回true，如果不是，返回false
     */
    private boolean isNextDay() {
        // 获取当前的时间
        LocalDateTime localDateTime = LocalDateTime.now();
        // String format = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String format = localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("时间 = " + format);
        // 如果当前时间 在0到3分钟 之内的话，则认为现在到了第2天
        return nextDay || format.startsWith("00:00") || format.startsWith("00:01") || format.startsWith("00:02") || format.startsWith("00:03");
    }

    public static void onNextDay() {
        ForegroundAppRun.nextDay = true;
    }
}
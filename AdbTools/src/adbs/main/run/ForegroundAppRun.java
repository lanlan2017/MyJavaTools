package adbs.main.run;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
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
    private ArrayList<String> apkOpenedToday = new ArrayList<>();
    /**
     * 保存所有可赚钱的APP名称的列表
     */
    private static ArrayList<String> apps;


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
    private static boolean isAllAppSignedIn;

    public static void setIsAllAppSignedIn(boolean isAllAppSignedIn) {
        ForegroundAppRun.isAllAppSignedIn = isAllAppSignedIn;
    }

    /**
     * 判断是否需要进行签到检查
     */
    private boolean stopCheckInInspection;
    private Color background;
    private JPanel universalPanel;
    private UniversalPanels universalPanels;
    private AdbTools adbTools;

    public static void setStop(boolean stop) {
        ForegroundAppRun.stop = stop;
    }


    @Override
    public void run() {
        // 等待4秒
        ThreadSleep.seconds(4);
        adbTools = AdbTools.getInstance();
        //
        universalPanels = adbTools.getUniversalPanels();
        universalPanel = universalPanels.getUniversalPanel();
        background = universalPanel.getBackground();

        updatePackages_3_money();

        // final String serial = adbTools.getDevice().getSerial();
        String serial;
        // AdbTools adbTools = adbTools1;
        while (!stop) {
            serial = adbTools.getDevice().getSerial();
            // String topActivityCommand = getTopActivityCommand(serial);
            String topActivityCommand = AdbGetPackage.getTopActivityCommand(serial);

            System.out.println("ActivityCommand =" + topActivityCommand);
            String run = AdbCommands.runAbdCmd(topActivityCommand).trim();
            // String run = CmdRun.run(topActivityCommand).trim();
            // 如果命令结果中有反斜杠，说明有包名
            if (run.contains("/")) {
                //mResumedActivity: ActivityRecord{7fbc105 u0 com.huawei.health/.MainActivity t1573}
                String actName = AdbGetPackage.getActName(run);
                System.out.print("act名 =" + actName + " ");
                // run = getPackageName(run);
                run = AdbGetPackage.getPackageName(run);
                // System.out.println("包名 = " + run);
                System.out.print("包名 = " + run + " ");
                String appName = getAppName(run);
                // 如果还没停止签到检查的话
                if (!stopCheckInInspection) {
                    // 如果已签到列表和应用列表的长度一样，则说明所有APP都签到完毕了
                    if (apkOpenedToday.size() == apps.size()) {
                        // 签到完成设置
                        afterOpeningAllAPKs();
                    }
                    System.out.println();
                    // 把apk的名称放到列表中
                    System.out.println("已打开:" + apkOpenedToday);
                    // 打印没有打开的可赚钱APP
                    printAppNamesThatAreNotOpen();
                }

                // updateFormTitle
                // 在窗体标题上显示当前打开的APP的名称
                updateFormTitle(appName);
            }
            // 等待一定的时间
            wait_();
            // System.out.println("等待结束，，，，，，，，，，，，，，，");
            // 更新签到记录
            clearCheckInRecords();
            /**
             * 如果还没停止签到的话，并且全部签到标记被设置为true,
             * 如果所有的APP都签到完毕
             */
            if (!stopCheckInInspection && isAllAppSignedIn) {
                // 删除所有的签到记录
                Iterator<String> iterator = apkOpenedToday.iterator();
                while (iterator.hasNext()) {
                    iterator.next();
                    iterator.remove();
                }
                Iterator<String> iall = apps.iterator();
                while (iall.hasNext()) {
                    String next = iall.next();
                    apkOpenedToday.add(next);
                }
                afterOpeningAllAPKs();
            }
        }
    }

    /**
     * 打印还没打开的APP名称
     */
    private void printAppNamesThatAreNotOpen() {
        System.out.print("未打开:");
        for (int i = 0, size = apps.size(), count = 0; i < size; i++) {
            String apkName = apps.get(i);
            // 在所有的apk名称列表中查找 已打开的apk名称
            int index = Collections.binarySearch(apkOpenedToday, apkName);
            // 小于零，说明 该apk名称 不再已打开的apk名称列表里吗
            // 如果该apk没打开过
            if (index < 0) {
                // 最后一个逗号不
                if (count > 0) {
                    System.out.print(", ");
                }
                count++;
                // 输出这个没打开过的apk
                System.out.print(apkName);
            }
        }
        System.out.println();
        // System.out.println();
    }

    /**
     * 读取配置文件，根据当前APP的包名，获取APP的名称
     *
     * @param packageName apk的包名
     * @return APP的应用名
     */
    private String getAppName(String packageName) {
        // 获取配置文件中的值
        // String appName = AdbToolsProperties.propertiesTools.getProperty(packageName);
        String appName = AdbToolsProperties.moneyApkPro.getProperty(packageName);

        // 如果返回的value 不等于原来的key,说明配置文件中有这个值
        // 如果保存可赚钱apk名称的配置文件中找到这个apk
        if (!appName.equals(packageName)) {
            // Collections.binarySearch(apkOpenedToday,  , );
            // 在已打开过的apk名称列表中查找 当前apk名
            int i = Collections.binarySearch(apkOpenedToday, appName);
            // System.out.println("i = " + i);
            // 如果没找到
            if (i < 0) {
                // 把当前的apk名称添加到列表中
                apkOpenedToday.add(appName);
                // 排序，否则下次二分查找会有问题
                Collections.sort(apkOpenedToday);
            }
        } else {
            appName = "非赚钱apk";
        }
        // 输出应用的名称
        System.out.println("应用 = " + appName);
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
        // 已签到完成，停止签到检查
        stopCheckInInspection = true;
        // 把apk的名称放到列表中
        System.out.println("已打开:" + apkOpenedToday);
        System.out.println("所有的apk签到完成!");
        // // 改变背景色，表示签到完成
        universalPanel.setBackground(Color.pink);
    }


    /**
     * 清空签到记录
     * clearCheckInRecords
     */
    private void clearCheckInRecords() {
        // 在凌晨的时候，移除所有apk的打开记录
        if (isNextDay() && apkOpenedToday.size() > 0) {
            // 遍历签到记录列表
            Iterator<String> iterator = apkOpenedToday.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                // 删除签到记录
                iterator.remove();
            }
            // 开启签到检查
            stopCheckInInspection = false;
            // 恢复原来的背景色，表示还没签到完成
            universalPanel.setBackground(background);
        }
    }

    /**
     * 停止等待
     *
     * @param stopWait
     */
    public static void setStopWait(boolean stopWait) {
        ForegroundAppRun.stopWait = stopWait;
    }


    private void wait_() {
        // int seconds = 2;
        int seconds = 16;
        if (IsTest.isTest()) {
            // 测试时使用 5秒钟
            ThreadSleep.seconds(seconds);
        } else {
            // System.out.println("非测，，，，，，，，，，，，，，，，，，，，试");
            // 运行时使用 1分钟
            // ThreadSleep.minutes(1);
            // ThreadSleep.seconds(45);
            int endWait = 40;
            int count = 0;
            //
            while (!stopWait) {
                // System.out.println("stopWait = " + stopWait);
                // 等待5秒
                ThreadSleep.seconds(seconds);
                count += seconds;
                if (count >= endWait) {
                    stopWait = true;
                    break;
                }
            }
            // System.out.println();
            // System.out.println("count = " + count);
        }
        // System.out.println("等待结束。。。。。。。。。。。。。。。。。。。");
        stopWait = false;
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
        return format.startsWith("00:00") || format.startsWith("00:01") || format.startsWith("00:02") || format.startsWith("00:03");
    }

    // /**
    //  * 根据不同的手机序列号返回不同的查询当前activity的adb命令
    //  *
    //  * @param id 手机的序列号
    //  * @return 查询当前activity的adb命令
    //  */
    // private String getTopActivityCommand(String id) {
    //     String code;
    //     if (id.equals("jjqsqst4aim7f675")) {
    //         code = "adb -s " + id + " shell dumpsys activity | findstr topResumedActivity";
    //     } else {
    //         code = "adb -s " + id + " shell dumpsys activity | findstr \"mResume\"";
    //     }
    //     return code;
    // }
}

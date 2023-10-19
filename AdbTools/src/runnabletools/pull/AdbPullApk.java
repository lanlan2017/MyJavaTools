package runnabletools.pull;

import adbs.cmd.AdbCommands;
import adbs.cmd.CmdRun;
import adbs.model.Device;
import runnabletools.install.AdbInstall;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class AdbPullApk {
    // private static Scanner sysInscan = new Scanner(System.in);
    private static Scanner sysInscan = new Scanner(System.in, "GBK");
    /**
     * 要执行的adb命令
     */
    private static String command = "";
    private static Component parentComponent;
    // private static Device device;

    public static void main(String[] args) {
        // 显示当前电脑上连接的Android设备，并让用户选择要操作的设备
        Device device = showDeviceGetSelected();

        System.out.print("输入apk在电脑上的名称:");
        String apkName = sysInscan.nextLine();

        pullTopApk(device, apkName);
    }

    public static Component getParentComponent() {
        return parentComponent;
    }

    public static void setParentComponent(Component parentComponent) {
        AdbPullApk.parentComponent = parentComponent;
    }

    private static Device showDeviceGetSelected() {
        // 执行 adb devices -l 命令获取设备名称和设备对象的map
        LinkedHashMap<String, Device> deviceMap = AdbInstall.getDeviceLinkedHashMap();
        // 获取用户选择的设备名称
        String name = AdbInstall.getUserSelectedName(deviceMap);
        // 根据用户选择的设备名称从map中取出 设备对象
        // device = deviceMap.get(name);
        // return device;
        return deviceMap.get(name);
    }

    public static void pullTopApk(Device device, String apkName) {
        // 执行命令查询 顶部APP的activity
        String topActivity = getTopActivity(device);
        if (isAct(topActivity)) {
            // System.out.print("输入apk在电脑上的名称:");
            // 把顶部APP在Android中的apk拉取到电脑上
            pullTopActivityApk(device, topActivity, apkName);
        }

    }

    /**
     * 执行adb命令查询当前前天界面的activity
     *
     * @param device
     * @return adb shell dumpsys window | findstr mCurrentFocus命令执行的结果
     */
    public static String getTopActivity(Device device) {
        // echo oppo activity  && adb -s 75aed56d shell dumpsys window | findstr mCurrentFocus
        command = "adb -s " + device.getSerial() + " shell dumpsys window | findstr mCurrentFocus";
        System.out.println("command = " + command);
        String run = CmdRun.run(command).trim();
        System.out.println("run = " + run);
        return run;
    }

    public static void pullTopActivityApk(Device device, String activityOut, String apkName) {
        // if (isAct(activityOut)) {
        // 查询apk在Android中的绝对路径
        String apkPath = findApk(device, activityOut);
        if (isApkNameInAndroid(apkPath)) {
            // System.out.print("输入apk在电脑上的名称:");
            adbPullApk(device, apkPath, apkName);
        }
        // }
    }


    private static String findApk(Device device, String activityOut) {
        // 从activity输出中取出包名
        String packagename = activityOut.substring(0, activityOut.lastIndexOf("/"));
        packagename = packagename.substring(packagename.lastIndexOf(" ") + 1);
        System.out.println("packagename =|" + packagename + "|");

        // echo 找bai  apk  && adb -s U8ENW18117021408 shell pm path 包名
        command = "adb -s " + device.getSerial() + " shell pm path " + packagename;
        // String findApkCommandOut = CmdRun.run(command).trim();
        String findApkCommandOut = AdbCommands.runAbdCmd(command);
        System.out.println("findApkCommandOut = " + findApkCommandOut);
        return findApkCommandOut;
    }

    private static void adbPullApk(Device device, String findApkCommandOut, String apkNameInAndroid) {
        String apkPath = findApkCommandOut.substring(findApkCommandOut.indexOf("package:") + "package:".length());
        System.out.println("apkPath = " + apkPath);
        // echo 从bai 获取  && adb -s U8ENW18117021408 pull xx.apk yy.apk

        // System.out.println("command = " + command);
        // System.out.print("输入apk在电脑上的名称:");
        if (!"".equals(apkNameInAndroid)) {
            if (!apkNameInAndroid.endsWith(".apk")) {
                apkNameInAndroid += ".apk";
            }
            File apkDir = new File("D:\\网络共享\\可读可写\\apk\\");
            command = "adb -s " + device.getSerial() + " pull " + apkPath + " ";
            if (apkDir.isDirectory()) {
                // command = command + "D:\\网络共享\\可读可写\\apk\\";
                command = "D: && cd D:\\网络共享\\可读可写\\apk\\ && " + command;
            }
            // command = command + apkNameInAndroid+"&&D:&&D:\\网络共享\\只读";
            command = command + apkNameInAndroid;
            System.out.println("command = " + command);

            String adbPullOut = CmdRun.run(command);
            System.out.println("adbPullOut = " + adbPullOut);

            if (adbPullOut.contains("bytes in")) {
                JOptionPane.showConfirmDialog(parentComponent, "apk提取成功");
            }else {
                JOptionPane.showConfirmDialog(parentComponent, "apk提取失败");
            }

        }
    }

    private static boolean isApkNameInAndroid(String findapk) {
        return findapk.startsWith("package:/data/app/");
    }

    private static boolean isAct(String run) {

        boolean hasBackslash = false;
        boolean hasSpaces = false;
        boolean hasDotNumber = false;
        for (int i = 0, length = run.length(); i < length; i++) {
            char charAt = run.charAt(i);
            if (!hasDotNumber && charAt == '.') {
                hasDotNumber = true;
            } else if (!hasSpaces && charAt == ' ') {
                hasSpaces = true;
            } else if (!hasBackslash && charAt == '/') {
                hasBackslash = true;
            }
        }
        return hasBackslash && hasSpaces && hasDotNumber;
    }
}

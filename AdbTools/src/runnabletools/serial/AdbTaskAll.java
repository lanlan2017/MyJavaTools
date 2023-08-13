package runnabletools.serial;

import adbs.cmd.AdbCommands;
import adbs.model.Device;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;

public class AdbTaskAll {
    public static void main(String[] args) {
        String argsStr = "";
        for (int i = 0; i < args.length; i++) {
            argsStr += " " + args[i];
        }
        argsStr = argsStr.trim();
        System.out.println("argsStr ='" + argsStr + "'");
        if ("".equals(argsStr)) {
            taskAll();
            // } else if (argsStr.startsWith("kill")) {
        } else if (argsStr.matches("kill")) {
            taskKillOpenAll(true, false);
        } else if (argsStr.matches("kill yd")) {
            taskKillOpenAll(true, true);
        }


    }

    /**
     * 打开所有的任务视图界面
     */
    private static void taskAll() {
        taskKillOpenAll(false, false);
    }

    /**
     * 打开所有设备的任务视图界面,并点击底部的清除按钮。
     */
    private static void taskKillOpenAll(boolean killAll, boolean open) {
        // 获取当前电脑上的所有adb设备的LinkedHashMap集合
        LinkedHashMap<String, Device> simpleId_Device_map = Devices.getStringDeviceLinkedHashMap();
        System.out.println("-------------------------");
        // System.out.println("通过map打印");
        Set<Map.Entry<String, Device>> entries = simpleId_Device_map.entrySet();
        entries.forEach(new Consumer<Map.Entry<String, Device>>() {
            @Override
            public void accept(Map.Entry<String, Device> e) {
                System.out.println(e.getKey());
                Device device = e.getValue();
                String serial1 = device.getSerial();
                String serial = serial1;
                // 按任务键
                taskView(serial);
                // 按任务键底部的清除按钮
                if (killAll) {
                    clickDeleteBtn(device);
                    // 按home键
                    home(serial);
                }
                // 打开运动健康APP
                if (open) {
                    openSportsAndHealthApp(serial);
                }
            }
        });
        System.out.println("-------------------------");
    }

    private static void taskView(String serial) {
        //    adb -s _id_ shell input keyevent 187
        String code = "adb -s " + serial + " shell input keyevent 187";
        // x=540,y=1930
        AdbCommands.runAbdCmd(code);
    }

    private static void home(String serial) {
        //    adb -s _id_ shell input keyevent 187
        String code = "adb -s " + serial + " shell input keyevent 3";
        // x=540,y=1930
        AdbCommands.runAbdCmd(code);
    }

    /**
     * 打开运动健康APP
     * openSportsAndHealthApp
     *
     * @param serial adb设备的序列号
     */
    private static void openSportsAndHealthApp(String serial) {
        // String openYunDong="adb -s 75aed56d shell am start -n com.kmxs.reader/.home.ui.HomeActivity";
        // String openYunDong="adb -s 75aed56d shell am start -n com.huawei.health/.home.ui.HomeActivity";
        // String openYunDong = "adb -s 75aed56d shell am start -n com.huawei.health/.MainActivity";
        if (!"75aed56d".equals(serial) && !"jjqsqst4aim7f675".equals(serial)) {
            String huaWaiYunDong = "adb -s " + serial + " shell am start -n com.huawei.health/.MainActivity";
            // 打开华为运动健康
            AdbCommands.runAbdCmd(huaWaiYunDong);
        }
    }

    /**
     * 点击删除任务按钮
     * 点击底部的删除按钮
     *
     * @param device
     */
    private static void clickDeleteBtn(Device device) {
        int width = device.getWidth();
        int height = device.getHeight();
        String serial = device.getSerial();
        // System.out.println("width=" + width + " ,height=" + height);
        int x, y;
        // ThreadSleep.seconds(2);
        // width=1080 ,height=2160
        // int x = 540, y = 1930;
        x = (int) (width * 0.5);
        if ("75aed56d".equals(serial)) {
            y = (int) (height * 0.8);
        } else {
            y = (int) (height * 0.9);
        }
        // System.out.println("x = " + x+",y = " + y);

        String tapCode = "adb -s " + serial + " shell input tap " + x + " " + y;
        AdbCommands.runAbdCmd(tapCode);
    }
}

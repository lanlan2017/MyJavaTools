package runnabletools.serial;

import adbs.cmd.AdbCommands;
import adbs.main.AdbTools;
import adbs.main.ui.jpanels.scrcpy.OpenApp;
import adbs.model.Device;
import adbs.tools.thread.ThreadSleep;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class AdbAll {
    public static void main(String[] args) {
        String argsStr = "";
        for (int i = 0; i < args.length; i++) {
            argsStr += " " + args[i];
        }
        argsStr = argsStr.trim();
        System.out.println("argsStr ='" + argsStr + "'");
        switch (argsStr) {
            case "yd":
                taskKillOpenAll(false, false, false, true);
                break;
            case "home":
                taskKillOpenAll(false, false, true, false);
                break;
            case "task":
            case "task home":
                taskKillOpenAll(true, false, true, false);
                break;
            case "task kill":
            case "task kill home":
                taskKillOpenAll(true, true, true, false);
                break;
            case "task kill yd":
            case "task kill home yd":
                taskKillOpenAll(true, true, true, true);
                // taskKillOpenAll(true, true, true, true);
                break;

        }

    }

    /**
     * 打开所有设备的任务视图界面,并点击底部的清除按钮。
     *
     * @param task    是否按触发键
     * @param killAll 杀死所有的前台APP
     * @param open    是否打开运动健康
     */
    private static void taskKillOpenAll(boolean task, boolean killAll, boolean home, boolean open) {
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

                if (task) {
                    // 按任务键
                    taskView(serial);
                }
                // 按任务键底部的清除按钮
                if (killAll) {
                    clickDeleteBtn(device);
                }
                if (home) {
                    // 按home键
                    home(serial);
                }
                // 打开运动健康APP
                if (open) {
                    // openSportsAndHealthApp(serial);
                    // OpenApp.openPedometerAPP();
                    // wait_TaskBtn();
                    OpenApp.openPedometerAPP(device);
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

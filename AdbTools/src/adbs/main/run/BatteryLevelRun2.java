package adbs.main.run;

import adbs.cmd.CmdRun;
import adbs.main.AdbTools;
import adbs.tools.thread.ThreadSleep;
import config.AdbToolsProperties;
import tools.config.properties.PropertiesTools;

import javax.swing.*;

public class BatteryLevelRun2 implements Runnable {
    private static boolean stop = false;

    @Override
    public void run() {
        // 等待2秒
        ThreadSleep.seconds(2);

        AdbTools adbTools = AdbTools.getInstance();
        String serial = adbTools.getDevice().getSerial();
        String name = adbTools.getDevice().getName();
        BatteryModel batteryModel = new BatteryModel(serial);
        batteryModel.update();
        // System.out.print("--------------------------");
        // System.out.print(batteryModel);
        // System.out.println("--------------------------");
        while (!stop) {
            // 更新电池信息
            batteryModel.update();
            // 获取电池电量百分比
            int level = batteryModel.getLevel();

            // 更新电池信息，并且判断是否需要使用充电头充电
            if (batteryModel.needAcPower()) {
                // 获取电池电量
                String message = "电量:" + level + "% 充电?";
                System.out.println(message);
                // 弹出确认对话框
                // int confirmDialog = JOptionPane.showConfirmDialog(null, message);
                // 弹出确认对话框，显示标题，显示“是，否，取消”三个按钮

                // int confirmDialog = JOptionPane.showConfirmDialog(null, message,name,JOptionPane.YES_NO_CANCEL_OPTION);
                // int confirmDialog = JOptionPane.showConfirmDialog(adbTools.getContentPane(), message, name, JOptionPane.YES_NO_OPTION);
                int confirmDialog = JOptionPane.showConfirmDialog(adbTools.getContentPane(), message, name, JOptionPane.YES_NO_CANCEL_OPTION);
                switch (confirmDialog) {
                    case JOptionPane.OK_OPTION:
                        System.out.println("点击 是 按钮");

                        PropertiesTools propertiesTools = AdbToolsProperties.propertiesTools;
                        String IpPort = propertiesTools.findKey(name + "-");
                        // 如果找到对应的可以
                        if (!IpPort.equals(PropertiesTools.withoutThisVlaue)) {
                            System.out.println("IP:端口=" + IpPort);
                            String port = IpPort.substring(IpPort.lastIndexOf(":") + ":".length());
                            System.out.println("port = " + port);
                            String code = "adb -s " + serial + " tcpip " + port + " && adb connect " + IpPort;
                            System.out.println("code = " + code);
                            CmdRun.run("adb -s " + serial + " tcpip 5560 && adb connect 192.168.0.109:5560");
                        } else {
                            System.out.println("没有在配置文件中配置 设备：“" + name + "”的网络地址");
                        }
                        break;
                    case JOptionPane.NO_OPTION:
                        System.out.println("点击 否 按钮");
                        break;
                    case JOptionPane.CANCEL_OPTION:
                        System.out.println("点击 取消 按钮");
                        // 停止电池检测线程
                        stop = true;
                        break;
                }
            }
            JFrame frame = adbTools.getFrame();
            // 缓存旧标题
            String oldFrameTitle = frame.getTitle();

            String frameTitle = oldFrameTitle;
            // 如果原来的标题中已经有了百分号
            if (frameTitle.contains("%")) {
                // System.out.println("old frameTitle = " + frameTitle);
                //替换其中的百分号
                frameTitle = frameTitle.replaceAll(":[0-9]{1,2}%", ":" + level + "%");
                // System.out.println("new frameTitle = " + frameTitle);
            } else {
                // 在标题上加上百分号
                frameTitle = frameTitle + ":" + level + "%";
            }
            // 如果标题有改变的话
            if (!frameTitle.equals(oldFrameTitle)) {
                // System.out.println("update Title");
                frame.setTitle(frameTitle);
            }

            ThreadSleep.minutes(2);
            // for test
            // ThreadSleep.seconds(2);
        }
    }

    public static void main(String[] args) {
        new Thread(new BatteryLevelRun2()).start();
    }
}

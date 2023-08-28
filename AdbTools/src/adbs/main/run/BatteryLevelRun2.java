package adbs.main.run;

import adbs.cmd.CmdRun;
import adbs.main.AdbTools;
import adbs.tools.thread.ThreadSleep;
import config.AdbToolsProperties;
import tools.config.properties.PropertiesTools;

import javax.swing.*;

public class BatteryLevelRun2 implements Runnable {
    private static boolean stop = false;
    private JFrame frame;
    private AdbTools adbTools;
    private String serial;
    private String name;
    private BatteryModel batteryModel;


    @Override
    public void run() {
        // 等待2秒
        ThreadSleep.seconds(2);

        adbTools = AdbTools.getInstance();
        // // 获取设备的序列号
        // serial = adbTools.getDevice().getSerial();
        // 获取设备名
        name = adbTools.getDevice().getName();
        // 根据序列号 创建电池模型对象
        batteryModel = new BatteryModel(serial);
        // 更新电池信息
        batteryModel.update();
        frame = adbTools.getFrame();

        stop = false;
        // System.out.print("--------------------------");
        // System.out.print(batteryModel);
        // System.out.println("--------------------------");
        while (!stop) {
            serial = adbTools.getDevice().getSerial();
            batteryModel.setSerial(serial);
            // 更新电池信息
            batteryModel.update();
            // 获取电池电量百分比
            int level = batteryModel.getLevel();
            System.out.println("level = " + level);
            // 更新窗体标题中的电池电量值
            updateJFrameTitle(level);
            // 并且判断是否需要使用充电头充电
            if (batteryModel.needAcPower()) {
                // 弹窗提醒用户充电
                remindAC(level);
            }
            wait_();

        }
    }

    private void wait_() {
        if (IsTest.isIsTest()) {
            ThreadSleep.seconds(20);
        } else {
            // 等待一段时间，再进行更新电池信息
            ThreadSleep.minutes(2);
        }
    }

    /**
     * 提醒用户使用充电头充电
     *
     * @param level
     */
    private void remindAC(int level) {
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

    /**
     * 更新JFrame标题中的电池电量信息。
     *
     * @param level 当前的电池电量
     */
    private void updateJFrameTitle(int level) {
        // 缓存旧标题
        String oldFrameTitle = frame.getTitle();

        String newFrameTitle = oldFrameTitle;
        // 如果原来的标题中已经有了百分号
        String delimiter1 = ",";
        if (newFrameTitle.contains("%")) {
            // System.out.println("old newFrameTitle = " + newFrameTitle);
            //替换其中的百分号
            // newFrameTitle = newFrameTitle.replaceAll(":[0-9]{1,2}%", ":" + level + "%");
            String previous = newFrameTitle.substring(0, newFrameTitle.indexOf(delimiter1));
            // System.out.println("previous = " + previous);
            // 简写端口号
            previous = portAbbr(previous);
            // System.out.println("previous = " + previous);
            String behind = newFrameTitle.substring(newFrameTitle.indexOf("%"));
            // System.out.println("level = " + level);
            // System.out.println("behind = " + behind);
            newFrameTitle = previous + delimiter1 + level + behind;
            // System.out.println("title:" + newFrameTitle + "_");
            // System.out.println("new newFrameTitle = " + newFrameTitle);
        } else {
            // 在标题上加上百分号
            newFrameTitle = oldFrameTitle + delimiter1 + level + "%";
        }

        // System.out.println("oldFrameTitle = " + oldFrameTitle);
        // System.out.println("newFrameTitle = " + newFrameTitle);
        // 如果标题有改变的话
        if (!newFrameTitle.equals(oldFrameTitle)) {
            System.out.println("。。。。。。。。。。。update Title、、、、、、、、、、、");
            frame.setTitle(newFrameTitle);
            // frame.pack();
        }
    }

    private String portAbbr(String ip_port) {
        if (ip_port.matches("[0-9.:]+")) {
            // System.out.println("序列号是IP地址");
            ip_port = ip_port.substring(ip_port.length() - 2);
        }
        return ip_port;
    }

    public static void stop() {
        BatteryLevelRun2.stop = true;
    }

    public static void main(String[] args) {
        new Thread(new BatteryLevelRun2()).start();
    }
}

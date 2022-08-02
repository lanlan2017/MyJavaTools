package adbs.test;

import adbs.cmd.CmdRun;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 设备单选按钮监听器
 */
public class DeviceListener implements ActionListener {
    JFrame frame;
    /**
     * 设备id
     */
    private static String phoneId;
    /**
     * 设备的宽度(像素)
     */
    private static int width;
    /**
     * 设备的高度(像素)
     */
    private static int height;

    public DeviceListener(JFrame frame) {
        this.frame = frame;
    }

    public static String getPhoneId() {
        return phoneId;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // 获取触发事件的按钮
        AbstractButton button = (AbstractButton) e.getSource();
        // 输出按钮中的文本
        String buttonText = button.getText();
        System.out.println("你选择了:" + buttonText);
        frame.setTitle(buttonText);
        String phoneId = Device.map.get(buttonText);
        String run = CmdRun.run("adb -s " + phoneId + " shell wm size").trim();
        System.out.println("run =" + run);
        // int width = 0;
        // int height = 0;
        if (run.startsWith("Physical size")) {
            String flag = "Physical size: ";
            String widthStr = run.substring(run.indexOf(flag) + flag.length(), run.lastIndexOf("x"));
            String heightStr = run.substring(run.lastIndexOf("x") + "x".length());

            if (widthStr.matches("[0-9]+")) {
                width = Integer.parseInt(widthStr);
            }
            if (heightStr.matches("[0-9]+")) {
                height = Integer.parseInt(heightStr);
            }
        }
        DeviceListener.phoneId = phoneId;
    }
}
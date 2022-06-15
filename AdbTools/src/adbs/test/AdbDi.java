package adbs.test;

import adbs.cmd.AdbCommands;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class AdbDi {
    // public static void main(String[] args) {
    //     JFrame frame = new JFrame("测试自动生成设备列表");
    //     JPanel panel = new AdbDi().createDevicesPanel();
    //     if (panel != null) {
    //         frame.add(panel);
    //         frame.pack();
    //         frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    //         frame.setAlwaysOnTop(true);
    //         frame.setVisible(true);
    //     }
    // }
    JFrame frame;

    public AdbDi(JFrame frame) {
        this.frame = frame;
    }

    /**
     * 执行adb devices -l命令，如果存在设备的话，则生成存放这些设备的单选按钮组
     *
     * @return 如果存在设置，则返回包含这些设备的JPanel
     */
    public JPanel createDevicesPanel() {
        // JPanel panel = null;
        JPanel panel = new JPanel();
        String devicesListStr = AdbCommands.runAbdCmd("adb devices -l");
        Collection<Device> devices = new LinkedHashSet<>();
        Scanner scanner = new Scanner(devicesListStr);
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            // System.out.println("line = " + line);
            if (!line.equals("List of devices attached") && !"".equals(line)) {
                String[] deviceStrs = line.split("[ ]{2,}");
                // System.out.println("ID = " + deviceStrs[0]);
                // System.out.println("dir = " + deviceStrs[1]);
                Device device = new Device(deviceStrs[0], deviceStrs[1]);
                // if (devices.contains(device))
                devices.add(device);
            }
        }
        // System.out.println(devices.size());
        if (devices.size() > 0) {
            // 创建单选按钮的容器
            panel = new JPanel();
            // 床架按钮组
            ButtonGroup buttonGroup = new ButtonGroup();
            // 设置布局管理区
            panel.setLayout(new FlowLayout());

            JPanel finalPanel = panel;
            // 创建设备单选按钮的事件监听器
            DeviceRadioBtAcListener listener = new DeviceRadioBtAcListener(frame);

            AtomicBoolean isFirst = new AtomicBoolean(true);
            devices.forEach(device -> {
                // 创建一个单选按钮
                JRadioButton deviceRadioButton = new JRadioButton(device.getSimpleId(device.getId()));
                deviceRadioButton.addActionListener(listener);
                if (isFirst.get()) {
                    // 默认选择第1个单选按钮
                    deviceRadioButton.doClick();
                    isFirst.set(false);
                }
                // 添加到按钮组中
                buttonGroup.add(deviceRadioButton);
                // 添加到面板中
                finalPanel.add(deviceRadioButton);
            });
        }
        return panel;
    }
}

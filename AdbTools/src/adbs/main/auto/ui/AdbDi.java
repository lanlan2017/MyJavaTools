package adbs.main.auto.ui;

import adbs.cmd.AdbCommands;
import adbs.main.auto.listener.Device;
import adbs.main.auto.listener.DeviceListener;
import adbs.main.auto.ui.config.FlowLayouts;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 根据adb devices -l命令的结果，返回包换设备的单选框的JPanel
 */
public class AdbDi {
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
        JPanel panel = new JPanel();
        Collection<Device> devices = new LinkedHashSet<>();
        // 执行adb devices -l命令
        String devicesListStr = AdbCommands.runAbdCmd("adb devices -l");
        // 分析adb devices -l命令结果
        Scanner scanner = new Scanner(devicesListStr);
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            // System.out.println("line = " + line);
            // List of devices attached表示没有设备，
            // 如果是设备输出信息
            if (!line.equals("List of devices attached") && !"".equals(line)) {
                // 按两个或者更多的空格符作为分界 来分割字符串
                String[] deviceStrs = line.split("[ ]{2,}");
                // System.out.println("ID = " + deviceStrs[0]);
                // System.out.println("dir = " + deviceStrs[1]);
                // 分割得到的第1段是设备id，第2段是设备的描述信息
                Device device = new Device(deviceStrs[0], deviceStrs[1]);
                // if (devices.contains(device))
                // 将这个设备添加到集合中
                devices.add(device);
            }
        }
        // System.out.println(devices.size());
        // 如果存在设备的话
        if (devices.size() > 0) {
            // 创建单选按钮的容器
            panel = new JPanel();
            // 创建按钮组
            ButtonGroup buttonGroup = new ButtonGroup();
            // 设置布局管理器
            // panel.setLayout(new FlowLayout());
            panel.setLayout(FlowLayouts.flowLayoutLeft);

            JPanel finalPanel = panel;
            // 创建设备单选按钮的事件监听器
            // DeviceListener listener = new DeviceListener(frame);
            // DeviceListener listener = new DeviceListener(frame);
            // 设置原子布尔值
            AtomicBoolean isFirst = new AtomicBoolean(true);
            // 遍历设备集合
            devices.forEach(device -> {
                // 为每一个设备，
                // 创建一个单选按钮
                JRadioButton deviceRadioButton = new JRadioButton(device.getSimpleId(device.getId()));
                // 为这个设备创建监听器
                DeviceListener listener = new DeviceListener(frame, device);
                // 设置监听器
                deviceRadioButton.addActionListener(listener);
                // 默认为true，也就是第1个
                if (isFirst.get()) {
                    // 默认选择第1个单选按钮
                    deviceRadioButton.doClick();
                    // 改变标记，后面不再进入此代码块
                    isFirst.set(false);
                }
                // 添加该单选按钮 到按钮组中
                buttonGroup.add(deviceRadioButton);
                // 添加该按钮到设备面板中
                finalPanel.add(deviceRadioButton);
            });
        }
        return panel;
    }
}

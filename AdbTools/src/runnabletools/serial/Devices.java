package runnabletools.serial;

import adbs.cmd.AdbCommands;
import adbs.model.Device;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class Devices {
    /**
     * 获取保存所有adb设备的LinkedHashMap
     *
     * @return
     */
    public static LinkedHashMap<String, Device> getStringDeviceLinkedHashMap() {
        // 设备别名
        LinkedHashMap<String, Device> simpleId_Device_map = new LinkedHashMap<>();
        // 执行adb命令
        String devicesListStr = AdbCommands.runAbdCmd("adb devices -l");
        // 分析adb devices -l命令结果
        Scanner scanner = new Scanner(devicesListStr);
        String line;
        while (scanner.hasNextLine()) {
            // 逐行读入
            line = scanner.nextLine();
            // System.out.println("line = " + line);
            // List of devices attached表示没有设备，
            // 如果是设备输出信息
            if (!line.equals("List of devices attached") && !"".equals(line)) {
                // 按两个或者更多的空格符作为分界 来分割字符串
                String[] deviceStrs = line.split("[ ]{2,}");
                // System.out.println("ID = " + deviceStrs[0]);
                // System.out.println("dir = " + deviceStrs[1]);
                // 创建设备对象
                // 分割得到的第1段是设备id，第2段是设备的描述信息
                Device device = new Device(deviceStrs[0], deviceStrs[1]);
                // 添加设备的id到列表中
                // NameList.add(device.getName());
                // System.out.println(device.getName());
                // 把id和设备作为键值对放入map中
                simpleId_Device_map.put(device.getName(), device);
            }
        }
        return simpleId_Device_map;
    }
}

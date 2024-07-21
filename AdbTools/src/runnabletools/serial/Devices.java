package runnabletools.serial;

import adbs.cmd.AdbCommands;
import adbs.main.run.IsTest;
import adbs.model.Device;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Devices {


    private static LinkedHashMap<String, Device> serial_device_map;
    private static int size;

    static void printConnectedDevices() {
        LinkedHashMap<String, Device> connectedDeviceMap = Devices.getStringDeviceLinkedHashMap();
        Devices.printConnectedDevices(connectedDeviceMap);
    }

    public static LinkedHashMap<String, Device> getSerial_device_map() {
        if (serial_device_map == null) {
            serial_device_map = getStringDeviceLinkedHashMap();
        }
        return serial_device_map;
    }

    public static int getSize() {
        return size;
    }

    /**
     * 获取保存所有adb设备的LinkedHashMap
     *
     * @return
     */
    private static LinkedHashMap<String, Device> getStringDeviceLinkedHashMap() {
//         serial
        // 设备别名
        serial_device_map = new LinkedHashMap<>();
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
                serial_device_map.put(device.getName(), device);
            }
        }
        scanner.close();
        size = serial_device_map.size();
        return serial_device_map;
    }

    /**
     * 打印已连接到电脑上的所有设备
     *
     * @param connectedDeviceMap
     */
    public static void printConnectedDevices(LinkedHashMap<String, Device> connectedDeviceMap) {
        int size = connectedDeviceMap.size();
        if (size > 0) {
            boolean isTest = IsTest.isTest();
            String formatTest = "%4d %-8s%-22s%-6s%-6s\n";
            String formatRun = "%4d %-8s%-22s\n";
            printTitle(isTest, formatTest, formatRun);
            printConnectedDevices(connectedDeviceMap, isTest, formatTest, formatRun);
        }
    }

    private static void printTitle(boolean isTest, String formatTest, String formatRun) {
        if (isTest) {
            System.out.printf(formatTest.replace("d", "s"), "num", "name", "Serial", "width", "height");
        } else {
            System.out.printf(formatRun.replace("d", "s"), "num", "name", "Serial");
        }
    }

    private static void printConnectedDevices(LinkedHashMap<String, Device> deviceLinkedHashMap, boolean isTest, String formatTest, String formatRun) {
        deviceLinkedHashMap.entrySet().forEach(new Consumer<Map.Entry<String, Device>>() {
            int count = 0;

            @Override
            public void accept(Map.Entry<String, Device> stringDeviceEntry) {
                String key = stringDeviceEntry.getKey();
                Device device = stringDeviceEntry.getValue();
//                count++;
                // System.out.println(count + " :" + key);
                // System.out.printf(formatTest, count, key, device.getSerial(), device.getWidth(), device.getHeight());

                if (isTest) {
                    System.out.printf(formatTest, count, key, device.getSerial(), device.getWidth(), device.getHeight());
                } else {
                    System.out.printf(formatRun, count, key, device.getSerial());
                }
                count++;

                // // ArrayList<String> list = Collections.list(names);
                // int i = Collections.binarySearch(list, key);
                // if (i < 0) {
                //     unconnectedNames.add(k)
                // }

            }
        });
    }
}

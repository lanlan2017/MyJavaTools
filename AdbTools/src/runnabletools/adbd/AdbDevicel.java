package runnabletools.adbd;

import adbs.model.Device;
import config.AdbToolsProperties;
import runnabletools.serial.Devices;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 获取Android设备列表
 * adbd.bat命令
 */
public class AdbDevicel {
    public static void main(String[] args) {
//        LinkedHashMap<String, Device> connectedDeviceMap = Devices.getStringDeviceLinkedHashMap();
        LinkedHashMap<String, Device> connectedDeviceMap = Devices.getSerial_device_map();
        Devices.printConnectedDevices(connectedDeviceMap);
        System.out.println();
        printUnconnectedDevices(connectedDeviceMap);
    }

//    /**
//     * 打印已连接到电脑上的所有设备
//     *
//     * @param connectedDeviceMap
//     */
//    private static void printConnectedDevices(LinkedHashMap<String, Device> connectedDeviceMap) {
//        int size = connectedDeviceMap.size();
//        if (size > 0) {
//            boolean isTest = IsTest.isTest();
//            String formatTest = "%4d %-8s%-22s%-6s%-6s\n";
//            String formatRun = "%4d %-8s%-22s\n";
//            printTitle(isTest, formatTest, formatRun);
//            printConnectedDevices(connectedDeviceMap, isTest, formatTest, formatRun);
//        }
//    }

    private static void printUnconnectedDevices(LinkedHashMap<String, Device> connectedDeviceMap) {
        int size = connectedDeviceMap.size();

        // 获取配置文件中的所有设备名列表
        Set<String> setSerialInFile = AdbToolsProperties.propertiesTools.properties.stringPropertyNames();

        // 大于零表示有连接的设备
        if (size > 0) {
            // 移除已经连接在电脑上的设备的序列或
            removeConnectedSerialInFile(connectedDeviceMap, setSerialInFile);
            // 遍历没有连接的设备序列号
            printDevicesInFile(setSerialInFile);

        } else {
            // 电脑上没有连接Android设备
            // 直接打印配置文件中的序列号即可
            printDevicesInFile(setSerialInFile);
        }
    }

    private static void removeConnectedSerialInFile(LinkedHashMap<String, Device> connectedDeviceMap, Set<String> setSerialInFile) {
        // 获取已经连接的Android设备的 设备对象列表
        ArrayList<Device> devices = new ArrayList<>(connectedDeviceMap.values());
        // 创建设备名称列表
        ArrayList<String> deviceNames = new ArrayList<>(devices.size());
        devices.forEach(new Consumer<Device>() {
            @Override
            public void accept(Device device) {
                // 把设备的序列号添加到 list中
                deviceNames.add(device.getSerial());
            }
        });
        // 从配置文件中保存的序列号集合中 移除 所有 已连接的设备的序列号
        // 剩下的序列号就是没有连接的设备的序列号
        setSerialInFile.removeAll(deviceNames);
    }

    private static void printDevicesInFile(Set<String> setSerialInFile) {
        setSerialInFile.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                // 打印设备序列号
                printUnconnectedDevices(s);
            }
        });
    }

    private static void printUnconnectedDevices(String s) {
        if (!s.equals("isTest")) {

            Device device = new Device(s, "");
            String name = device.getName();
            System.out.println("未连接:" + name);
        }
    }

    private static void printConnectedDevices(LinkedHashMap<String, Device> deviceLinkedHashMap, boolean isTest, String formatTest, String formatRun) {
        deviceLinkedHashMap.entrySet().forEach(new Consumer<Map.Entry<String, Device>>() {
            int count = 0;

            @Override
            public void accept(Map.Entry<String, Device> stringDeviceEntry) {
                String key = stringDeviceEntry.getKey();
                Device device = stringDeviceEntry.getValue();
                count++;
                // System.out.println(count + " :" + key);
                // System.out.printf(formatTest, count, key, device.getSerial(), device.getWidth(), device.getHeight());

                if (isTest) {
                    System.out.printf(formatTest, count, key, device.getSerial(), device.getWidth(), device.getHeight());
                } else {
                    System.out.printf(formatRun, count, key, device.getSerial());
                }

                // // ArrayList<String> list = Collections.list(names);
                // int i = Collections.binarySearch(list, key);
                // if (i < 0) {
                //     unconnectedNames.add(k)
                // }

            }
        });
    }

    private static void printTitle(boolean isTest, String formatTest, String formatRun) {
        if (isTest) {
            System.out.printf(formatTest.replace("d", "s"), "num", "name", "Serial", "width", "height");
        } else {
            System.out.printf(formatRun.replace("d", "s"), "num", "name", "Serial");
        }
    }
}

package runnabletools.serial;

import adbs.cmd.AdbCommands;
import adbs.model.Device;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class AdbPower {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Devices.printConnectedDevices();
        System.out.println("输入要按下电源键的设备编号：");
        String nextLine = scanner.nextLine();
        if (nextLine.matches("\\d+")) {
//            System.out.println("数字");
            int select = Integer.parseInt(nextLine);
//            System.out.println("Devices.getSize() = " + Devices.getSize());
            if (select < Devices.getSize()) {
                LinkedHashMap<String, Device> serial_device_map = Devices.getSerial_device_map();
                Map.Entry<String, Device> element = getNthElement(serial_device_map, select);
//                Map.Entry<String, Integer> nthElement = getNthElement(map, n);

                Map.Entry<String, Device> nthElement = getNthElement(serial_device_map, select);
                if (nthElement != null) {
                    Device value = nthElement.getValue();
                    System.out.println("The " + (select) + "-th element is: " + nthElement.getKey() + " -> " + value);

                    String serial = value.getSerial();
//                    AdbCommands.runAbdCmd("adb -s "+serial+" input keyevent 26");
                    AdbCommands.powerBtn(value);


                } else {
                    System.out.println("The list does not have an N-th element.");
                }

            }
        }
    }

    private static <K, V> Map.Entry<K, V> getNthElement(LinkedHashMap<K, V> map, int n) {
        if (map == null || n < 0 || n >= map.size()) {
            return null;
        }
        int i = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (i == n) {
                return entry;
            }
            i++;
        }
        return null;
    }


//    private static void printConnectedDevices() {
//        LinkedHashMap<String, Device> connectedDeviceMap = Devices.getStringDeviceLinkedHashMap();
//        Devices.printConnectedDevices(connectedDeviceMap);
//    }
}

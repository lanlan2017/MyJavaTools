package runnabletools.adbd;

import adbs.main.run.IsTest;
import adbs.model.Device;
import runnabletools.serial.Devices;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 获取Android设备列表
 * adbd.bat命令
 */
public class AdbDevicel {
    public static void main(String[] args) {
        LinkedHashMap<String, Device> deviceLinkedHashMap = Devices.getStringDeviceLinkedHashMap();
        // System.out.printf("%2s %-8s%-20s%-5s%-5s\n", "编号", "设备名", "序列号", "宽度", "高度");

        boolean isTest = IsTest.isTest();
        String formatTest = "%4d %-8s%-22s%-6s%-6s\n";
        // String formatRun = "%4d %-8s%-22s%\n";
        String formatRun = "%4s %-8s%-22s%\n";
        if (isTest) {
            System.out.printf(formatTest.replace("d", "s"), "num", "name", "Serial", "width", "height");
        } else {
            System.out.printf(formatRun.replace("d", "s"), "num", "name", "Serial");
        }


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
            }
        });
    }
}

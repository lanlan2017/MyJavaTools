package runnabletools.serial;

import adbs.model.Device;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

public class AdbDevicel {
    public static void main(String[] args) {
        LinkedHashMap<String, Device> deviceLinkedHashMap = Devices.getStringDeviceLinkedHashMap();
        deviceLinkedHashMap.entrySet().forEach(new Consumer<Map.Entry<String, Device>>() {
            int count = 0;
            @Override
            public void accept(Map.Entry<String, Device> stringDeviceEntry) {
                String key = stringDeviceEntry.getKey();
                count++;
                System.out.println(count + " :" + key);
            }
        });
    }
}

package adbs.model;

import adbs.cmd.CmdRun;
import config.AdbToolsProperties;
import tools.config.properties.PropertiesTools;

import java.util.*;

/**
 * adb设备模型
 */
public class Device {
    /**
     * 设备序列号
     */
    private String serial;
    /**
     * 设备名称
     */
    private String name;
    /**
     * 设备描述信息
     */
    private String description;

    /**
     * 设备的宽度(像素)
     */
    private int width;
    /**
     * 设备的高度(像素)
     */
    private int height;


    public static HashMap<String, String> map = new HashMap<>();

    public Device(String serial, String description) {
        this.serial = serial;
        this.description = description;
        // String simpleId = getSimpleId(id);
        // map.put(simpleId, id);

        name = getSimpleId(serial);
        map.put(name, serial);
        // System.out.println("id=" + id + ",simpleId=" + simpleId);
    }

    public String getSerial() {
        return serial;
    }

    public String getName() {
        return name;
    }

    /**
     * 获取设备编号对应的短名称
     *
     * @param id adb序列号
     * @return adb序列号别名
     */
    public static String findSimpleId(String id) {
        Set<Map.Entry<String, String>> entries = Device.map.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            if (next.getValue().equals(id)) {
                return next.getKey();
            }
        }
        return "";
    }

    /**
     * AdbTools.properties
     *
     * @param id 设备的ID
     * @return 设备的别名
     */
    public String getSimpleId(String id) {
        PropertiesTools propertiesTools = AdbToolsProperties.propertiesTools;
        String property = propertiesTools.getProperty(id);
        // System.out.println("通过 配置文件 获取设备别名");
        // System.out.println(id + " = " + property);
        return property;
    }

    /**
     * 获取当前选择的设备的品牌名
     *
     * @return 当前选择的设备的品牌名称
     */
    public String getBrand2() {
        String brand = name.toLowerCase();
        if (brand.contains("wifi")) {
            // 如果配置文件中有HonorWiFI之类的别名
            brand = brand.substring(0, brand.lastIndexOf("wifi"));
        } else if (brand.contains("usb")) {
            // 如果配置文件中有HonorUSB之类的别名
            brand = brand.substring(0, brand.lastIndexOf("usb"));
        } else {
            // 没有的话使用手机id作为品牌名
            // brand = phoneId;
            brand = serial;
        }
        System.out.println("brand = " + brand);
        return brand;
    }

    public String getDescription() {
        return description;
    }


    /**
     * 初始化设备的屏幕宽度和屏幕高度
     */
    private void initWidthHeight() {
        // 执行adb命令
        String run = CmdRun.run("adb -s " + serial + " shell wm size").trim();
        // String run = CmdRun.run("adb -s " + selectedPhoneId + " shell wm size").trim();
        // 打印adb命令结果
        // System.out.println("设备像素：" + run);
        // int width = 0;
        // int height = 0;
        if (run.startsWith("Physical size")) {
            String flag = "Physical size: ";
            // 截取出屏幕宽度，高度
            String widthStr = run.substring(run.indexOf(flag) + flag.length(), run.lastIndexOf("x"));
            String heightStr = run.substring(run.lastIndexOf("x") + "x".length());

            if (widthStr.matches("[0-9]+")) {
                // 设置屏幕宽度到
                // DeviceListener.width = Integer.parseInt(widthStr);
                width = Integer.parseInt(widthStr);
            }
            if (heightStr.matches("[0-9]+")) {
                // DeviceListener.height = Integer.parseInt(heightStr);
                height = Integer.parseInt(heightStr);
            }
        }
    }

    /**
     * 获取adb设备的屏幕高度（像素）
     *
     * @return
     */
    public int getHeight() {
        if (height == 0 || width == 0) {
            initWidthHeight();
        }
        return height;
    }

    /**
     * 获取adb设备的宽度(像素)
     *
     * @return
     */
    public int getWidth() {
        if (width == 0 || height == 0) {
            initWidthHeight();
        }
        return width;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Device devices = (Device) o;
        // 如果设备描述信息一样的话，则认为是同一个设备
        return Objects.equals(description, devices.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serial, description);
    }

    @Override
    public String toString() {
        return "Device{" + "serial='" + serial + '\'' + ", name='" + name + '\'' + ", width=" + width + ", height=" + height + '}';
    }
}

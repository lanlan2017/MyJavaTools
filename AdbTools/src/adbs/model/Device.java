package adbs.model;

import adbs.cmd.AdbCommands;
import adbs.cmd.CmdRun;
import adbs.main.run.AdbShellPmListPackages_3;
import adbs.main.run.IsTest;
import config.AdbToolsProperties;
import tools.config.properties.PropertiesTools;

import java.util.*;

/**
 * adb设备模型
 * 设备模型
 * 设备对象
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
    /**
     * 产品型号
     */
    private String product;

    /**
     * 品牌名
     */
    private String brand;

    /**
     * 是否安装快手或者快手极速版APP
     */
    private boolean kuaiShouInstalled;

    private boolean dianTaoInstalled;

    /**
     * 100以内的质数共有25个，从小到大依次排列为：
     * 2、3、5、7、11、
     * 13、17、19，23、29、
     * 31、37、41、43、47、
     * 53，59、61、67、71、
     * 73、79、83、89、97
     */
    private int priority = 1;

    public static HashMap<String, String> map = new HashMap<>();

    public Device(String serial, String description) {
        this.serial = serial;
        this.description = description;
        // System.out.println("description = " + description);
        String productFlag = "product:";
        if (description.contains(productFlag)) {
            product = description.substring(description.indexOf(productFlag) + productFlag.length());
            product = product.substring(0, product.indexOf(" "));
            // System.out.println("product = |" + product + "|");
        }

        // String simpleId = getName(id);
        // map.put(simpleId, id);
        name = getName(serial);
        map.put(name, serial);
        // System.out.println("id=" + id + ",simpleId=" + simpleId);
        setIsKuaiShouInstalled(serial);
        if (IsTest.isIsTest()) {
            System.out.print(name + " " + serial + " ");
            System.out.print(" width=" + getWidth());
            System.out.print(" height=" + getHeight());
            System.out.println();
        }
    }

    private void setIsKuaiShouInstalled(String serial) {
        // com.kuaishou.nebula                     快手极速版
        // com.smile.gifmaker                      快手
        // getName()
        // 先在配置文件中查找标记
        String installedFlags = getInstalledFlags(serial);
        // System.out.print(name + " " + serial + " ");
        // System.out.println();
        if (installedFlags.contains("QuTouTiao")) {
            priority = priority * Priority.priority[0];
            // System.out.print(",趣头条=" + Priority.priority[0]);
        }
        if (installedFlags.contains("DianTao")) {
            dianTaoInstalled = true;
            priority = priority * Priority.priority[1];
            // System.out.print(",点淘=" + Priority.priority[1]);
        }
        if (installedFlags.contains("BaiDuJiSuBan")) {
            priority = priority * Priority.priority[2];
            // System.out.print(",百度极速版=" + Priority.priority[2]);
        }
        if (installedFlags.contains("KuaiShou")) {
            kuaiShouInstalled = true;
            priority = priority * Priority.priority[3];
            // System.out.print(",快手(极速版)=" + Priority.priority[3]);
        }
        // System.out.println(" priority = " + priority);
    }

    public int getPriority() {
        return priority;
    }

    public String getSerial() {
        return serial;
    }

    public String getName() {
        return name;
    }

    public boolean isKuaiShouInstalled() {
        return kuaiShouInstalled;
    }

    public boolean isDianTaoInstalled() {
        return dianTaoInstalled;
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
     * @param serial 设备的ID
     * @return 设备的别名
     */
    public String getName(String serial) {
        PropertiesTools propertiesTools = AdbToolsProperties.propertiesTools;
        String property = propertiesTools.getProperty(serial);
        // System.out.println("property = " + property);
        // System.out.println("通过 配置文件 获取设备别名");
        // System.out.println(serial + " = " + property);
        if (property.contains("_")) {
            property = property.substring(0, property.indexOf("_"));
        }
        // System.out.println("property = " + property);
        return property;
    }

    public String getInstalledFlags(String serial) {
        PropertiesTools propertiesTools = AdbToolsProperties.propertiesTools;
        String flags = propertiesTools.getProperty(serial);
        // System.out.println("flags = " + flags);
        if (flags.contains("_")) {
            flags = flags.substring(flags.indexOf("_") + 1);
        }
        // System.out.println("flags = " + flags);
        return flags;
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

    // public String getBrand() {
    //     String code = "adb -s " + serial + " shell getprop ro.product.brand";
    // }


    public String getBrand() {
        if (brand == null) {
            String code = "adb -s " + serial + " shell getprop ro.product.brand";
            String s = AdbCommands.runAbdCmd(code).trim();
            brand = s;
        }
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

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setName(String name) {
        this.name = name;
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

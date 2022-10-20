package adbs.main.auto.listener;

import config.AdbToolsProperties;
import tools.config.properties.PropertiesTools;

import java.util.*;

public class Device {
    private String id;
    private String description;

    public static HashMap<String, String> map = new HashMap<>();

    public Device(String id, String description) {
        this.id = id;
        this.description = description;
        String simpleId = getSimpleId(id);
        map.put(simpleId, id);
    }

    public String getId() {
        return id;
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
        System.out.println("通过 配置文件 获取设备别名");
        System.out.println(id + " = " + property);
        return property;
    }

    /**
     * 获取当前的品牌名
     *
     * @return
     */
    public static String getBrand() {
        // todo 修改此处硬编码方式
        String simpleId = Device.findSimpleId(DeviceListener.getPhoneId()).toLowerCase();
        System.out.println("simpleId = " + simpleId);
        String brand = "未知设备";
        if (simpleId.contains("oppo")) {
            brand = "oppo";
        } else if (simpleId.contains("honor")) {
            brand = "honor";
        } else if (simpleId.contains("redmi")) {
            brand = "redmi";
        }
        return brand;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return Objects.hash(id, description);
    }
}

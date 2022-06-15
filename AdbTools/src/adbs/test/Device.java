package adbs.test;

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

    public String getSimpleId(String id) {
        String simpleId = "";
        switch (id) {
            case "U8ENW18117021408":
                simpleId = "HonorUSB";
                break;
            case "75aed56d":
                simpleId = "OppoUSB";
                break;
            case "192.168.0.102:5555":
                simpleId = "HonorWiFi";
                break;
            case "192.168.0.100:5556":
                simpleId = "OppoWiFi";
                break;
        }
        return simpleId;
    }

    /**
     * 获取当前的品牌名
     *
     * @return
     */
    public static String getBrand() {
        String simpleId = Device.findSimpleId(DeviceRadioBtAcListener.getId()).toLowerCase();
        String brand = null;
        if (simpleId.contains("oppo")) {
            brand = "oppo";
        } else if (simpleId.contains("honor")) {
            brand = "honor";
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

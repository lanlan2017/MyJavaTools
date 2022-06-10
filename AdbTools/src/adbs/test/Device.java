package adbs.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

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

    public String getSimpleId(String id) {
        String simpleId = "";
        switch (id) {
            case "U8ENW18117021408":
                simpleId = "HonorUSB";
                break;
            case "75aed56d":
                simpleId = "OppoUSB";
                break;
        }
        return simpleId;
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
        // return Objects.equals(id, devices.id) && Objects.equals(description, devices.description);
        // 如果设备描述信息一样的话，则认为是同一个设备
        return Objects.equals(description, devices.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}

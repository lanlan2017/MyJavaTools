package yml.load.map.key;

import org.yaml.snakeyaml.Yaml;

import java.util.*;

public class MapKeyList {
    private static Map<String, Object> map;
    private static Collection<String> keysList = new TreeSet<>();

    static {
        Yaml yaml = new Yaml();
        map = yaml.load(MapKeyList.class.getClassLoader().getResourceAsStream("yml/Map2.yml"));
    }

    public static Collection<String> getKeysList() {
        getKeysList(map, "");
        return keysList;
    }

    private static void getKeysList(Map<String, Object> mapTemp, String before) {
        Set<String> keySet = mapTemp.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = mapTemp.get(key);
            if (value instanceof Map) {
                Map next = (Map<String, Object>) value;
                if ("".equals(before)) {
                    getKeysList(next, key);
                } else {
                    getKeysList(next, before + " " + key);
                }
            }
            if ("".equals(before)) {
                // System.out.println("|" + key + "|");
                keysList.add(key);
            } else {
                // System.out.println("|" + before + " " + key + "|");
                keysList.add(before + " " + key);
            }
        }
    }

    public static void main(String[] args) {
        Collection<String> items = MapKeyList.getKeysList();
        Iterator<String> it = items.iterator();
        while (it.hasNext()) {
            System.out.println("|" + it.next() + "|");
        }
        System.out.println();
    }
}

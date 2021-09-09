package yml.load.map;

import org.yaml.snakeyaml.Yaml;
import yml.load.map.key.MapKeyList;

import java.util.*;

public class TestMapHelp {
    public static Map<String, Object> map;

    static {
        Yaml yaml = new Yaml();
        map = yaml.load(TestMapHelp.class.getClassLoader().getResourceAsStream("Map2.yml"));
    }
    // public TestMapHelp() {
    //     Yaml yaml = new Yaml();
    //     map = yaml.load(this.getClass().getClassLoader().getResourceAsStream("Map2.yml"));
    // }

    public static void main(String[] args) {
        Map<String, Object> mapTemp = map;
        // String[] keyStrs = {"m", "cb"};
        Collection<String> items = MapKeyList.getKeysList();
        Iterator<String> it = items.iterator();
        while (it.hasNext()) {
            System.out.println("|" + it.next() + "|");
        }
        System.out.println();
    }

    private static void printOneKeyset(Map<String, Object> mapTemp, String before) {
        Set<String> keySet = mapTemp.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = mapTemp.get(key);
            if (value instanceof Map) {
                Map next = (Map<String, Object>) value;
                if ("".equals(before)) {
                    printOneKeyset(next, key);
                } else {
                    printOneKeyset(next, before + " " + key);
                }
            }
            if ("".equals(before)) {
                System.out.println("|" + key + "|");
            } else {
                System.out.println("|" + before + " " + key + "|");
            }
        }
    }

    // static List<String> keysList = new ArrayList<>();
    // public static void getKeys(Map<String, Object> mapTemp, String before) {
    //     Set<String> keySet = mapTemp.keySet();
    //     Iterator<String> it = keySet.iterator();
    //     while (it.hasNext()) {
    //         String key = it.next();
    //         Object value = mapTemp.get(key);
    //         if (value instanceof Map) {
    //             Map next = (Map<String, Object>) value;
    //             if ("".equals(before)) {
    //                 getKeys(next, key);
    //             } else {
    //                 getKeys(next, before + " " + key);
    //             }
    //         }
    //         if ("".equals(before)) {
    //             // System.out.println("|" + key + "|");
    //             keysList.add(key);
    //         } else {
    //             // System.out.println("|" + before + " " + key + "|");
    //             keysList.add(before + " " + key);
    //         }
    //     }
    // }

    public static Collection<String> getKeySets(Map<String, Object> mapTemp, String[] keyStrs) {
        Collection<String> items = null;
        Object son = null;
        for (int i = 0; i < keyStrs.length; i++) {
            // 取出value
            son = mapTemp.get(keyStrs[i]);
            // 如果value是map的话
            if (son instanceof Map) {
                // 转换成value
                mapTemp = (Map<String, Object>) son;
            }
        }
        if (son instanceof Map) {
            // 转换成value
            mapTemp = (Map<String, Object>) son;
            items = mapTemp.keySet();
        }
        return items;
    }

    /**
     * @param mapTemp
     */
    private static void findLastValue(Map<String, Object> mapTemp) {
        Scanner scanner = new Scanner(System.in);
        Collection<String> keys;
        Iterator it;
        keys = mapTemp.keySet();
        String command = "";
        while (keys != null) {
            // 打印集合的内容
            System.out.println(printCollection(keys));
            System.out.println("输入命令:");
            String key = scanner.nextLine();
            command += key + " ";
            System.out.print(command + "=");
            keys = getKeySet(mapTemp, key);
            // 获取下一级的map
            // mapTemp = (Map<String, Object>) mapTemp.get(key);
            Object next = mapTemp.get(key);
            if (next instanceof Map) {
                mapTemp = (Map<String, Object>) next;
            } else if (next instanceof String) {
                System.out.println((String) next);
            }
        }
    }

    private static String printCollection(Collection<String> keys) {
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it;
        it = keys.iterator();
        while (it.hasNext()) {
            // System.out.print(it.next()+",");
            stringBuilder.append(it.next()).append(",");
        }
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        return stringBuilder.toString();
        // System.out.println();
    }

    private static Set<String> getKeySet(Map<String, Object> mapTemp, String key) {
        Set<String> keySetTemp = null;
        while (mapTemp != null) {
            // String key = "m";
            // String keys={"m",""};
            if (mapTemp.containsKey(key)) {
                Object son = mapTemp.get(key);
                if (son instanceof Map) {
                    Map<String, Object> sonMap = (Map<String, Object>) son;
                    // Set<String> keySet = sonMap.keySet();
                    keySetTemp = sonMap.keySet();
                    // Iterator it = keySet.iterator();
                    break;
                    // mapTemp = sonMap;
                } else {
                    mapTemp = null;
                }
            }
        }
        return keySetTemp;
    }

    private static List<String> getKeyList(Map<String, Object> mapTemp, String key) {
        List<String> keyList = null;
        // Set<String> keySetTemp = null;
        while (mapTemp != null) {
            // String key = "m";
            // String keys={"m",""};
            if (mapTemp.containsKey(key)) {
                Object son = mapTemp.get(key);
                if (son instanceof Map) {
                    Map<String, Object> sonMap = (Map<String, Object>) son;
                    // Set<String> keySet = sonMap.keySet();
                    // keySetTemp = sonMap.keySet();
                    keyList = new ArrayList<>(sonMap.keySet());
                    // Iterator it = keySet.iterator();
                    break;
                    // mapTemp = sonMap;
                } else {
                    mapTemp = null;
                }
            }
        }
        return keyList;
    }
}

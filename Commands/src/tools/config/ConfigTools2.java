package tools.config;

import org.yaml.snakeyaml.Yaml;
import reader.resouce.ResourceFileReader;

import java.util.Map;

public class ConfigTools2 {
    /**
     * 配置文件得到的Map
     */
    private final Map<String, Object> configMap;
    // 直接创建实例
    private static final ConfigTools2 instance = new ConfigTools2();

    // 私有化构造函数
    private ConfigTools2() {
        Yaml yaml = new Yaml();
        // 配置文件解析为map
        configMap = yaml.load(ResourceFileReader.getInputStream(this.getClass(), "config.yml"));
    }

    // 提供获取实例的方法
    public static ConfigTools2 getInstance() {
        return instance;
    }

    public String keySequenceValue(String[] args) {
        // 最后一个命令作为key的value
        Object objectOfLastKey;
        // 返回值
        String valueOfLastKey = null;
        // 配置文件对应的Map
        Map<String, Object> map = configMap;
        // 倒数第2个命令行参数的下标
        final int lastTwoArgIndex = args.length - 2;
        // 逐层向下取出map,取出倒数第2层命令行参数的map
        for (int i = 0; i < lastTwoArgIndex; i++) {
            map = (Map<String, Object>) map.get(args[i]);
        }
        // 如果倒数第2层的map存在"lastTwodefault"这个key
        if (map.containsKey("lastTwoDefault")) {
            System.out.println("倒数第2个命令所在层存在lastTwoDefault");
            // 取出对应的value
            Object valueObject = map.get("lastTwoDefault");
            // 如果这个value是字符串
            if (valueObject instanceof String) {
                // 强制转换为字符串，并返回
                valueOfLastKey = (String) valueObject;
                return valueOfLastKey;
            }
        }
        // 否则，如果倒数第2层的map存在
        else {
            System.out.println("倒数第2个命令所在层不存在lastTwoDefault");
            // System.out.println(args[lastTwoArgIndex+1]);
            // 取出倒数第个2命令对应的map,进入下一层Map
            String lastTwoArg = args[lastTwoArgIndex];
            map = (Map<String, Object>) map.get(lastTwoArg);
            // System.out.println(lastTwoArg + "--" + map);
            // 最后一个命令行参数
            String lastArg = args[lastTwoArgIndex + 1];
            // 如果存在最后一个命令对应的key
            if (map.containsKey(lastArg)) {
                Object value = map.get(lastArg);
                // System.out.println(lastArg + "--" + value);
                if (value instanceof Map) {
                    Map valueMap = (Map) value;
                    valueOfLastKey = (String) valueMap.get("default");
                } else if (value instanceof String) {
                    valueOfLastKey = (String) value;
                }
            }
            // 否则，如果存在default对应的key
            else if (map.containsKey("default")) {
                valueOfLastKey = (String) map.get("default");
            }
        }
        return valueOfLastKey;
    }
}

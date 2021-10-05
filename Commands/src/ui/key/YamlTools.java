package ui.key;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.*;

public class YamlTools {
    /**
     * YML解析对象
     */
    private static final Yaml yaml = new Yaml();
    /**
     * 保存缓存命令的文件
     */
    private static final File cacheCommandFile;
    /**
     * 保存配置文件的Map
     */
    private static Map<String, Object> configMap;

    // 初始化块，要写在commandsNotInConfig定义语句的前面
    static {
        configMap = yaml.load(YamlTools.class.getClassLoader().getResourceAsStream("config.yml"));
        // 保存不再配置文件中的命令的文件
        cacheCommandFile = new File("commands.yml");
        // 如果这个文件不存在
        if (!cacheCommandFile.exists()) {
            try {
                // 那么先创建这个文件
                cacheCommandFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 保存命令的Collection
     */
    private static Collection<String> commands = new TreeSet<>();
    /**
     * 保存缓存命令的Collection，这些命令没有写在配置文件中，但是可以正确执行的命令
     */
    private static final Collection<String> cacheCommands = loadCacheCommands();
    /**
     * 标记，命令缓存列表是否改变了。
     */
    private static boolean isCacheCommandsChage = false;


    /**
     * 获取所有的命令
     *
     * @return 配置文件中的命令+缓存命令
     */
    public static Collection<String> getCommands() {
        getKeysList(configMap, "");
        // 如果存在非配置文件中的命令
        if (cacheCommands != null) {
            System.out.println("添加默认二级命令");
            // 添加这些命令
            commands.addAll(cacheCommands);
        }
        // 返回命令列表
        return commands;
    }

    /**
     * 读取配置文件中的命令到 keysList中
     *
     * @param mapTemp 保存配置文件命令的Map
     * @param before  上一级的key
     */
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
            if ("default".equals(key)) {
                continue;
            }
            if ("".equals(before)) {
                // System.out.println("|" + key + "|");
                commands.add(key);
            } else {
                // System.out.println("|" + before + " " + key + "|");
                commands.add(before + " " + key);
            }
        }
    }

    /**
     * 加载缓存的命令。
     *
     * @return 不再配置文件中的命令的集合
     */
    private static Collection<String> loadCacheCommands() {
        Collection<String> collection;
        Reader reader = null;
        try {
            reader = new FileReader(cacheCommandFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        collection = yaml.load(reader);
        if (collection == null) {
            return new ArrayList<>();
        }
        return collection;
    }

    /**
     * 增加一个命令到命令缓存列表。
     *
     * @param command 要缓存的非配置文件中的命令
     */
    public static void addCacheCommands(String command) {
        // 添加命令到非配置文件命令列表
        cacheCommands.add(command);
        // 改变标记
        isCacheCommandsChage = true;
    }

    /**
     * 保存命令缓存列表。
     */
    public static void saveCacheCommands() {
        if (isCacheCommandsChage) {
            System.out.println("有新的命令");
            Writer writer = null;
            try {
                writer = new FileWriter(cacheCommandFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            saveCacheCommands(cacheCommands, writer);
        } else {
            System.out.println("没有新的命令");
        }

    }

    /**
     * 保存缓存命令到文件中。
     *
     * @param collection 保存缓存命令
     * @param writer     字符输出流
     */
    private static void saveCacheCommands(Collection<String> collection, Writer writer) {
        yaml.dump(collection, writer);
    }


    public static void main(String[] args) {
        Collection<String> items = YamlTools.getCommands();
        Iterator<String> it = items.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println();
    }
}
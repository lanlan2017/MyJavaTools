package tools.config.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertiesTools {
    // private File propertyFile;
    //1 实例化
    public Properties properties = new Properties();

    public static String withoutThisVlaue = "找不到这个value对应的key";

    // FileOutputStream fileOutputStream;
    private File file;


    public PropertiesTools(String path) {
        file = new File(path);
        // System.out.println("配置文件绝对路径= " + file.getAbsolutePath());
        load(file);
    }

    public PropertiesTools(File file) {
        load(file);
    }

    public void load(File file) {
        if (file.exists()) {
            try {
                properties.load(new FileInputStream(file));
                // 新加入
                // fileOutputStream = new FileOutputStream(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取键对应的值
     *
     * @param key
     * @return 如果这个key存在对应的vlaue，则返回value,否则返回传入的参数key.
     */
    public String getProperty(String key) {
        return properties.getProperty(key, key);
    }

    // public String findKey(String value) {
    //
    // }


    /**
     * 根据value查找value
     *
     * @param value 要查找的value
     * @return 如果找到对应的key则返回查找到的key，否则返回withoutThisVlaue
     */
    public String findKey(String value) {
        // 获取所有键的Set
        Set<String> stringSet = keySet();
        // 遍历这些key
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            // 获取这些key的value
            String property = properties.getProperty(key);
            // 如果有一个value和参数相等
            if (property.equals(value)) {
                System.out.println("key=" + key + ",value=" + property);
                return key;
            }
        }
        return withoutThisVlaue;
    }


    /**
     * 返回包含所有key的Set<String>集合
     *
     * @return 包含配置文件所有key的Set<String>
     */
    private Set<String> keySet() {
        return properties.stringPropertyNames();
    }

    /**
     * 获取所有key的枚举
     *
     * @return 获取所有key的枚举
     */
    private Enumeration<?> getKeyEnumeration() {
        return properties.propertyNames();
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    public void store() {
        try {
            // properties.store(fileOutputStream, "更新端口号");
            // fileOutputStream.close();
            // fileOutputStream=new FileOutputStream(file);
            // fileOutputStream.flush();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            properties.store(fileOutputStream, "更新端口号");
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        // URL resource = PropertiesTools.class.getClassLoader().getResource("config/properties/Test.properties");
        // String path = resource.getPath();
        // String path = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\ui.properties";
        // testFindKey();
        testStore();
        testStore();
        testStore();
    }

    private static void testStore() {
        String path = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools_Port.properties";
        PropertiesTools propertiesTools = new PropertiesTools(path);
        String nextPort = propertiesTools.getProperty("NextPort");
        System.out.println("nextPort = " + nextPort);

        int port = Integer.parseInt(nextPort);
        String nextPort1 = String.valueOf(port + 1);

        propertiesTools.setProperty("NextPort", nextPort1);

        // 保存到配置文件中
        propertiesTools.store();
    }


    private static void testFindKey() {
        String path = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools.properties";
        // System.out.println("file path = " + path);
        PropertiesTools propertiesTools = new PropertiesTools(path);
        // test0(propertiesTools);
        // test1(propertiesTools);
        // test2(propertiesTools);

        String toSearchFor = "9-";
        System.out.println(propertiesTools.findKey(toSearchFor));
    }


    private void test0() {
        String site = getProperty("site");
        System.out.println("site = " + site);
    }

    private void test2() {
        Set<String> stringSet = keySet();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next + "=" + properties.getProperty(next));
        }
    }

    private static void test1(PropertiesTools propertiesTools) {
        Enumeration<?> enumeration = propertiesTools.getKeyEnumeration();
        while (enumeration.hasMoreElements()) {
            String nextElement = (String) enumeration.nextElement();
            // System.out.println("nextElement = " + nextElement);
            System.out.println(nextElement + "=" + propertiesTools.properties.getProperty(nextElement));
        }
    }

}

package tools.config.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertiesTools {
    // private File propertyFile;
    //1 实例化
    public Properties properties = new Properties();


    public PropertiesTools(String path) {
        File file = new File(path);
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key, key);
    }

    public static void main(String[] args) {
        // URL resource = PropertiesTools.class.getClassLoader().getResource("config/properties/Test.properties");
        // String path = resource.getPath();
        String path = "G:\\dev2\\idea_workspace\\MyJavaTools\\AdbTools\\Pythons\\ui.properties";

        System.out.println("path = " + path);
        PropertiesTools propertiesTools = new PropertiesTools(path);
        String site = propertiesTools.getProperty("site");
        System.out.println("site = " + site);
        Enumeration<?> enumeration = propertiesTools.getKeyEnumeration();
        while (enumeration.hasMoreElements()) {
            String nextElement = (String) enumeration.nextElement();
            // System.out.println("nextElement = " + nextElement);
            System.out.println(nextElement + "=" + propertiesTools.properties.getProperty(nextElement));
        }
        Set<String> stringSet = propertiesTools.keySet();
        Iterator<String> iterator = stringSet.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            System.out.println(next + "=" + propertiesTools.properties.getProperty(next));
        }
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
}

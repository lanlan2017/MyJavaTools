package property.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertyTools {
    public static void main(String[] args)
    {
        String test = "id\r\n" + "MIME\r\n" + "url\r\n" + "GET\r\n" + "CRUD\r\n" + "JavaEE\r\n"
                + "SpringMVC\r\n";
        String[] tests = test.split("\r\n");
        HashMap<String, String> hashMap = properties2Map("SpecialWords.properties", "utf-8");
        Set<String> keySet = hashMap.keySet();
        // // 1 获取Map.Entry对象的Set集合
        // Set<Entry<String, String>> mapEntry = hashMap.entrySet();
        // // 2 Map.Entry对象的Set集合迭代器
        // Iterator<Entry<String, String>> mapEntryIt = mapEntry.iterator();
        // while (mapEntryIt.hasNext())
        // {
        // // 2 从Set集合中取出一个 Map.Entry实例
        // Entry<String, String> mapEntryElement = mapEntryIt.next();
        // // 3 分别取出键和值
        // String key = mapEntryElement.getKey();
        // String value = mapEntryElement.getValue();
        // System.out.println("key=" + key + ",value=" + value);
        // }
        // 替换
        for (int i = 0; i < tests.length; i++)
        {
            if (findKeyFromSet(tests[i], keySet))
            {
                tests[i] = hashMap.get(tests[i]);
            }
        }
        System.out.println("--------------------------------------");
        // 查看替换结果
        for (int i = 0; i < tests.length; i++)
        {
            System.out.println(tests[i]);
        }
    }
    /**
     * @param tests
     * @param keySet
     * @param i
     */
    public static boolean findKeyFromSet(String toFind, Set<String> keySet)
    {
        for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();)
        {
            String string = (String) iterator.next();
            if (string.equals(toFind))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * @param  path
     *                     xxx.properties配置文件的路径.
     * @param  charset
     *                     properties配置文件的编码.
     * @return
     */
    public static HashMap<String, String> properties2Map(String path, String charset)
    {
        // 1 实例化
        Properties properties = new Properties();
        // 获取当前包下的配置文件路径
        // 注意这个路径的根目录是以src目录为根目录.
        // /Tools/src/property/reader/SpecialWords.properties对应的路径为:
        // /property/reader/SpecialWords.properties,/Tools/src不需要写出来
        // InputStream in = ResourceFileReader.class.getResourceAsStream(
        // "/property/reader/SpecialWords.properties");
        try
        {
            // 加载配置文件
            properties.load(new InputStreamReader(new FileInputStream(new File(path)), charset));
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // 获取所有key的Set集合
        Set<String> keys = properties.stringPropertyNames();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String key;
        String value;
        // 遍历所有的key
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();)
        {
            key = (String) iterator.next();
            // 取出对应key的value值
            value = properties.getProperty(key);
            // System.out.println("key=" + key + ",value=" + value);
            hashMap.put(key, value);
        }
        return hashMap;
    }
}

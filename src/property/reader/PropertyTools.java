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
        // // 1 ��ȡMap.Entry�����Set����
        // Set<Entry<String, String>> mapEntry = hashMap.entrySet();
        // // 2 Map.Entry�����Set���ϵ�����
        // Iterator<Entry<String, String>> mapEntryIt = mapEntry.iterator();
        // while (mapEntryIt.hasNext())
        // {
        // // 2 ��Set������ȡ��һ�� Map.Entryʵ��
        // Entry<String, String> mapEntryElement = mapEntryIt.next();
        // // 3 �ֱ�ȡ������ֵ
        // String key = mapEntryElement.getKey();
        // String value = mapEntryElement.getValue();
        // System.out.println("key=" + key + ",value=" + value);
        // }
        // �滻
        for (int i = 0; i < tests.length; i++)
        {
            if (findKeyFromSet(tests[i], keySet))
            {
                tests[i] = hashMap.get(tests[i]);
            }
        }
        System.out.println("--------------------------------------");
        // �鿴�滻���
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
     *                     xxx.properties�����ļ���·��.
     * @param  charset
     *                     properties�����ļ��ı���.
     * @return
     */
    public static HashMap<String, String> properties2Map(String path, String charset)
    {
        // 1 ʵ����
        Properties properties = new Properties();
        // ��ȡ��ǰ���µ������ļ�·��
        // ע�����·���ĸ�Ŀ¼����srcĿ¼Ϊ��Ŀ¼.
        // /Tools/src/property/reader/SpecialWords.properties��Ӧ��·��Ϊ:
        // /property/reader/SpecialWords.properties,/Tools/src����Ҫд����
        // InputStream in = ResourceFileReader.class.getResourceAsStream(
        // "/property/reader/SpecialWords.properties");
        try
        {
            // ���������ļ�
            properties.load(new InputStreamReader(new FileInputStream(new File(path)), charset));
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // ��ȡ����key��Set����
        Set<String> keys = properties.stringPropertyNames();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String key;
        String value;
        // �������е�key
        for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();)
        {
            key = (String) iterator.next();
            // ȡ����Ӧkey��valueֵ
            value = properties.getProperty(key);
            // System.out.println("key=" + key + ",value=" + value);
            hashMap.put(key, value);
        }
        return hashMap;
    }
}

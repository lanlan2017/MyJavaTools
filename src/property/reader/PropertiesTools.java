package property.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

public class PropertiesTools
{
	public static void main(String[] args)
	{
		PropertiesTools propertiesTools = new PropertiesTools(
				"SpecialWords.properties", "utf-8");
		String test = "id\r\n" + "MIME\r\n" + "url\r\n" + "GET\r\n" + "CRUD\r\n"
				+ "JavaEE\r\n" + "SpringMVC\r\n+xxxx\r\n";
		String[] tests = test.split("\r\n");
		for (String string : tests)
		{
			if (propertiesTools.findKey(string))
			{
				System.out.println("value=" + propertiesTools.getValue(string));
			}
		}
	}
	// 1.实例化配置文件对象
	private Properties properties = new Properties();
	// 获取所有key的Set集合
	private Set<String> keySet;
	public PropertiesTools()
	{
	}
	public PropertiesTools(String path, String charset)
	{
		try
		{
			// 2.加载配置文件
			this.properties.load(new InputStreamReader(
					new FileInputStream(new File(path)), charset));
			// 3.获取所有的key,保存到Set集合中
			this.keySet = properties.stringPropertyNames();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * 查找配置文件之中有没有相关的key.
	 * 
	 * @param keyToFind
	 *            想要查找的key.
	 * @return 如果找到返回true,如果没有找到返回false.
	 */
	public boolean findKey(String keyToFind)
	{
		for (Iterator<String> iterator = keySet.iterator(); iterator.hasNext();)
		{
			String keyInSet = (String) iterator.next();
			if (keyInSet.equals(keyToFind))
			{
				return true;
			}
		}
		return false;
	}
	/**
	 * 根据key获取值.
	 * 
	 * @param key
	 *            就是配置文件中的key.
	 * @return key对应的值.
	 */
	public String getValue(String key)
	{
		return properties.getProperty(key);
	}

}

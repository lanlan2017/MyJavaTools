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
	// 1.ʵ���������ļ�����
	private Properties properties = new Properties();
	// ��ȡ����key��Set����
	private Set<String> keySet;
	public PropertiesTools()
	{
	}
	public PropertiesTools(String path, String charset)
	{
		try
		{
			// 2.���������ļ�
			this.properties.load(new InputStreamReader(
					new FileInputStream(new File(path)), charset));
			// 3.��ȡ���е�key,���浽Set������
			this.keySet = properties.stringPropertyNames();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/**
	 * ���������ļ�֮����û����ص�key.
	 * 
	 * @param keyToFind
	 *            ��Ҫ���ҵ�key.
	 * @return ����ҵ�����true,���û���ҵ�����false.
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
	 * ����key��ȡֵ.
	 * 
	 * @param key
	 *            ���������ļ��е�key.
	 * @return key��Ӧ��ֵ.
	 */
	public String getValue(String key)
	{
		return properties.getProperty(key);
	}

}

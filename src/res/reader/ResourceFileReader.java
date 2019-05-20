package res.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * �����ר��������ȡ��Դ�ļ�
 */
public class ResourceFileReader
{
	 public static void main(String[] args)
	 {
	 System.out.println(getResourceFileContent("/AudioPlay.html", "utf-8"));
	 }
	/**
	 * 
	 * /** ������ԴĿ¼�µ�path�ļ��е�����.����UTF-8��������ȡ,���Ը��ļ�����Ҫ����ΪUTF-8�����ʽ.
	 * 
	 * @param path
	 *            ��Դ�ļ���·��,������<code>/</code>��ͷ.<br>
	 *            ������Ŀ��ԴĿ¼res�µĵ�<code>AudioPlay.html</code>��·��Ϊ:
	 *            <code>"/AudioPlay.html"</code>
	 * @return ��ԴĿ¼�µ�path�ļ�������
	 */
	public static String getResourceFileContent(String path, String charset)
	{
		if (path.startsWith("/"))
		{
			String content = null;
			// TODO Auto-generated method stub
			InputStream inputStream = ResourceFileReader.class
					.getResourceAsStream(path);
			try (BufferedReader reader = new BufferedReader(
					new InputStreamReader(inputStream, charset));)
			{

				StringBuilder builder = new StringBuilder();
				char[] charArray = new char[200];
				int number = -1;
				while ((number = reader.read(charArray)) != -1)
				{
					builder.append(charArray, 0, number);
				}
				content = builder.toString();
				// System.out.println(builder.toString());
			} catch (FileNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return content;
		}
		return null;

	}
}

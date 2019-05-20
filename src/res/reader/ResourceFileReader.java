package res.reader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * 这个类专门用来读取资源文件
 */
public class ResourceFileReader
{
	 public static void main(String[] args)
	 {
	 System.out.println(getResourceFileContent("/AudioPlay.html", "utf-8"));
	 }
	/**
	 * 
	 * /** 读入资源目录下的path文件中的内容.会以UTF-8编码来读取,所以该文件必须要保存为UTF-8编码格式.
	 * 
	 * @param path
	 *            资源文件的路径,必须以<code>/</code>开头.<br>
	 *            例如项目资源目录res下的的<code>AudioPlay.html</code>的路径为:
	 *            <code>"/AudioPlay.html"</code>
	 * @return 资源目录下的path文件的内容
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

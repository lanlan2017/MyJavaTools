package get.file.encode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		String path = "D:\\学习\\python\\test_utf8.py";
		File file = new File(path);
		System.out.println(file.getAbsolutePath()+"是utf-8编码的文件:"+isUTF8File(file));
	}

	/**
	 * 判断是不是utf8编码
	 * @param file 文件名
	 * @return 根据文件字节序列的头部的编码信息判断是不是utf8编码
	 */
	public static boolean isUTF8File(File file)
	{
		InputStream in = null;
		byte[] b = new byte[3];
		try
		{
			in = new java.io.FileInputStream(file);
			// 读取3个字节
			in.read(b);
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (in != null)
			{
				try
				{
					in.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
		
		if (b[0] == -17 && b[1] == -69 && b[2] == -65)
			return true;
		return false;
	}
}

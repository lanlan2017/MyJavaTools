package get.file.encode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Test
{
	public static void main(String[] args) throws IOException
	{
		String path = "D:\\ѧϰ\\python\\test_utf8.py";
		File file = new File(path);
		System.out.println(file.getAbsolutePath()+"��utf-8������ļ�:"+isUTF8File(file));
	}

	/**
	 * �ж��ǲ���utf8����
	 * @param file �ļ���
	 * @return �����ļ��ֽ����е�ͷ���ı�����Ϣ�ж��ǲ���utf8����
	 */
	public static boolean isUTF8File(File file)
	{
		InputStream in = null;
		byte[] b = new byte[3];
		try
		{
			in = new java.io.FileInputStream(file);
			// ��ȡ3���ֽ�
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

package io.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyByBufferdStream
{

	public static void main(String[] args)
	{
		copyByBufferedStream("C:\\Users\\lan\\Desktop\\2.png","C:\\Users\\lan\\Desktop\\����.png");

	}
	/**
	 * ʹ�ô�������ֽ��������ļ��ĸ���
	 * @param fromFile Դ�ļ���·��
	 * @param toFile	Ŀ���ļ���·��
	 */
	public static void copyByBufferedStream(String fromFile,String toFile)
	{
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try
		{
			// ָ��Ҫ��ȡ�ļ��Ļ��������ֽ���
			in = new BufferedInputStream(
					new FileInputStream(fromFile));
			File file = new File(toFile);
			if (!file.exists())
			{
				file.createNewFile();
			}
			// ָ��Ҫд���ļ��Ļ�������ֽ���
			out = new BufferedOutputStream(new FileOutputStream(file));
			// ����,����ÿ��
			byte[] bb = new byte[1024];// �����洢ÿ�ζ�ȡ�����ֽ�����
			int n;// ÿ�ζ�ȡ�����ֽ�����ĳ���
			while ((n = in.read(bb)) != -1)
			{
				out.write(bb, 0, n);// д�뵽�����
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		} finally
		{
			if (out != null)
			{
				try
				{
					out.close();
				} catch (IOException e)
				{
					e.printStackTrace();
				} // �ر���
			}
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
	}

}
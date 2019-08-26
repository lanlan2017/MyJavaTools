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
		copyByBufferedStream("C:\\Users\\lan\\Desktop\\2.png","C:\\Users\\lan\\Desktop\\副本.png");

	}
	/**
	 * 使用带缓冲的字节流进行文件的复制
	 * @param fromFile 源文件的路径
	 * @param toFile	目标文件的路径
	 */
	public static void copyByBufferedStream(String fromFile,String toFile)
	{
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try
		{
			// 指定要读取文件的缓冲输入字节流
			in = new BufferedInputStream(
					new FileInputStream(fromFile));
			File file = new File(toFile);
			if (!file.exists())
			{
				file.createNewFile();
			}
			// 指定要写入文件的缓冲输出字节流
			out = new BufferedOutputStream(new FileOutputStream(file));
			// 缓冲,用于每次
			byte[] bb = new byte[1024];// 用来存储每次读取到的字节数组
			int n;// 每次读取到的字节数组的长度
			while ((n = in.read(bb)) != -1)
			{
				out.write(bb, 0, n);// 写入到输出流
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
				} // 关闭流
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
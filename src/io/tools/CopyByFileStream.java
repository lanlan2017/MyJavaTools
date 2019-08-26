package io.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyByFileStream
{
	/**
	 * @param fromFile
	 * @param toFile
	 */
	public static void copyByStream(File fromFile, File toFile)
	{
		// 源文件存在才复制
		if (fromFile.exists())
		{
			// 如果找不到目标文件:说明这个文件还没有
			if (!toFile.exists())
			{
				//获取目标文件的父目录
				File parent=toFile.getParentFile();
				if(parent!=null&&!parent.exists())
				{
					parent.mkdirs();
				}
			}
			FileInputStream ins = null;
			FileOutputStream out = null;
			try
			{
				ins = new FileInputStream(fromFile);
				// 如果目标文件不存在，则FileOutputStream会创建目标文件(前提是父路径文件对象必须存在)。
				out = new FileOutputStream(toFile);
				byte[] buf = new byte[1024];
				int size = 0;
				// 每次从输入流中读取1024个字节,然后把这1024个字节写入输出流中
				while ((size = ins.read(buf)) != -1)
				{
					out.write(buf, 0, size);
				}

			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally
			{
				if (ins != null)
				{
					try
					{
						ins.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
				if (out != null)
				{
					try
					{
						out.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}

			}

		} else
		{
			System.out.println("源文件不存在");
		}
	}
}

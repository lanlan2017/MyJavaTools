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
		// Դ�ļ����ڲŸ���
		if (fromFile.exists())
		{
			// ����Ҳ���Ŀ���ļ�:˵������ļ���û��
			if (!toFile.exists())
			{
				//��ȡĿ���ļ��ĸ�Ŀ¼
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
				// ���Ŀ���ļ������ڣ���FileOutputStream�ᴴ��Ŀ���ļ�(ǰ���Ǹ�·���ļ�����������)��
				out = new FileOutputStream(toFile);
				byte[] buf = new byte[1024];
				int size = 0;
				// ÿ�δ��������ж�ȡ1024���ֽ�,Ȼ�����1024���ֽ�д���������
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
			System.out.println("Դ�ļ�������");
		}
	}
}

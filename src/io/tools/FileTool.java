package io.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileTool
{
	/**
	 * ���ļ��ж�ȡ�ַ������»��档
	 * 
	 * @param file
	 *            markdown�ļ���File����
	 * @return ��������������ַ���StringBuilder����
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static StringBuilder readFile(File file)
	{
		StringBuilder builder = new StringBuilder(5000);
		// �Ȱ����¶��뻺����
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), "utf-8"));)
		{
			String line = null;
			// һ��һ�еĶ�ȡ
			while ((line = reader.readLine()) != null)
			{
				builder.append(line + "\n");// ��ӵ�������
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return builder;
	}
	/**
	 * �����»����е������ַ�д��markdown�ļ��С�
	 * 
	 * @param file
	 *            �ļ�
	 * @param article
	 *            ���»���
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static void writeFile(File file, StringBuilder article)
	{
		try (BufferedWriter writer = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file), "utf-8"));)
		{
			writer.write(article.toString());
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * ��ȡĿ¼�µ�����markdown�ĵ��б�
	 * 
	 * @param dirFile
	 *            Ŀ¼��File
	 * @return File��������,ÿһ�����ʾһ��markdown�ĵ�(��.md��β)
	 */
	public static File[] getMarkdownFileArray(File dirFile)
	{
		// ��ȡmd�ļ���������Ŀ¼���б�
		File[] fileList = dirFile.listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				// �����markdown�ļ��Ļ�
				if (name.endsWith(".md"))
					return true;
				// �����һ��Ŀ¼�Ļ�
				else if (new File(dir, name).isDirectory())
					return true;
				// ��������ص��ļ�������
				return false;
			}
		});
		return fileList;
	}
}

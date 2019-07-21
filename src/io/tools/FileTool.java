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
	 * 从文件中读取字符到文章缓存。
	 * 
	 * @param file
	 *            markdown文件的File对象。
	 * @return 存放文章中所有字符的StringBuilder对象。
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static StringBuilder readFile(File file)
	{
		StringBuilder builder = new StringBuilder(5000);
		// 先把文章读入缓冲中
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(file), "utf-8"));)
		{
			String line = null;
			// 一行一行的读取
			while ((line = reader.readLine()) != null)
			{
				builder.append(line + "\n");// 添加到缓冲中
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return builder;
	}
	/**
	 * 把文章缓存中的所有字符写入markdown文件中。
	 * 
	 * @param file
	 *            文件
	 * @param article
	 *            文章缓存
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
	 * 获取目录下的所有markdown文档列表。
	 * 
	 * @param dirFile
	 *            目录的File
	 * @return File对象数组,每一对象表示一个markdown文档(以.md结尾)
	 */
	public static File[] getMarkdownFileArray(File dirFile)
	{
		// 获取md文件，或者是目录的列表
		File[] fileList = dirFile.listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				// 如果是markdown文件的话
				if (name.endsWith(".md"))
					return true;
				// 如果是一个目录的话
				else if (new File(dir, name).isDirectory())
					return true;
				// 其他不相关的文件不返回
				return false;
			}
		});
		return fileList;
	}
}

package io.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 输出重定向.
 */
public class OutputRedirection
{
	/**
	 * 输出重定向
	 * 
	 * @param filePath
	 *            输出文件的路径
	 */
	public static void outputRedirection(String filePath)
	{
		PrintStream txtfile;
		try
		{
			txtfile = new PrintStream(new File(filePath));
			System.setOut(txtfile);
		} catch (FileNotFoundException e)
		{

			e.printStackTrace();
		}
	}
}

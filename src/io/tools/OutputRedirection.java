package io.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * ����ض���.
 */
public class OutputRedirection
{
	/**
	 * ����ض���
	 * 
	 * @param filePath
	 *            ����ļ���·��
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

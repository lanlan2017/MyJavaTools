package systemcall.cmd.cls;

import java.io.IOException;

public class ConsoleCLS
{
	public static void main(String[] args)
			throws IOException, InterruptedException
	{
		for (int i = 0; i < 20; i++)
		{
			System.out.println("%%%%%%  " + i + "   %%%%%");
		}
		// 睡眠三秒
		Thread.currentThread().sleep(1000 * 3);
		// 控制台清屏
		cls();
	}

	/**
	 * @throws IOException
	 */
	public static void cls() throws IOException
	{
		new ProcessBuilder("cmd", "/C", "cls").inheritIO().start();
	}

}

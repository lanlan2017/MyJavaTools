package cmd.tools;

import java.io.IOException;

/**
 * 控制台清屏功能.这个清屏功能在eclipse中无效,只在CMD命令行中有效.
 */
public class CmdCls
{
	/**
	 * 实现在cmd控制台下清屏功能.
	 */
	public static void cls()
	{
		try
		{
			//// 新建一个 ProcessBuilder，其要执行的命令是 cmd.exe，参数是 /c 和 cls
			new ProcessBuilder("cmd", "/c", "cls")
					// 将 ProcessBuilder 对象的输出管道和 Java 的进程进行关联，这个函数的返回值也是一个
					// ProcessBuilder
					.inheritIO()
					// 开始执行 ProcessBuilder 中的命令
					.start()
					// 等待 ProcessBuilder 中的清屏命令执行完毕
					// 如果不等待则会出现清屏代码后面的输出被清掉的情况
					.waitFor();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 清屏命令
	}
}

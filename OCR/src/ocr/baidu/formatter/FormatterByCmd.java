package ocr.baidu.formatter;

import tools.ProcessRunner;

import java.util.List;

public abstract class FormatterByCmd
{
	/**
	 * 保存要执行的程序和该程序对应的命令行参数的List集合,由子类负责初始化.
	 */
	protected List<String> commandList;
	/**
	 * 执行给定的格式化程序来格式化文字识别得到的字符串.
	 */
	public void format()
	{
		if (commandList != null)
		{
			ProcessBuilder processBuilder = new ProcessBuilder(commandList);
			new ProcessRunner().runProcess(processBuilder);
		}
	}
}
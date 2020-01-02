package ocr.baidu.formatter.impl;

import java.util.ArrayList;
import java.util.List;
import ocr.baidu.formatter.FormatterByCmd;

public class MdCodeInLineFormatter extends FormatterByCmd
{
	/**
	 * 执行<code>cmd /c m cpkd</code>命令来格式化字符串.
	 */
	public MdCodeInLineFormatter()
	{
		List<String> commandList = new ArrayList<>();
		commandList.add("cmd");
		commandList.add("/c");
		commandList.add("m");
		commandList.add("ks");
		this.commandList = commandList;
	}
}
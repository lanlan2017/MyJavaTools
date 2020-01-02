package ocr.baidu.formatter.impl;

import java.util.ArrayList;
import java.util.List;
import ocr.baidu.formatter.FormatterByCmd;
/**
 * 格式化为Markdown Sql代码块.
 */
public class MdCodeBlockSqlFormatter extends FormatterByCmd
{
	/**
	 * 执行<code>cmd /c m cb sql</code>命令来格式化字符串.
	 */
	public MdCodeBlockSqlFormatter()
	{
		List<String> commandList = new ArrayList<>();
		commandList.add("cmd");
		commandList.add("/c");
		commandList.add("m");
		commandList.add("cb");
		commandList.add("sql");
		this.commandList = commandList;
	}
}

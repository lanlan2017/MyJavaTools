package ocr.baidu.formatter.impl;

import java.util.ArrayList;
import java.util.List;
import ocr.baidu.formatter.FormatterByCmd;

public class PdfBookmarkCmdFormatter extends FormatterByCmd
{
	/**
	 * 书签格式.如x.x.x,xx.x,...
	 */
	String bookMarkPatten;
	/**
	 * 执行<code>cmd /c s f shuqian 书签格式</code>来格式化字符串
	 * @param bookMarkPatten - 表示个数话形式
	 *            <table border='1'>
	 *            <tr>
	 *            <th>参数</th>
	 *            <th>格式话形式</th>
	 *            </tr>
	 *            <tr>
	 *            <td>111</td>
	 *            <td>x.x.x</td>
	 *            </tr>
	 *            <tr>
	 *            <td>12</td>
	 *            <td>x.xx</td>
	 *            </tr>
	 *            <tr>
	 *            <td>121</td>
	 *            <td>x.xx.x</td>
	 *            </tr>
	 *            <tr>
	 *            <td>21</td>
	 *            <td>xx.x</td>
	 *            </tr>
	 *            <tr>
	 *            <td>22</td>
	 *            <td>xx.xx</td>
	 *            </tr>
	 *            <tr>
	 *            <td>221</td>
	 *            <td>xx.xx.x</td>
	 *            </tr>
	 *            </table>
	 */
	public PdfBookmarkCmdFormatter(String bookMarkPatten)
	{
		this.bookMarkPatten = bookMarkPatten;
		List<String> commandList = new ArrayList<>();
		commandList.add("cmd");
		commandList.add("/c");
		commandList.add("s");
		commandList.add("f");
		commandList.add("shuqian" + bookMarkPatten);
		this.commandList = commandList;

	}
}
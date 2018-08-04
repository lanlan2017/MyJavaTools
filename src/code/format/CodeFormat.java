package code.format;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import clipboard.util.SysClipboardUtil;
import clipboard.util.SysClipboardUtil;

public class CodeFormat
{
	
	public static void main(String[] args)
	{
		String text = SysClipboardUtil.getSysClipboardText();
		System.out.println("接收到的文本:");
		System.out.println("------------------------------------");
		System.out.println(text);
		System.out.println("------------------------------------");
		System.out.println("java方式的格式化：");
		System.out.println("------------------------------------");
		System.out.println(formatLikeJava(text));
		System.out.println("------------------------------------");
		System.out.println("VC方式的格式化：");
		System.out.println("------------------------------------");
		System.out.println(formatLikeVC(text));
		System.out.println("------------------------------------");

//		OutputStream
		
	}// main结束

	/**   
	 * @param unformattedCode  
	 */  
	public static String formatLikeJava(String unformattedCode)
	{
		//tab键：4个空格
		final String Tab = "    ";
		StringBuilder TabBuider = new StringBuilder();
		StringBuilder formatedCode=new StringBuilder();
		Scanner scanner = new Scanner(unformattedCode);
		String line = null;
		while (scanner.hasNextLine())
		{
			line = scanner.nextLine().trim();
			if (line.matches(".*\\}.*$"))
			{
				TabBuider.delete(0, 4);
//				System.out.println(TabBuider.toString() + line);
				formatedCode.append(TabBuider.toString() + line+"\n");
			}
			else if (line.matches(".*\\{.*$"))
			{
				//vs样式的格式化：
//				System.out.println(TabBuider.toString() + line.substring(0,line.lastIndexOf('{')));
//				System.out.println(TabBuider.toString()+line.substring(line.lastIndexOf('{')));
				//Java样式的格式化：
//				System.out.println(TabBuider.toString()+line);
				formatedCode.append(TabBuider.toString()+line+"\n");
				TabBuider.append(Tab);
			}
			else if(line.matches("^throws.*$"))
			{
				formatedCode.append(Tab + line+"\n");
			}
			else {
//				System.out.println(TabBuider.toString() + line);
				formatedCode.append(TabBuider.toString() + line+"\n");
			}
		}
		scanner.close();
		return formatedCode.toString();
	}
	/**   
	 * @param unformattedCode  
	 */  
	public static String formatLikeVC(String unformattedCode)
	{
		final String Tab = "    ";
		StringBuilder TabBuider = new StringBuilder();
		StringBuilder formatedCode=new StringBuilder();
		Scanner scanner = new Scanner(unformattedCode);
		String line = null;
		while (scanner.hasNextLine())
		{
			line = scanner.nextLine().trim();
//			System.out.println("-->"+line);
			if (line.matches(".*\\}.*$"))
			{
				TabBuider.delete(0, 4);
//				System.out.println(TabBuider.toString() + line);
				formatedCode.append(TabBuider.toString() + line+"\n");
			}
			//如果这一行只有一个开括号，就不用再分割了
			else if(line.matches("\\{"))
			{
				formatedCode.append(TabBuider.toString() + line+"\n");
				TabBuider.append(Tab);
			}
			else if (line.matches(".*\\{.*$"))
			{
				//vs样式的格式化：
//				System.out.println(TabBuider.toString() + line.substring(0,line.lastIndexOf('{')));
//				System.out.println(TabBuider.toString()+line.substring(line.lastIndexOf('{')));
				formatedCode.append(TabBuider.toString() + line.substring(0,line.lastIndexOf('{'))+"\n");
				formatedCode.append(TabBuider.toString()+line.substring(line.lastIndexOf('{'))+"\n");
//				//Java样式的格式化：
//				System.out.println(TabBuider.toString()+line);
				TabBuider.append(Tab);
			}
			else if(line.matches("^throws.*$"))//匹配抛出异常语句
			{
				formatedCode.append(Tab + line+"\n");
			}
//			else if(line.matches("^//(\\s+).*$"))//匹配行注释,替换其中的空白符
			else if(line.matches("^//(\\s+).*$"))//匹配行注释,替换其中的空白符
			{
				//总感觉这里应该怎么优化一下，匹配两次正则
				Pattern pattern=Pattern.compile("^//(\\s+).*$");
				Matcher matcher = pattern.matcher(line);
//				System.out.println(matcher.matches());
//				Pattern.compile("^//(\\s+).*$").matcher(line).matches();
				
				
				matcher.find();//查找捕获组
				//使用缩进替换空白符,因为注释符本省占两个字节，所以缩进空格少打两个
				line=line.replaceAll("\\s+", TabBuider.toString().substring(2));
//				System.out.println("#--->"+line);
				formatedCode.append(line+"\n");
			}
			else {
//				System.out.println(TabBuider.toString() + line);
				formatedCode.append(TabBuider.toString() + line+"\n");
			}
		}
		scanner.close();
		return formatedCode.toString();
	}
}

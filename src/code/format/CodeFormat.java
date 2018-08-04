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
		System.out.println("���յ����ı�:");
		System.out.println("------------------------------------");
		System.out.println(text);
		System.out.println("------------------------------------");
		System.out.println("java��ʽ�ĸ�ʽ����");
		System.out.println("------------------------------------");
		System.out.println(formatLikeJava(text));
		System.out.println("------------------------------------");
		System.out.println("VC��ʽ�ĸ�ʽ����");
		System.out.println("------------------------------------");
		System.out.println(formatLikeVC(text));
		System.out.println("------------------------------------");

//		OutputStream
		
	}// main����

	/**   
	 * @param unformattedCode  
	 */  
	public static String formatLikeJava(String unformattedCode)
	{
		//tab����4���ո�
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
				//vs��ʽ�ĸ�ʽ����
//				System.out.println(TabBuider.toString() + line.substring(0,line.lastIndexOf('{')));
//				System.out.println(TabBuider.toString()+line.substring(line.lastIndexOf('{')));
				//Java��ʽ�ĸ�ʽ����
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
			//�����һ��ֻ��һ�������ţ��Ͳ����ٷָ���
			else if(line.matches("\\{"))
			{
				formatedCode.append(TabBuider.toString() + line+"\n");
				TabBuider.append(Tab);
			}
			else if (line.matches(".*\\{.*$"))
			{
				//vs��ʽ�ĸ�ʽ����
//				System.out.println(TabBuider.toString() + line.substring(0,line.lastIndexOf('{')));
//				System.out.println(TabBuider.toString()+line.substring(line.lastIndexOf('{')));
				formatedCode.append(TabBuider.toString() + line.substring(0,line.lastIndexOf('{'))+"\n");
				formatedCode.append(TabBuider.toString()+line.substring(line.lastIndexOf('{'))+"\n");
//				//Java��ʽ�ĸ�ʽ����
//				System.out.println(TabBuider.toString()+line);
				TabBuider.append(Tab);
			}
			else if(line.matches("^throws.*$"))//ƥ���׳��쳣���
			{
				formatedCode.append(Tab + line+"\n");
			}
//			else if(line.matches("^//(\\s+).*$"))//ƥ����ע��,�滻���еĿհ׷�
			else if(line.matches("^//(\\s+).*$"))//ƥ����ע��,�滻���еĿհ׷�
			{
				//�ܸо�����Ӧ����ô�Ż�һ�£�ƥ����������
				Pattern pattern=Pattern.compile("^//(\\s+).*$");
				Matcher matcher = pattern.matcher(line);
//				System.out.println(matcher.matches());
//				Pattern.compile("^//(\\s+).*$").matcher(line).matches();
				
				
				matcher.find();//���Ҳ�����
				//ʹ�������滻�հ׷�,��Ϊע�ͷ���ʡռ�����ֽڣ����������ո��ٴ�����
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

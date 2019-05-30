package tcp;

import java.net.*;
import java.io.*;
public class EchoClient
{
	public static void main(String args[]) throws Exception
	{
		// 定义客户端引用
		Socket client = null;
		// 创建客户端监听本机的6666端口
		client = new Socket("localhost", 6666);
		BufferedReader inputByServer = null;
		PrintStream outToServer = null;
		BufferedReader input = null;
		// 获取键盘输入流
		input = new BufferedReader(new InputStreamReader(System.in));
		// 获取服务器对本客户端的输入流
		inputByServer = new BufferedReader(
				new InputStreamReader(client.getInputStream()));
		// 获取对服务器的输出流
		outToServer = new PrintStream(client.getOutputStream());

		boolean flag = true; // 定义标志位
		// 如果服务器没死
		while (flag && EchoThreadServer.isServerAlive())
		{
			System.out.print("输入信息：");
			// 接收键盘输入
			String str = input.readLine();
			// 把键盘输入的字符串，打印到输出流，也就是打印到服务器端
			outToServer.println(str);
			// 客户端退出或者关闭服务器
			if ("exit".equals(str))
			{
				flag = false;
			} else if ("shutdownServer".equals(str))
			{
				flag = false;
				// 无效连接,用来抵消服务端的监听连接请求,
				// 不然服务端会一直阻塞造成无法关闭
				new Socket("localhost", 6666);
			} else
			{
				// 接收服务端的输入流
				String echo = inputByServer.readLine(); // 接收返回结果
				System.out.println(echo); // 输出回应信息
			}
		}
		// 关闭服务器对本客户端的输入流
		inputByServer.close();
		// 关闭客户端
		client.close();
	}
}
package tcp;

import java.net.*;
import java.io.*;
public class EchoServer
{
	public static void main(String args[]) throws Exception
	{
		// 定义服务器引用
		ServerSocket server = null;
		// 定义客户端引用
		Socket client = null;
		BufferedReader buf = null;
		PrintStream out = null;
		// 创建服务器，监听6666端口
		server = new ServerSocket(6666);
		//
		boolean f = true; // 定义个标记位
		String clientInput;

		while (f)
		{
			System.out.println("等待一个客户端连接....");
			// 等待客户端连接
			client = server.accept();
			System.out.println("    与客户端连接成功...");
			// 连接成功之后
			// 生成对客户端的输出流
			out = new PrintStream(client.getOutputStream());
			// 获取客户端对本服务器的输入流
			buf = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			boolean flag = true; // 标志位，表示可以一直接收并回应信息
			while (flag)
			{
				// 从客户端读入一条消息.
				clientInput = buf.readLine();
				// 如果有读到消息，或者没有读到
				if ("exit".equals(clientInput))
				{ // 如果输入的内容为exit表示结束
					flag = false;
				} else
				{
					out.println("ECHO : " + clientInput); // 回应信息
				}
			}
			// 关闭客户端
			client.close();
			System.out.println("    客户端已退出...");
			// 进入下一个循环
		}
		// 关闭服务端
		server.close();
	}
}
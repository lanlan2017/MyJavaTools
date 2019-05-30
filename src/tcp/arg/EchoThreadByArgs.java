package tcp.arg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import tcp.EchoThreadServer;

public class EchoThreadByArgs implements Runnable
{
	// 客户端引用
	private Socket client = null;
	int clienId;
	// 构造函数
	public EchoThreadByArgs(Socket client)
	{
		this.client = client;
		// 设置服务线程编号
		this.clienId = EchoThreadServer.getClientNum();
		// 服务线程数量加1
		EchoThreadServer.addClientNum();
	}
	// 线程执行体
	public void run()
	{
		// 表示来着客户端的输入
		BufferedReader inByclient = null;
		// 对客户端的输出
		PrintStream outToClient = null;
		try
		{
			// 获取对客户端的输出流
			outToClient = new PrintStream(client.getOutputStream());
			// 获取来自客户端的输入流
			inByclient = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			boolean isClientAlive = true; // 标志位，表示可以一直接收并回应信息
			while (isClientAlive)
			{
				// 读取来自客户端的输入
				String str = inByclient.readLine();
				// 表示客户端要退出
				if ("exit".equals(str))
				{
					// 结束循环不再接收客户端的输入.
					isClientAlive = false;
					// 服务线程数目减1
					EchoThreadServer.minusClientNum();
				}
				// 关闭服务端
				else if ("shutdownServer".equals(str))
				{
					EchoThreadServer.shutdownServer();
					System.out.println("关闭服务器");
					break;
				} else
				{
					// 输出到客户端
					outToClient.println(
							"服务线程 " + this.clienId + " ECHO to client: " + str); // 回应信息
				}
			}
		} catch (Exception e)
		{
		}
	}
}
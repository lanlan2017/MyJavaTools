package tcp;

import java.net.*;
public class EchoThreadServer
{
	// 静态变量,可以更改该标记以便停止服务端
	private static boolean isServerAlive = true;
	private static int clientNum = 0;
	public static void main(String args[]) throws Exception
	{
		// 1.定义服务器的引用
		ServerSocket server = null;
		// 2.客户端的引用
		Socket client = null;
		// 3.建立服务器，监听本地6666端口
		server = new ServerSocket(6666);
		while (isServerAlive)
		{
			System.out.println("等待客户端连接...");
			// 4.取得连接，客户端没连接之前先等待连接。
			client = server.accept();
			System.out.println("    客户端连接成功,当前客户端数量:" + clientNum);
			if (isServerAlive)
			{
				// 一个客户端连接之后，为该客户端启动一个服务线程进行服务
				new Thread(new EchoThread(client)).start();
			}
		}
		server.close();
		client.close();
		System.out.println("服务端已经停止...");
	}
	public static void addClientNum()
	{
		clientNum = clientNum + 1;
		// 注意了,下面的赋值方法错误
		// 后++:先取值,再增加,这将达不到增加的效果
		// clientNum = clientNum ++;
	}
	public static void minusClientNum()
	{
		clientNum = clientNum - 1;
	}
	public static int getClientNum()
	{
		return clientNum;
	}
	public static void shutdownServer()
	{
		isServerAlive = false;
	}
	public static boolean isServerAlive()
	{
		return isServerAlive;
	}
}

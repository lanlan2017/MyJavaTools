package tcp.arg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClientByArgs
{
	public static void main(String[] args)
	{
		// 定义客户端引用
		Socket client = null;
		try
		{
			// 创建客户端监听本机的6666端口
			client = new Socket("localhost", 6666);
		} catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader inputByServer = null;
		PrintStream outToServer = null;
		try
		{
			// 获取服务器对本客户端的输入流
			inputByServer = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			// 获取对服务器的输出流
			outToServer = new PrintStream(client.getOutputStream());
			outToServer.println(args);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

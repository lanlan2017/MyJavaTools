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
		// ����ͻ�������
		Socket client = null;
		try
		{
			// �����ͻ��˼���������6666�˿�
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
			// ��ȡ�������Ա��ͻ��˵�������
			inputByServer = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			// ��ȡ�Է������������
			outToServer = new PrintStream(client.getOutputStream());
			outToServer.println(args);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

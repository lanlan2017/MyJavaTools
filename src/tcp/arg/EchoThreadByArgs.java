package tcp.arg;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import tcp.EchoThreadServer;

public class EchoThreadByArgs implements Runnable
{
	// �ͻ�������
	private Socket client = null;
	int clienId;
	// ���캯��
	public EchoThreadByArgs(Socket client)
	{
		this.client = client;
		// ���÷����̱߳��
		this.clienId = EchoThreadServer.getClientNum();
		// �����߳�������1
		EchoThreadServer.addClientNum();
	}
	// �߳�ִ����
	public void run()
	{
		// ��ʾ���ſͻ��˵�����
		BufferedReader inByclient = null;
		// �Կͻ��˵����
		PrintStream outToClient = null;
		try
		{
			// ��ȡ�Կͻ��˵������
			outToClient = new PrintStream(client.getOutputStream());
			// ��ȡ���Կͻ��˵�������
			inByclient = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			boolean isClientAlive = true; // ��־λ����ʾ����һֱ���ղ���Ӧ��Ϣ
			while (isClientAlive)
			{
				// ��ȡ���Կͻ��˵�����
				String str = inByclient.readLine();
				// ��ʾ�ͻ���Ҫ�˳�
				if ("exit".equals(str))
				{
					// ����ѭ�����ٽ��տͻ��˵�����.
					isClientAlive = false;
					// �����߳���Ŀ��1
					EchoThreadServer.minusClientNum();
				}
				// �رշ����
				else if ("shutdownServer".equals(str))
				{
					EchoThreadServer.shutdownServer();
					System.out.println("�رշ�����");
					break;
				} else
				{
					// ������ͻ���
					outToClient.println(
							"�����߳� " + this.clienId + " ECHO to client: " + str); // ��Ӧ��Ϣ
				}
			}
		} catch (Exception e)
		{
		}
	}
}
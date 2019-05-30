package tcp;

import java.net.*;
public class EchoThreadServer
{
	// ��̬����,���Ը��ĸñ���Ա�ֹͣ�����
	private static boolean isServerAlive = true;
	private static int clientNum = 0;
	public static void main(String args[]) throws Exception
	{
		// 1.���������������
		ServerSocket server = null;
		// 2.�ͻ��˵�����
		Socket client = null;
		// 3.��������������������6666�˿�
		server = new ServerSocket(6666);
		while (isServerAlive)
		{
			System.out.println("�ȴ��ͻ�������...");
			// 4.ȡ�����ӣ��ͻ���û����֮ǰ�ȵȴ����ӡ�
			client = server.accept();
			System.out.println("    �ͻ������ӳɹ�,��ǰ�ͻ�������:" + clientNum);
			if (isServerAlive)
			{
				// һ���ͻ�������֮��Ϊ�ÿͻ�������һ�������߳̽��з���
				new Thread(new EchoThread(client)).start();
			}
		}
		server.close();
		client.close();
		System.out.println("������Ѿ�ֹͣ...");
	}
	public static void addClientNum()
	{
		clientNum = clientNum + 1;
		// ע����,����ĸ�ֵ��������
		// ��++:��ȡֵ,������,�⽫�ﲻ�����ӵ�Ч��
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

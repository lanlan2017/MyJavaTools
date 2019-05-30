package tcp;

import java.net.*;
import java.io.*;
public class EchoServer
{
	public static void main(String args[]) throws Exception
	{
		// �������������
		ServerSocket server = null;
		// ����ͻ�������
		Socket client = null;
		BufferedReader buf = null;
		PrintStream out = null;
		// ����������������6666�˿�
		server = new ServerSocket(6666);
		//
		boolean f = true; // ��������λ
		String clientInput;

		while (f)
		{
			System.out.println("�ȴ�һ���ͻ�������....");
			// �ȴ��ͻ�������
			client = server.accept();
			System.out.println("    ��ͻ������ӳɹ�...");
			// ���ӳɹ�֮��
			// ���ɶԿͻ��˵������
			out = new PrintStream(client.getOutputStream());
			// ��ȡ�ͻ��˶Ա���������������
			buf = new BufferedReader(
					new InputStreamReader(client.getInputStream()));
			boolean flag = true; // ��־λ����ʾ����һֱ���ղ���Ӧ��Ϣ
			while (flag)
			{
				// �ӿͻ��˶���һ����Ϣ.
				clientInput = buf.readLine();
				// ����ж�����Ϣ������û�ж���
				if ("exit".equals(clientInput))
				{ // ������������Ϊexit��ʾ����
					flag = false;
				} else
				{
					out.println("ECHO : " + clientInput); // ��Ӧ��Ϣ
				}
			}
			// �رտͻ���
			client.close();
			System.out.println("    �ͻ������˳�...");
			// ������һ��ѭ��
		}
		// �رշ����
		server.close();
	}
}
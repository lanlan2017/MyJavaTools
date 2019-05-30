package tcp;

import java.net.*;
import java.io.*;
public class EchoClient
{
	public static void main(String args[]) throws Exception
	{
		// ����ͻ�������
		Socket client = null;
		// �����ͻ��˼���������6666�˿�
		client = new Socket("localhost", 6666);
		BufferedReader inputByServer = null;
		PrintStream outToServer = null;
		BufferedReader input = null;
		// ��ȡ����������
		input = new BufferedReader(new InputStreamReader(System.in));
		// ��ȡ�������Ա��ͻ��˵�������
		inputByServer = new BufferedReader(
				new InputStreamReader(client.getInputStream()));
		// ��ȡ�Է������������
		outToServer = new PrintStream(client.getOutputStream());

		boolean flag = true; // �����־λ
		// ���������û��
		while (flag && EchoThreadServer.isServerAlive())
		{
			System.out.print("������Ϣ��");
			// ���ռ�������
			String str = input.readLine();
			// �Ѽ���������ַ�������ӡ���������Ҳ���Ǵ�ӡ����������
			outToServer.println(str);
			// �ͻ����˳����߹رշ�����
			if ("exit".equals(str))
			{
				flag = false;
			} else if ("shutdownServer".equals(str))
			{
				flag = false;
				// ��Ч����,������������˵ļ�����������,
				// ��Ȼ����˻�һֱ��������޷��ر�
				new Socket("localhost", 6666);
			} else
			{
				// ���շ���˵�������
				String echo = inputByServer.readLine(); // ���շ��ؽ��
				System.out.println(echo); // �����Ӧ��Ϣ
			}
		}
		// �رշ������Ա��ͻ��˵�������
		inputByServer.close();
		// �رտͻ���
		client.close();
	}
}
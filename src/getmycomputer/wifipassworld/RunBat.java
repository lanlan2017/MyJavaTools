package getmycomputer.wifipassworld;

import java.io.IOException;

public class RunBat
{
	public static void run_cmd(String strcmd)
	{
		//
		Runtime rt = Runtime.getRuntime(); // Runtime.getRuntime()���ص�ǰӦ�ó����Runtime����
		Process ps = null; // Process���Կ��Ƹ��ӽ��̵�ִ�л��ȡ���ӽ��̵���Ϣ��
		try
		{
			ps = rt.exec(strcmd); // �ö����exec()����ָʾJava���������һ���ӽ���ִ��ָ���Ŀ�ִ�г��򣬲���������ӽ��̶�Ӧ��Process����ʵ����
			ps.waitFor(); // �ȴ��ӽ������������ִ�С�
		} catch (IOException e1)
		{
			e1.printStackTrace();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int i = ps.exitValue(); // ����ִ����ϵķ���ֵ
		if (i == 0)
		{
			System.out.println("ִ�����.");
		} else
		{
			System.out.println("ִ��ʧ��.");
		}

		ps.destroy(); // �����ӽ���
		ps = null;
	}
	public static void main(String[] args)
	{
		//������������ĿĿ¼��׼���õ�bat�ļ��������������ĿĿ¼�£���ѡ�����ļ���.bat���ĳ��ļ�����·����
		String strcmd = "cmd /c start  .bat";  		
		run_cmd(strcmd);  //���������run_cmd����ִ�в���
	}
}

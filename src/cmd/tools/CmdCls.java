package cmd.tools;

import java.io.IOException;

/**
 * ����̨��������.�������������eclipse����Ч,ֻ��CMD����������Ч.
 */
public class CmdCls
{
	/**
	 * ʵ����cmd����̨����������.
	 */
	public static void cls()
	{
		try
		{
			//// �½�һ�� ProcessBuilder����Ҫִ�е������� cmd.exe�������� /c �� cls
			new ProcessBuilder("cmd", "/c", "cls")
					// �� ProcessBuilder ���������ܵ��� Java �Ľ��̽��й�������������ķ���ֵҲ��һ��
					// ProcessBuilder
					.inheritIO()
					// ��ʼִ�� ProcessBuilder �е�����
					.start()
					// �ȴ� ProcessBuilder �е���������ִ�����
					// ������ȴ��������������������������������
					.waitFor();
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ��������
	}
}

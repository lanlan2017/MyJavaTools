package tcp;

public class THread
{
	static C c = new C();
	// flag������־���߳�ִ�н���
	static boolean flag = false;

	public static void main(String[] arg)
	{
		c.setvalue(12);
		System.out.println("���߳�ִ��֮ǰvalue��ֵ�ǣ�" + c.getvalue());
		System.out.println("ִ�����߳�");

		Thread mythread = new MyThread(c);
		mythread.start();

		// �ȴ����߳�ִ�н���
		while (!flag);
		System.out.println("���߳�ִ��֮��value��ֵ�ǣ�" + c.getvalue());
	}

	public static void callback()
	{
		System.out.println("���߳�ִ�н���");
		flag = true;
	}
}

class C
{
	private int value = 0;
	public int getvalue()
	{
		return value;
	}
	public void setvalue(int v)
	{
		this.value = v;
	}
}

class MyThread extends Thread
{
	public MyThread(C cc)
	{
		this.cc = cc;
	}
	private C cc;
	@Override
	public void run()
	{
		cc.setvalue(20);
		//�������̵߳Ļص�����
		THread.callback();
	}
}
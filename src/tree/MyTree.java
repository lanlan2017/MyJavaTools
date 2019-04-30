package tree;

import java.io.File;
import java.io.FilenameFilter;

public class MyTree
{
	private static String RootPath = null;
	private static MavenJavaWebFileNameFilter mavenJaveWebFileNameFilter = null;
	private static DefaultFileNameFileter defaultFileNameFileter=new DefaultFileNameFileter();
	public static void main(String args[])
	{
		// String path = "E:\\workspace\\Regex";
		// String path = "E:\\workspace_web\\app17a";
		// ͨ�����������Ŀ¼
		// String path =SysClipboardUtil.getSysClipboardText();
		// ʹ�õ�ǰĿ¼��ΪĿ¼
		String path = System.getProperty("user.dir");
		// �����Ŀ¼,����Ҫ�õ�
		RootPath = path;
		mavenJaveWebFileNameFilter = new MavenJavaWebFileNameFilter(RootPath);
		File dir = new File(path);
		switch (args.length)
		{
			case 1 :
				oneArgs(args, dir);
				break;
			default :
				printTreeDir(dir);
				break;
		}
	}
	/**
	 * ����һ�����������.
	 * 
	 * @param args
	 *            �����б�
	 * @param dir
	 *            Ŀ¼��File����.
	 */
	private static void oneArgs(String[] args, File dir)
	{
		// TODO Auto-generated method stub
		String arg1 = args[0];
		switch (arg1)
		{
			case "f" :
				printTreeFileAndDir(dir, defaultFileNameFileter);
				break;
			case "java" :
				printTreeFileAndDir(dir, mavenJaveWebFileNameFilter);
				break;

			default :
				break;
		}
	}
	/**
	 * ��ӡdir��ʾ��Ŀ¼��Ŀ¼��.
	 * 
	 * @param dir
	 *            ��ʾĿ¼��File����.
	 */
	public static void printTreeDir(File dir)
	{
		System.out.println(dir.getAbsolutePath());
		printTreeDir(dir, "", 0);
	}
	/**
	 * @param dir
	 */
	private static void printTreeDir(File dir, String prefix, int deep)
	{
		if (dir.isDirectory())
		{
			// ����Ŀ¼�µ���Ŀ¼�б�
			File[] dirList = dir.listFiles(new FilenameFilter()
			{
				@Override
				public boolean accept(File dir, String name)
				{
					// ���ļ���Ŀ¼,���Ҳ����Ե�ſ�ͷ,
					// ��ſ�ͷ��һ���������ļ�
					return new File(dir, name).isDirectory()
							&& !name.startsWith(".");
				}
			});
			String thisPrefix = "";
			String nextPrefix = "";
			for (int i = 0; i < dirList.length; i++)
			{
				if (deep >= 0)
				{
					// ����������һ��Ԫ��
					if ((i + 1 < dirList.length))
					{
						// �������һ��Ŀ¼����ӡ�������
						thisPrefix = prefix + "����";
						// ��һ����ӡ����ű�ʾչ��Ŀ¼
						nextPrefix = prefix + "�� ";
					} else
					{
						// ���һ����Ŀ¼��
						thisPrefix = prefix + "����";
						//
						nextPrefix = prefix + "  ";
					}
				}
				System.out.println(thisPrefix + dirList[i].getName());
				printTreeDir(dirList[i], nextPrefix, deep + 1);
			}
		}

	}

	/**
	 * @param dir
	 */
	public static void printTreeFileAndDir(File dir,FilenameFilter fileNameFilter)
	{
		// ��ӡ��Ŀ¼
		System.out.println(dir.getAbsolutePath());
		printTreeFileAndDir(dir, fileNameFilter,"", 0);
	}
	/**
	 * ��ӡĿ¼��
	 * 
	 * @param dir
	 *            Ŀ¼
	 * @param prefix
	 *            ǰ׺,��Ҫ��ӡ���ļ�����Ŀ¼֮ǰ
	 * @param deep
	 *            ���
	 */
	private static void printTreeFileAndDir(File dir,
			FilenameFilter fileNameFilter, String prefix, int deep)
	{
		// �г�Ŀ¼�µ���Ŀ¼
		File[] childs = dir.listFiles(fileNameFilter);
		// ������Ŀ¼
		for (int i = 0; i < childs.length; i++)
		{
			// ���εݹ��ǰ׺
			String thisPrefix = "";
			// ��һ���ݹ��ǰ׺
			String nextPrefix = "";
			if (deep >= 0)
			{
				// ����������һ��Ԫ��
				if ((i + 1 < childs.length))
				{
					nextPrefix = prefix + "�� ";
					thisPrefix = prefix + "����";
				} else
				{
					nextPrefix = prefix + "  ";
					thisPrefix = prefix + "����";
				}
			}
			System.out.println(thisPrefix + childs[i].getName());
			if (childs[i].isDirectory())
			{
				printTreeFileAndDir(childs[i], fileNameFilter, nextPrefix,
						deep + 1);
			}
		}
	}
}
package runable.tree;

import java.io.File;
import java.io.FilenameFilter;

public class MyTree
{
	private static String RootPath = null;
	private static MavenJavaWebFileNameFilter mavenJaveWebFileNameFilter = null;
	private static DefaultFileNameFileter defaultFileNameFileter = new DefaultFileNameFileter();
	public static void main(String args[])
	{
		// String path = "E:\\workspace\\Regex";
		// String path = "E:\\workspace_web\\app17a";
		// 通过剪贴板读入目录
		// String path =SysClipboardUtil.getSysClipboardText();
		// 使用当前目录作为目录
		String path = System.getProperty("user.dir");
		// 保存根目录,后续要用到
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
	 * 输入一个参数的情况.
	 * 
	 * @param args
	 *            参数列表
	 * @param dir
	 *            目录的File对象.
	 */
	private static void oneArgs(String[] args, File dir)
	{
		String arg1 = args[0];
		switch (arg1)
		{
			case "f" :
				printTreeFileAndDir(dir, defaultFileNameFileter);
				break;
			case "java" :
				printTreeFileAndDir(dir, mavenJaveWebFileNameFilter);
				break;
			case "dir" :
				printDir(dir);
				break;
			default :
				System.out.println("本程序支持参数如下:");
				System.out.println("   f:打印目录和文件");
				System.out.println("java:打印目录和文件,只保留Java项目必要的目录,忽略IDE创建的目录");
				System.out.println(" dir:打印目录和文件,在目录后面打印一个反斜杠表示目录");
				break;
		}
	}
	/**
	 * 显示当前目录列表,类似与cmd的dir命令.
	 * 
	 * @param dir
	 *            目录
	 */
	public static void printDir(File dir)
	{
		if (dir.isDirectory())
		{
			System.out.println(dir.getAbsolutePath());
			// 获取列表项下的目录
			File[] fileList = dir.listFiles(defaultFileNameFileter);
			for (int i = 0; i < fileList.length; i++)
			{
				// 如果不是最后一行
				if (i + 1 < fileList.length)
				{
					if (fileList[i].isDirectory())
					{
						System.out.println(
								"├─" + fileList[i].getName() + File.separator);
					} else
					{
						System.out.println("├─" + fileList[i].getName());
					}
				} else
				{
					if (fileList[i].isDirectory())
					{
						System.out.println(
								"└─" + fileList[i].getName() + File.separator);
					} else
					{
						System.out.println("└─" + fileList[i].getName());
					}

				}
			}
		}
	}
	/**
	 * 打印dir表示的目录的目录树.
	 * 
	 * @param dir
	 *            表示目录的File对象.
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
			// 生成目录下的子目录列表
			File[] dirList = dir.listFiles(new FilenameFilter()
			{
				@Override
				public boolean accept(File dir, String name)
				{
					// 该文件是目录,并且不能以点号开头,
					// 点号开头的一般是隐藏文件
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
					// 如果不是最后一个元素
					if ((i + 1 < dirList.length))
					{
						// 不是最后一个目录都打印这个符号
						thisPrefix = prefix + "├─";
						// 下一个打印这符号表示展开目录
						nextPrefix = prefix + "│ ";
					} else
					{
						// 最后一个子目录项
						thisPrefix = prefix + "└─";
						//
						nextPrefix = prefix + "  ";
					}
				}
				System.out.println(thisPrefix + dirList[i].getName()+File.separator);
				printTreeDir(dirList[i], nextPrefix, deep + 1);
			}
		}

	}

	/**
	 * 打印目录和文件夹,默认只打印到目录.
	 * 
	 * @param dir
	 * @param fileNameFilter
	 */
	public static void printTreeFileAndDir(File dir,
			FilenameFilter fileNameFilter)
	{
		// 打印根目录
		System.out.println(dir.getAbsolutePath());
		printTreeFileAndDir(dir, fileNameFilter, "", 0);
	}
	/**
	 * 打印目录树
	 * 
	 * @param dir
	 *            目录
	 * @param prefix
	 *            前缀,需要打印在文件或者目录之前
	 * @param deep
	 *            深度
	 */
	private static void printTreeFileAndDir(File dir,
			FilenameFilter fileNameFilter, String prefix, int deep)
	{
		// 列出目录下的子目录
		File[] childs = dir.listFiles(fileNameFilter);
		// 遍历子目录
		for (int i = 0; i < childs.length; i++)
		{
			// 本次递归的前缀
			String thisPrefix = "";
			// 下一个递归的前缀
			String nextPrefix = "";
			if (deep >= 0)
			{
				// 如果不是最后一个元素
				if ((i + 1 < childs.length))
				{
					nextPrefix = prefix + "│ ";
					thisPrefix = prefix + "├─";
				} else
				{
					nextPrefix = prefix + "  ";
					thisPrefix = prefix + "└─";
				}
			}
			// 如果是目录,则递归
			if (childs[i].isDirectory())
			{
				System.out.println(
						thisPrefix + childs[i].getName() + File.separator);
				printTreeFileAndDir(childs[i], fileNameFilter, nextPrefix,
						deep + 1);
			} else
				System.out.println(thisPrefix + childs[i].getName());
		}
	}
}
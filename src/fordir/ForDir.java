package fordir;

import java.io.File;
import java.io.FilenameFilter;
import last.line.reader.LastLineReader;

public class ForDir
{
	public static void main(String[] args)
	{
		// String path = SysClipboardUtil.getSysClipboardText();
		String path = "E:\\Blog\\blog7\\source\\_posts\\";
		File file = new File(path);
		if (file.exists())
		{
			forDir(file);
		}
	}

	/**
	 * @param file
	 */
	public static void forDir(File file)
	{
		File[] fileList = file.listFiles(new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				// ��markdown�ļ�,������Ŀ¼
				return name.endsWith(".md")
						|| new File(dir, name).isDirectory();
				// return false;
			}
		});
		for (int i = 0; i < fileList.length; i++)
		{
			if (fileList[i].isDirectory())
			{
				// �����Ŀ¼,�ݹ�
				forDir(fileList[i]);
				// System.out.println("dir:" + fileList[i].getAbsolutePath());
			} else if (fileList[i].isFile())
			{
				// LastLineReader.getLastLineStr(file, "utf-8");
				if (">ԭ������: null".equals(
						LastLineReader.getLastLineStr(fileList[i], "utf-8")))
				{
					System.out.println(fileList[i].getAbsolutePath() + "---"
							+ LastLineReader.getLastLineStr(fileList[i],
									"utf-8"));

				}
				// ��ȡ�ļ����һ��,�ж�Ҫ��Ҫ��������Ŀ¼
			}
		}
	}
}

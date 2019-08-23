package tree;

import java.io.File;
import java.io.FilenameFilter;

public class MavenJavaWebFileNameFilter implements FilenameFilter
{

	private String RootPath = null;
	MavenJavaWebFileNameFilter(String RootPath)
	{
		this.RootPath = RootPath;
	}
	@Override
	public boolean accept(File dir, String name)
	{
		// ���ļ������Ե�ſ�ͷ,
		// Ҳ������txt�ļ���β
		// �ļ����Ʋ�����bin
		// �ļ����Ʋ�����target
		if (name.startsWith(".") || name.endsWith(".txt"))
		{
			return false;
		}
		// ����Ǹ�Ŀ¼
		if (RootPath.equals(dir.getAbsolutePath()))
		{
			// ��һ��Ŀ¼�µ�binĿ¼,
			// ����targetĿ¼(maven,���)����java��Ŀ����Ŀ¼���Բ����
			if ("bin".equals(name) || "target".equals(name)
					|| "test".equals(name)||"build".equals(name))
			{
				return false;
			}
		}
		return true;
	}
}

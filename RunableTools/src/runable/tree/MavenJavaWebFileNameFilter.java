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
		// 该文件不能以点号开头,
		// 也不能以txt文件结尾
		// 文件名称不能是bin
		// 文件名称不能是target
		if (name.startsWith(".") || name.endsWith(".txt"))
		{
			return false;
		}
		// 如果是根目录
		if (RootPath.equals(dir.getAbsolutePath()))
		{
			// 第一级目录下的bin目录,
			// 或者target目录(maven,输出)不是java项目必须目录所以不输出
			if ("bin".equals(name) || "target".equals(name)
					|| "process/tools".equals(name)||"build".equals(name))
			{
				return false;
			}
		}
		return true;
	}
}

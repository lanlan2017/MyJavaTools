package tree;

import java.io.File;
import java.io.FilenameFilter;

public class DirFileNameFilter implements FilenameFilter
{

	/* *
	 * <p>Title: accept</p>  
	 * <p>Description: </p>  
	 * @param dir 表示目录的File对象的引用.
	 * @param name 目录下的列表项的名称
	 * @return 是否是目录
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)  
	 */
	@Override
	public boolean accept(File dir, String name)
	{
		return new File(dir,name).isDirectory();
	}

}

package tree;

import java.io.File;
import java.io.FilenameFilter;

public class DirFileNameFilter implements FilenameFilter
{

	/* *
	 * <p>Title: accept</p>  
	 * <p>Description: </p>  
	 * @param dir ��ʾĿ¼��File���������.
	 * @param name Ŀ¼�µ��б��������
	 * @return �Ƿ���Ŀ¼
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)  
	 */
	@Override
	public boolean accept(File dir, String name)
	{
		return new File(dir,name).isDirectory();
	}

}

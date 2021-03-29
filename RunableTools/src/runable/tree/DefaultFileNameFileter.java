package runable.tree;

import java.io.File;
import java.io.FilenameFilter;

public class DefaultFileNameFileter implements FilenameFilter
{
	@Override
	public boolean accept(File dir, String name)
	{
		return true;
	}
}

package br.adjustfn.engine.comparator;

import java.io.File;
import java.util.Comparator;

public class FileComparator implements Comparator<File> 
{

	@Override
	public int compare(File file1, File file2) 
	{
		long date1 = file1.lastModified();
		long date2 = file1.lastModified();
		long result = date1-date2;
		int ret = Integer.valueOf(String.valueOf(result));
		if(ret == 0)
		{
			ret = file1.getName().compareTo(file2.getName());
		}
		return ret;
	}
}

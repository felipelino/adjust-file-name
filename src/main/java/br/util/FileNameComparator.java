package br.util;

import java.io.File;
import java.util.Comparator;

/**
 * @author Felipe Lino
  */
public class FileNameComparator implements Comparator<File> 
{
	@Override
	public int compare(File file1, File file2) 
	{
		String name1 = file1.getAbsolutePath();
		String name2 = file2.getAbsolutePath();
		return name1.compareTo(name2);
	}

}

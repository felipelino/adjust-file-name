package br.adjustfn.engine.comparator.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import br.adjustfn.engine.comparator.FileComparator;
import br.adjustfn.test.util.Util;

public class FileComparatorTest 
{
	@Test
	public void comparatorEquals()
	{
		File file1 = Util.getFileForTest("compare/old.txt");
		
		Comparator<File> comparator = new FileComparator();
		int value = comparator.compare(file1, file1);
		Assert.assertEquals(0, value);
	}
	
	@Test
	public void comparatorSort()
	{
		File file1 = Util.getFileForTest("compare/old.txt");
		
		File file2 = Util.getFileForTest("compare/new.txt");
		
		List<File> list = new ArrayList<File>(2);
		list.add(file1);
		list.add(file2);
		Assert.assertEquals("old.txt", list.get(0).getName());
		Assert.assertEquals("new.txt", list.get(1).getName());
		
		
		Comparator<File> comparator = new FileComparator();
		Collections.sort(list, comparator);
		Assert.assertEquals("new.txt", list.get(0).getName());
		Assert.assertEquals("old.txt", list.get(1).getName());
	}
	
	@Test
	@Ignore
	public void comparatorTwoFiles01()
	{
		File file1 = Util.getFileForTest("compare/old.txt");
		file1.setLastModified(10);
		
		File file2 = Util.getFileForTest("compare/new.txt");
		file2.setLastModified(5);
		
		Comparator<File> comparator = new FileComparator();
		int value = comparator.compare(file1, file2);
		Assert.assertTrue((value<0));
	}
	
	@Test
	@Ignore
	public void comparatorTwoFiles02()
	{
		File file1 = Util.getFileForTest("compare/old.txt");
		file1.setLastModified(10);
		
		File file2 = Util.getFileForTest("compare/old.txt");
		file2.setLastModified(5);
		
		Comparator<File> comparator = new FileComparator();
		int value = comparator.compare(file2, file1);
		Assert.assertTrue((value>0));
	}
}

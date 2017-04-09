package br.adjustfn.engine.filter.test;

import java.io.File;
import java.io.FilenameFilter;

import junit.framework.Assert;

import org.junit.Test;

import br.adjustfn.engine.filter.RegexFilter;

public class RegexFilterTest 
{
	@Test
	public void acceptTestNormal() throws Exception
	{
		String fileName01 = ClassLoader.getSystemResource("regex/img01.txt").getFile();
		File file01 = new File(fileName01); 
		FilenameFilter filter = new RegexFilter("img[0-9]*.[a-z]*");
		boolean result = filter.accept(file01, fileName01);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void acceptTestFail() throws Exception
	{
		String fileName = ClassLoader.getSystemResource("regex/none.txt").getFile();
		File file = new File(fileName); 
		FilenameFilter filter = new RegexFilter("img[0-9]*.[a-z]*");
		boolean result = filter.accept(file, fileName);
		Assert.assertEquals(false, result);
	}
}

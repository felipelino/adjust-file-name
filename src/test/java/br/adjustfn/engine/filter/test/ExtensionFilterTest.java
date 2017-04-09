package br.adjustfn.engine.filter.test;

import java.io.File;
import java.io.FilenameFilter;

import junit.framework.Assert;

import org.junit.Test;

import br.adjustfn.engine.filter.ExtensionFilter;

public class ExtensionFilterTest 
{
	@Test
	public void acceptTestNormal() throws Exception
	{
		String fileName01 = ClassLoader.getSystemResource("extension/extensionAccepted01.txt").getFile();
		File file01 = new File(fileName01); 
		FilenameFilter filter = new ExtensionFilter("txt");
		boolean result = filter.accept(file01, fileName01);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void acceptTestUppercase() throws Exception
	{
		String fileName = ClassLoader.getSystemResource("extension/extensionAccepted02.TXT").getFile();
		File file = new File(fileName); 
		FilenameFilter filter = new ExtensionFilter("txt");
		boolean result = filter.accept(file, fileName);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void acceptTestVariousCase() throws Exception
	{
		String fileName = ClassLoader.getSystemResource("extension/extensionAccepted03.TxT").getFile();
		File file = new File(fileName); 
		FilenameFilter filter = new ExtensionFilter("txt");
		boolean result = filter.accept(file, fileName);
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void acceptTestFail() throws Exception
	{
		String fileName = ClassLoader.getSystemResource("extension/extensionNotAccepted01.tat").getFile();
		File file = new File(fileName); 
		FilenameFilter filter = new ExtensionFilter("txt");
		boolean result = filter.accept(file, fileName);
		Assert.assertEquals(false, result);
	}
}

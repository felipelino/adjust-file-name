package br.adjustfn.engine.test;

import java.io.File;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import br.adjustfn.engine.AdjustFileName;

public class AdjustFileNameTest 
{

	private File getDirectory()
	{
		String dirName = ClassLoader.getSystemResource("adjust").getFile();
		dirName = dirName.replaceAll("bin", "src/test/resources");
		dirName = dirName.replaceAll("%20", " ");
		dirName = dirName.substring(1);
		File dir = new File(dirName);
		Assert.assertTrue(dir.exists());
		Assert.assertTrue(dir.isDirectory());
		
		return dir;
	}
	
	@Test
	public void getFilterFileNamesAllAccept()
	{
		File dir = getDirectory();
		File[] list = dir.listFiles();
		int originalSize = list.length;
		Assert.assertEquals(5, originalSize);
		
		AdjustFileName adjustFileName = new AdjustFileName();
		Collection<File> files = adjustFileName.getFilterFiles(dir);
		Assert.assertEquals(originalSize, files.size());
	}
	
	@Test
	public void getFilterFileNamesWithRegex()
	{
		File dir = getDirectory();
		
		int originalSize = dir.list().length;
		Assert.assertEquals(5, originalSize);
		
		AdjustFileName adjustFileName = new AdjustFileName(null, "arq[0-9]*.[a-z]*");
		Collection<File> files = adjustFileName.getFilterFiles(dir);
		Assert.assertEquals(4, files.size());
	}
	
	@Test
	public void getFilterFileNamesWithExtension()
	{
		File dir = getDirectory();
		
		int originalSize = dir.list().length;
		Assert.assertEquals(5, originalSize);
		
		AdjustFileName adjustFileName = new AdjustFileName("txt", null);
		Collection<File> files = adjustFileName.getFilterFiles(dir);
		Assert.assertEquals(4, files.size());
	}
	
	@Test
	public void getFilterFileNamesWithFilter()
	{
		File dir = getDirectory();
		
		int originalSize = dir.list().length;
		Assert.assertEquals(5, originalSize);
		
		AdjustFileName adjustFileName = new AdjustFileName("txt", "arq[0-9]*.[a-z]*");
		Collection<File> files = adjustFileName.getFilterFiles(dir);
		Assert.assertEquals(3, files.size());
	}
	
	@Test
	public void renameFile()
	{
		String newName = AdjustFileName.renameFile("d:\\test\\folder1\\level2\\file.jpg", "base_", 1, 1, 0);
		Assert.assertEquals("d:\\test\\folder1\\level2\\base_1.jpg", newName);
		
	}
	
	@Test
	public void getNumberWithZeroLead()
	{
		String number = AdjustFileName.getNumberWithZeroLead(999, 5);
		Assert.assertEquals("005", number);
	}
	
	@Test
	public void renameFilesInDir01()
	{
		AdjustFileName adjustFileName = new AdjustFileName("txt", "arq0[0-9]*.[a-z]*", "base_", 1);	
		File dir = getDirectory();
		
		List<File> fileList = adjustFileName.renameFilesInDir(dir);
		Assert.assertNotNull(fileList);
		Assert.assertEquals(3, fileList.size());
	}
	
	@Test
	public void renameFilesInDir02()
	{
		
		AdjustFileName adjustFileName = new AdjustFileName("txt", "base_[0-9]*.[a-z]*", "arq0", 1);
		File dir = getDirectory();
		
		List<File> fileList = adjustFileName.renameFilesInDir(dir);
		Assert.assertNotNull(fileList);
		Assert.assertEquals(3, fileList.size());
	}
	
	@Test
	@Ignore
	public void renameFilesInDir03()
	{
		AdjustFileName adjustFileName = new AdjustFileName("jpg", null, "2011-12-04_Shopping_", 1);	
		File dir = new File("H:\\DCIM\\Camera\\2011-12-04_Shopping");
		
		List<File> fileList = adjustFileName.renameFilesInDir(dir);
		Assert.assertNotNull(fileList);
	}
}

package br.adjustfn.test.util;

import java.io.File;

import junit.framework.Assert;

public class Util 
{
	public static File getFileForTest(String fileName)
	{
		String name = ClassLoader.getSystemResource(fileName).getFile();
		name = name.replaceAll("bin", "src/test/resources");
		name = name.replaceAll("%20", " ");
		name = name.substring(1);
		File file = new File(name);
		Assert.assertTrue(file.exists());
		return file;
	}
}

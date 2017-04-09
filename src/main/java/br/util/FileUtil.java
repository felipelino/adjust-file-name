package br.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Felipe Lino
 * Cont�m m�todos utilit�rios para manipula��o de arquivo
 */
public class FileUtil
{
	/**
	 * Carrega arquivo properties
	 * @param basePath
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Properties loadProperties(String basePath, String fileName) throws FileNotFoundException, IOException
	{
		if(!fileName.endsWith(".properties"))
		{
			fileName = fileName + ".properties";
		}
		Properties properties = new Properties();
		File file = FileUtil.findFile(basePath, fileName);
		FileInputStream inStream = new FileInputStream(file);
		properties.load(inStream);
		return properties;
	}
	
	/**
	 * Carrega arquivo properties do diret�rio corrente
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Properties loadProperties(String fileName) throws FileNotFoundException, IOException
	{
		return FileUtil.loadProperties(FileUtil.getCurrentDirectory(), fileName);
	}
	
	/**
	 * Busca arquivo no diret�rio corrente
	 * @param fileName
	 * @return
	 */
	public static File findFile(String fileName)
	{
		int idx = fileName.indexOf("/");
		if(idx > 0)
		{
			idx++;
		}
		fileName = StringUtils.trim(fileName.substring(idx));
		return FileUtil.findFile(FileUtil.getCurrentDirectory(), fileName);
	}
	
	/**
	 * Busca recursivamente um arquivo
	 * @param basePath
	 * @param fileName
	 * @return
	 */
	public static File findFile(String basePath, String fileName)
	{
		File baseDir = new File(basePath);
		File[] files = baseDir.listFiles();
		File fileFound = null;
		for(File fileOrDir : files)
		{
			if(fileOrDir.isFile())
			{
				String testFileName = fileOrDir.getName();
				if(fileName.equals(testFileName))
				{
					fileFound = fileOrDir;
					break;
				}
			}
			else if(fileOrDir.isDirectory())
			{
				fileFound = FileUtil.findFile(fileOrDir.getAbsolutePath(), fileName);
				if(fileFound != null){
					break;
				}
			}
		}
		return fileFound;
	}
	
	/**
	 * Retorna o diretorio corrente da aplica��o
	 * @return
	 */
	public static String getCurrentDirectory()
	{
		return System.getProperty("user.dir");
	}
	
	/**
	 * Retorna o diret�rio da home do usu�rio
	 * @return
	 */
	public static String getUserHomeDirectory()
	{
		return System.getProperty("user.home");
	}
	
	/**
	 * Retorna o diret�rio pai caso a URL aponte para um arquivo
	 * @param url
	 * @return
	 */
	public static File getDirectory(String url)
	{
		url = StringUtils.trim(url);
		File dir = new File(url);
		if(dir.isFile())
		{
			dir = dir.getParentFile();
		}
		return dir;
	}
	
	/**
	 * Retorna o diret�rio pai caso a URL aponte para um arquivo
	 * @param file
	 * @return
	 */
	public static File getDirectory(File file)
	{
		File dir = file;
		if(file.isFile())
		{
			dir = file.getParentFile();
		}
		return dir;
	}
}

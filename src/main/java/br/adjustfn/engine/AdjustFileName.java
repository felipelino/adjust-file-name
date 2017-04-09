package br.adjustfn.engine;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.list.TreeList;

import br.adjustfn.engine.comparator.FileComparator;
import br.adjustfn.engine.filter.ExtensionFilter;
import br.adjustfn.engine.filter.RegexFilter;

public class AdjustFileName 
{
	/** Filtro para extensao de arquivo */
	private FilenameFilter extensionFilter = null;
	
	/** Filtro para expressao regular */
	private FilenameFilter regexFilter = null;
	
	/** Nome Base */
	private String baseName = "";
	
	/** Contador Base */
	private int baseCount = 0;
	
	/**
	 * Construtor
	 */
	public AdjustFileName()
	{
		this.extensionFilter = new ExtensionFilter();
		this.regexFilter  	 = new RegexFilter();
		this.baseName = "";
		this.baseCount = 0;
	}
	
	/**
	 * Construtor
	 * @param baseName
	 * 
	 */
	public AdjustFileName(String baseName)
	{
		this.baseName = baseName;
	}
	
	/**
	 * Construtor
	 * @param baseName
	 * @param baseCount
	 */
	public AdjustFileName(String baseName, int baseCount)
	{
		this.baseName = baseName;
		this.baseCount = baseCount;
	}
	
	/**
	 * Construtor
	 * @param extension
	 * @param regex
	 */
	public AdjustFileName(String extension, String regex)
	{
		this.extensionFilter = new ExtensionFilter(extension);
		this.regexFilter  	 = new RegexFilter(regex);
	}
	
	/**
	 * Construtor
	 * @param extension
	 * @param regex
	 * @param baseName
	 */
	public AdjustFileName(String extension, String regex, String baseName)
	{
		this.extensionFilter = new ExtensionFilter(extension);
		this.regexFilter  	 = new RegexFilter(regex);
		this.baseName		 = baseName;
	}
	
	/**
	 * Construtor
	 * @param extension
	 * @param regex
	 * @param baseName
	 * @param baseCount
	 */
	public AdjustFileName(String extension, String regex, String baseName, int baseCount) 
	{
		super();
		this.extensionFilter = new ExtensionFilter(extension);
		this.regexFilter  	 = new RegexFilter(regex);
		this.baseName = baseName;
		this.baseCount = baseCount;
	}

	/**
	 * Renomeia os arquivos que estao dentro de um diretorio
	 * @param dir
	 * @return
	 */
	public List<File> renameFilesInDir(File dir)
	{
		Collection<File> files = getFilterFiles(dir);
		return this.renameFiles(files);
	}
	
	/**
	 * Retorna os arquivos que estao dentro de um diretorio que satisfazem os filtros
	 * @param dir
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Collection<File> getFilterFiles(File dir) 
	{
		File[] filesExtFilter = dir.listFiles(this.extensionFilter);
		File[] filesRegexFilter = dir.listFiles(this.regexFilter);
		List<File> list1 = Arrays.asList(filesExtFilter);
		List<File> list2 = Arrays.asList(filesRegexFilter);
		Collection<File> intersection = (Collection<File>) org.apache.commons.collections.CollectionUtils.intersection(list1, list2);
		return intersection;
	}

	/**
	 * Renomeia uma lista de arquivos
	 * @param files
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<File> renameFiles(Collection<File> files)
	{
		List<File> fileList = new TreeList(files);
		Collections.sort(fileList, new FileComparator());
		int size = fileList.size();
		for(int i = 0; i < size; i++)
		{
			File file = fileList.get(i);
			if(file.isFile())
			{
				String absoluteFilePath = file.getAbsolutePath();
				String newFileName = renameFile(absoluteFilePath, this.baseName, size, this.baseCount, i);
				File newFile = new File(newFileName);
				file.renameTo(newFile);
			}
		}
		
		return fileList;
	}
	
	/**
	 * Renomeia o nome final do arquivo mantendo a extensao
	 * @param absolutePath
	 * @param baseName
	 * @param totalFiles
	 * @param baseCount
	 * @param count
	 * @return
	 */
	public static String renameFile(String absolutePath, String baseName, int totalFiles, int baseCount, int count)
	{
		int index = absolutePath.lastIndexOf(File.separator);
		/* Obtem caminho completo onde o arquivo se encontra ate diretorio */
		StringBuilder newName = new StringBuilder(absolutePath.substring(0, index+1)); 
		
		/* Apena novo nome final do arquivo */
		String number = getNumberWithZeroLead(totalFiles + baseCount, count + baseCount);
		newName.append(baseName).append(number);
		int lastIndex = absolutePath.lastIndexOf(".");
		
		/* Obtem extensao do arquivo */
		String extension = absolutePath.substring(lastIndex);
		extension = extension.toLowerCase();
		newName.append(extension);
		return newName.toString();
	}
	
	/**
	 * @param base
	 * @param value
	 * @return
	 */
	public static String getNumberWithZeroLead(int base, int value)
	{
		String str = Integer.toString(base);
		int sizeZero = str.length();
		sizeZero = sizeZero - Integer.toString(value).length();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < sizeZero ; i++)
		{
			sb.append("0");
		}
		sb.append(value);
		return sb.toString();
	}
}

package br.adjustfn.engine.filter;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.commons.lang3.StringUtils;

public class ExtensionFilter implements FilenameFilter 
{
	/** Extensão do arquivo */
	private String extension = null;
	
	/**
	 * Construtor
	 * @param extension
	 */
	public ExtensionFilter()
	{
		this.extension = null;	
	}
	
	/**
	 * Construtor
	 * @param extension
	 */
	public ExtensionFilter(String extension)
	{
		this.extension = StringUtils.trimToNull(extension);	
	}

	@Override
	public boolean accept(File file, String fileName) 
	{
		Boolean isAccept = Boolean.FALSE;
		if(this.extension == null)
		{
			isAccept = Boolean.TRUE;
		}
		else
		{
			if(fileName != null && fileName.length() > 0)
			{
				int index = fileName.lastIndexOf(".");
				String ext = fileName.substring(index+1); 
				if(StringUtils.equalsIgnoreCase(this.extension, ext))
				{
					isAccept = Boolean.TRUE;
				}
			}
		}	
		return isAccept;
	}
}

package br.adjustfn.engine.filter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class RegexFilter implements FilenameFilter 
{
	/** Expressao Regular */
	public String regex = null;
	
	/**
	 * Construtor
	 */
	public RegexFilter()
	{
		this.regex = null;
	}
	
	/**
	 * Construtor
	 * @param regex
	 */
	public RegexFilter(String regex)
	{
		this.regex = StringUtils.trim(regex);
	}
	
	/**
	 * @see java.io.FilenameFilter#accept(java.io.File, java.lang.String)
	 */
	@Override
	public boolean accept(File file, String fileName) 
	{
		Boolean isAccept = Boolean.FALSE;
		if(this.regex == null)
		{
			isAccept = Boolean.TRUE;
		}
		else
		{
			String shortName = fileName;
		    Pattern pattern = Pattern.compile(this.regex);  
		    Matcher matcher = pattern.matcher(shortName);
		    isAccept = matcher.find();
		}	
	
		return isAccept;
	}
	
}

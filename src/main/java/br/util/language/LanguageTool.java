package br.util.language;

import java.util.Properties;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;

import br.util.FileUtil;

/**
 * Classe Auxiliar para suporte multilingue.
 *  
 * @author Felipe Lino
 */
public class LanguageTool
{
	/** Logger da classe */
	private static final Logger log = Logger.getLogger(LanguageTool.class);
	
	/** Nome do arquivo Properties com o mapeamento Chave e Conte�do */
	private static String propLanguageFileName = null;
	
	/** Objeto Properties com o mapeamento Chave e Conte�do */
	private static Properties langProperties = null;
	
	/**
	 * Map que faz o mapeamento Chave e Conte�do default
	 * Usada quando o properties n�o puder ser carregado ou alguma chave n�o for encontrada
	 * no properties
	 */
	private static Properties langDefaultProperties = null;
	
	/**
	 * Seta o idioma default a ser usado logo no in�cio do programa.
	 * Passando o nome do arquivo properties a ser consultado.
	 * @param strFile Nome do arquivo Properties 
	 */
	public static void setLanguageDefault(String strFile)
	{
		propLanguageFileName = strFile;
		loadPropertiesLanguage();
	}
	
	/**
	 * Seta o idioma a ser usado passando o nome do arquivo properties a ser consultado.
	 * @param strFile Nome do arquivo Properties
	 */
	public static void changeLanguage(String strFile)
	{
		propLanguageFileName = strFile;
		loadPropertiesLanguage();
	}
	/**
	 * Carrega o arquivo properties com o idioma escolhido	 
	 */
	public static void loadPropertiesLanguage()
	{
		try
		{
			LanguageTool.langProperties = FileUtil.loadProperties(LanguageTool.propLanguageFileName);
		}
		catch (Exception e) 
		{
			LanguageTool.langProperties = null;
		}
	}
	
	/**
	 * Adiciona o par Chave e Conte�do a Map Default. O conte�do ser� a String
	 * default a ser usada caso o properties n�o possa ser carregado ou a chave
	 * n�o seja encontrada.
	 * @param strKey Chave
	 * @param strContent Conte�do
	 */
	public static void addString(String strKey, String strContent)
	{
		if(langDefaultProperties == null){
			langDefaultProperties = new Properties();
		}
		langDefaultProperties.put(strKey, strContent);
	}
		
	/**
	 * Retorna String para chave fornecida, buscando no arquivo de properties
	 * do idioma selecionado.
	 * @param strKey Chave a ser buscada no arquivo de properties
	 * @return String do arquivo de properties do idioma selecionado, ou default caso
	 * n�o seja encontrado a chave no arquivo properties.
	 */
	public static String getString(String strKey)
	{
		
		String value = null;
		if(strKey != null)
		{
			if(langDefaultProperties != null)
			{
				if(langProperties == null){
					value = ObjectUtils.toString(langDefaultProperties.get(strKey));
				}
				else{
					Object defaultValue = langDefaultProperties.get(strKey);
					value = langProperties.getProperty(strKey, ObjectUtils.toString(defaultValue));
				}
			}
			else 
			{
				if(langProperties != null){
					value = langProperties.getProperty(strKey);
				}
			}
		}
		log.debug("getString for key:["+strKey+"] - value:["+value+"]");
		return value;
	}
	
	/**
	 * Retorna String para a chave fornecida caso encontre, sen�o retorna oldValue
	 * @param strKey
	 * @param oldValue
	 * @return
	 */
	public static String getString(String strKey, String oldValue)
	{
		String newValue = getString(strKey);
		if(newValue == null)
		{
			log.warn("Nao foi possivel obter o valor para a chave:["+strKey+"]\nRetornando valor antigo:["+oldValue+"]\n");
			return oldValue;
		}
		else{
			return newValue;
		}
	}

}

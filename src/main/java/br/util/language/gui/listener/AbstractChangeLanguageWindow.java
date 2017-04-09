package br.util.language.gui.listener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;

import br.util.FileUtil;
import br.util.WindowUtil;
import br.util.language.LanguageTool;

/**
 * @author Felipe Lino
 */
public class AbstractChangeLanguageWindow extends JFrame implements ChangeLanguageWindow
{

	/** Default Serial Version UID */
	private static final long serialVersionUID = 1L;

	/** Objetos que podem ter seus rótulos alterados */
	protected Set<Object> refreshLabelSet;
	
	/* Services */
	/** Serviço de Idioma */
	protected LanguageActionListener languageListener;
	
	/** Obtem propriedades da aplicação */
	protected Properties properties; 
	
	/**
	 * Construtor
	 * @param configFileName
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public AbstractChangeLanguageWindow(String configFileName) throws FileNotFoundException, IOException
	{
		super();
		this.refreshLabelSet = new HashSet<Object>();
		this.properties = FileUtil.loadProperties(FileUtil.getCurrentDirectory(), configFileName);
		LanguageTool.setLanguageDefault(properties.getProperty(ChangeLanguageWindow.LANGUAGE_DEFAULT));
		
		this.languageListener = new LanguageActionListener(this);
	}
	
	/**
	 * Cria JLabel configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 * @param key
	 * @param defaultLabel
	 * @return
	 */
	public JLabel createLabel(String key, String defaultLabel){
		return WindowUtil.createLabel(key, defaultLabel, this.refreshLabelSet);
	}
	
	/**
	 * Cria JButton configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 * @param key
	 * @param defaultLabel
	 * @return
	 */
	public JButton createButton(String key, String defaultLabel){
		return WindowUtil.createButton(key, defaultLabel, this.refreshLabelSet);
	}
	
	/**
	 * Cria JRadioButton configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 * @param key
	 * @param defaultLabel
	 * @return
	 */
	public JRadioButton createJRadioButton(String key, String defaultLabel){
		return WindowUtil.createJRadioButton(key, defaultLabel, this.refreshLabelSet); 
	}

	/**
	 * Cria JCheckBox configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 * @param key
	 * @param defaultLabel
	 * @return
	 */
	public JCheckBox createJCheckBox(String key, String defaultLabel){
		return WindowUtil.createJCheckBox(key, defaultLabel, this.refreshLabelSet); 
	}

	
	/**
	 * Cria JMenu configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 * @param key
	 * @param defaultLabel
	 * @return
	 */
	public JMenu createJMenu(String key, String defaultLabel){
		return WindowUtil.createJMenu(key, defaultLabel, this.refreshLabelSet);
	}
	
	/**
	 * Cria JMenuItem configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 * @param key
	 * @param defaultLabel
	 * @return
	 */
	public JMenuItem createJMenuItem(String key, String defaultLabel){
		return WindowUtil.createJMenuItem(key, defaultLabel, this.refreshLabelSet);
	}
	
	
	/**
	 * @see br.util.language.gui.listener.ChangeLanguageWindow#refreshLabels()
	 */
	@Override
	public void refreshLabels() 
	{
		WindowUtil.refreshLabels(this.refreshLabelSet);
	}

}

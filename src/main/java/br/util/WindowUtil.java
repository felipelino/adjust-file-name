package br.util;

import java.awt.Component;
import java.io.File;
import java.util.Set;

import javax.swing.AbstractButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;

import org.apache.log4j.Logger;

import br.util.language.LanguageTool;

/**
 * @author Felipe Lino
 *
 * Classe utilitaria para manipular Janelas
 */
public class WindowUtil
{
	/** Logger da Classe */
	private static final Logger log = Logger.getLogger(WindowUtil.class);
	
	/**
	 * Cria um ícone
	 * @param path
	 * @param description
	 * @return
	 */
	public static ImageIcon createImageIcon(String path, String description) 
	{
		File file = FileUtil.findFile(path);
		//java.net.URL imgURL = getClass().getResource(path);
		if(file != null && file.exists()) 
		{
			return new ImageIcon(file.getAbsolutePath(), description);
		}
		else 
		{
			log.error("Could not find file:["+path+"]");
			return null;
		}
	}
	
	/**
	 * Usando o LanguageTool altera o rotulo do item da janela
	 * @param item
	 */
	public static void refreshLabel(AbstractButton item)
	{
		Icon icon = item.getIcon();
		if(icon == null)
		{
			String key = item.getName();
			String newText = LanguageTool.getString(key, item.getText());
			item.setText(newText);
		}
	}
	
	/**
	 * Usando o LanguageTool altera o rotulo
	 * @param label
	 */
	public static void refreshLabel(JLabel label)
	{
		String key = label.getName();
		String newText = LanguageTool.getString(key, label.getText());
		label.setText(newText);
	}
	
	/**
	 * Usando o LanguageTool altera o rotulo do menu
	 * @param label
	 */
	public static void refreshLabel(JMenu menu)
	{
		String key = menu.getName();
		String newText = LanguageTool.getString(key, menu.getText());
		menu.setText(newText);
	}
	
	/**
	 * Usando o LanguageTool altera o rotulo do item de menu
	 * @param label
	 */
	public static void refreshLabel(JMenuItem menuItem)
	{
		String key = menuItem.getName();
		String newText = LanguageTool.getString(key, menuItem.getText());
		menuItem.setText(newText);
	}
	
	/**
	 * Usando o LanguageTool altera o rotulo
	 * @param label
	 */
	public static void refreshLabel(Object item)
	{
		if(item instanceof JLabel)
		{
			WindowUtil.refreshLabel((JLabel) item);
		}
		else if(item instanceof AbstractButton)
		{
			WindowUtil.refreshLabel((AbstractButton) item);
		}
		else if(item instanceof JMenu)
		{
			WindowUtil.refreshLabel((JMenu) item);
		}
		else if(item instanceof JMenuItem)
		{
			WindowUtil.refreshLabel((JMenuItem) item);
		}
		else
		{
			log.warn("Item nao eh instancia de classe valida. Item Class:["+item.getClass()+"]");
		}
	}
	
	/**
	 * Usando o LanguageTool altera o rotulo de uma lista de objetos
	 * @param items
	 */
	public static void refreshLabels(Set<Object> refreshLabelSet)
	{
		for(Object item : refreshLabelSet)
		{
			WindowUtil.refreshLabel(item);
		}
	}

	/**
	 * Usando o LanguageTool altera o rotulo das abas
	 * @param tabs
	 */
	public static void refreshLabel(JTabbedPane tabs)
	{
		for(int i = 0; i < tabs.getTabCount(); i++)
		{
			Component component = tabs.getComponent(i);
			String key = component.getName();
			String oldValue = tabs.getTitleAt(i);
			tabs.setTitleAt(i, LanguageTool.getString(key, oldValue));
		}
	}
	
	/**
	 * Cria JLabel configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 *  
	 * @param key	Chave para obter o conteúdo do rótulo
	 * @param defaultLabel Rótulo default
	 * @param refreshLabelSet Conjunto de objetos que podem sofrer atualização do rótulo
	 * @return
	 */
	public static JLabel createLabel(String key, String defaultLabel, Set<Object> refreshLabelSet)
	{
		LanguageTool.addString(key, defaultLabel);
		JLabel label = new JLabel(LanguageTool.getString(key));
		label.setName(key);
		refreshLabelSet.add(label);
		return label;
	}

	/**
	 * Cria JButton configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 *  
	 * @param key	Chave para obter o conteúdo do rótulo
	 * @param defaultLabel Rótulo default
	 * @param refreshLabelSet Conjunto de objetos que podem sofrer atualização do rótulo
	 * @return
	 */
	public static JButton createButton(String key, String defaultLabel, Set<Object> refreshLabelSet)
	{
		LanguageTool.addString(key, defaultLabel);
		JButton button = new JButton(LanguageTool.getString(key));
		button.setName(key);
		refreshLabelSet.add(button);
		return button;
	}
	
	/**
	 * Cria JRadioButton configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 *  
	 * @param key	Chave para obter o conteúdo do rótulo
	 * @param defaultLabel Rótulo default
	 * @param refreshLabelSet Conjunto de objetos que podem sofrer atualização do rótulo
	 * @return
	 */
	public static JRadioButton createJRadioButton(String key, String defaultLabel, Set<Object> refreshLabelSet)
	{
		LanguageTool.addString(key, defaultLabel);
		JRadioButton radioButton = new JRadioButton(LanguageTool.getString(key));
		radioButton.setName(key);
		refreshLabelSet.add(radioButton);
		return radioButton;
	}
	
	/**
	 * Cria JMenu configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 *  
	 * @param key	Chave para obter o conteúdo do rótulo
	 * @param defaultLabel Rótulo default
	 * @param refreshLabelSet Conjunto de objetos que podem sofrer atualização do rótulo
	 * @return
	 */
	public static JMenu createJMenu(String key, String defaultLabel, Set<Object> refreshLabelSet)
	{
		LanguageTool.addString(key, defaultLabel);
		JMenu menu = new JMenu(LanguageTool.getString(key));
		menu.setName(key);
		refreshLabelSet.add(menu);
		return menu;
	}
	
	/**
	 * Cria JMenuItem configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 *  
	 * @param key	Chave para obter o conteúdo do rótulo
	 * @param defaultLabel Rótulo default
	 * @param refreshLabelSet Conjunto de objetos que podem sofrer atualização do rótulo
	 * @return
	 */
	public static JMenuItem createJMenuItem(String key, String defaultLabel, Set<Object> refreshLabelSet)
	{
		LanguageTool.addString(key, defaultLabel);
		JMenuItem menuItem = new JMenuItem(LanguageTool.getString(key));
		menuItem.setName(key);
		refreshLabelSet.add(menuItem);
		return menuItem;
	}

	/**
	 * Cria JCheckBox configurando LanguageTool e setando com o valor correspondente ao do idioma selecionado.
	 * @param key
	 * @param defaultLabel
	 * @param refreshLabelSet
	 * @return
	 */
	public static JCheckBox createJCheckBox(String key, String defaultLabel, Set<Object> refreshLabelSet) 
	{
		LanguageTool.addString(key, defaultLabel);
		JCheckBox chkBox = new JCheckBox(LanguageTool.getString(key));
		chkBox.setName(key);
		refreshLabelSet.add(chkBox);
		return chkBox;
	}
}

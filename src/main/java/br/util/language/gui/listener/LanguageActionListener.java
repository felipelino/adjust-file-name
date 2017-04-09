package br.util.language.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JMenuItem;

import br.util.language.LanguageTool;

/**
 * @author Felipe Lino
 *
 * Listener responsável por tratar os eventos de alteração de idioma do Menu
 */
public class LanguageActionListener implements ActionListener 
{
	/** Telas com possibilidade de mudar de idioma */
	private Set<ChangeLanguageWindow> windows = null;
	
	/**
	 * Construtor
	 * @param windows Lista de Janelas
	 */
	public LanguageActionListener(Set<ChangeLanguageWindow> windows)
	{
		this.windows = windows;
	}
	
	/**
	 * Construtor
	 * @param window Janela
	 */
	public LanguageActionListener(ChangeLanguageWindow window)
	{
		this.windows = new HashSet<ChangeLanguageWindow>();
		this.windows.add(window);
	}
	
	/**
	 * @param window Janela
	 */
	public void addWindow(ChangeLanguageWindow window)
	{
		this.windows.add(window);
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem languageOption = (JMenuItem) e.getSource();
		String fileName = languageOption.getName();
		LanguageTool.changeLanguage(fileName);
		for(ChangeLanguageWindow window : this.windows )
		{
			window.refreshLabels();
		}
	}

}

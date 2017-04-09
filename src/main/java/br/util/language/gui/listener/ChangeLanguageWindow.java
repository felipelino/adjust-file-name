package br.util.language.gui.listener;

public interface ChangeLanguageWindow
{
	/** Representa a chave para o idioma default */
	static final String LANGUAGE_DEFAULT 	= "language.default";
	
	/** Chaves para as opções de idioma */
	static final String LANGUAGE_OPTIONS	= "language.options";
	
	/** Altera os rotulos para o novo idioma selecionado */
	public void refreshLabels();
}

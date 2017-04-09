package br.adjustfn.gui.listener.menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.util.language.LanguageTool;

/**
 * @author Felipe Lino
 */
public class HelpContentActionListener implements ActionListener 
{
	/** Frame 'Pai' da Caixa de diálogo a ser exibida */
	private Component parentCompenent;
	
	/**
	 * Construtor
	 * @param parentCompenent
	 */
	public HelpContentActionListener(Component parentCompenent)
	{
		this.parentCompenent = parentCompenent; 
	}
	
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		String title = LanguageTool.getString("help.about.title");
		String pattern = LanguageTool.getString("help.about.message");
		String message = String.format(pattern, new Object[]{"Felipe G. de Oliveira Lino", "felipelino44@gmail.com", "1.0"});
		JOptionPane.showMessageDialog(this.parentCompenent, message, title, JOptionPane.INFORMATION_MESSAGE);
	}

}

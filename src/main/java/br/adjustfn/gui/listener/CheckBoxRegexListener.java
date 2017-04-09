package br.adjustfn.gui.listener;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JTable;
import javax.swing.JTextField;

import br.adjustfn.gui.table.FileTableModel;

public class CheckBoxRegexListener implements ItemListener {

	/** Text Field para a expressão regular */
	private JTextField fieldRegex;
	
	/** Tabela que apresenta os resultados */
	private FileTableModel fileTableModel;
	
	/** Tabela com o conteúdo */
	private JTable tableContent;
	
	/**
	 * Construtor
	 * @param fieldRegex
	 * @param fileTableModel
	 * @param tableContent 
	 */
	public CheckBoxRegexListener(JTextField fieldRegex, FileTableModel fileTableModel, JTable tableContent) 
	{
		this.fieldRegex = fieldRegex;
		this.fileTableModel = fileTableModel;
	}

	/**
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent evt) 
	{
		boolean isSelected = ItemEvent.SELECTED == evt.getStateChange();
		if(isSelected)
		{
			this.fieldRegex.setEditable(Boolean.TRUE);
			this.fieldRegex.setEnabled(Boolean.TRUE);
		}
		else
		{
			this.fieldRegex.setEditable(Boolean.FALSE);
			this.fieldRegex.setEnabled(Boolean.FALSE);
		}
		this.fileTableModel.refresh();
		this.tableContent.revalidate();
	}

}

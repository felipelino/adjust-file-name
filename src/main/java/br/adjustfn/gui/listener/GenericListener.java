package br.adjustfn.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventObject;

import javax.swing.JTable;

import br.adjustfn.gui.table.FileTableModel;

/**
 * Listener para o Field Path da URL
 * @author Felipe Lino
 */
public class GenericListener implements ActionListener , PropertyChangeListener, FocusListener
{
	/** Tabela exibindo os arquivos do diretório correntemente selecionado */
	private FileTableModel fileTableModel;
	
	/** Tabela com conteúdo */
	private JTable tableContent;
	
	/**
	 * Construtor
	 * @param fileTableModel
	 * @param tableContent 
	 */
	public GenericListener(FileTableModel fileTableModel, JTable tableContent) 
	{
		this.fileTableModel = fileTableModel;
		this.tableContent = tableContent;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		this.updateTableResults(evt);
	}

	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) 
	{
		this.updateTableResults(evt);
	}
	
	/**
	 * @see java.awt.event.FocusListener#focusGained(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusGained(FocusEvent evt) 
	{
		this.updateTableResults(evt);
	}

	/**
	 * @see java.awt.event.FocusListener#focusLost(java.awt.event.FocusEvent)
	 */
	@Override
	public void focusLost(FocusEvent evt) 
	{
		this.updateTableResults(evt);
	}

	
	/**
	 * @param evt
	 */
	private void updateTableResults(EventObject evt)
	{
		/* Lista os arquivos que estão no diretório */
		this.fileTableModel.refresh();
		this.tableContent.revalidate();
	}
}

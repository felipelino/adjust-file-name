package br.adjustfn.gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.table.TableModel;

/**
 * Listener para CheckBoxHeader para selecionar tudo
 * @author Felipe Lino
 *
 */
public class CheckBoxAllListener implements ItemListener 
{
	/** Modelo de tabela */
	private TableModel tableModel;
	
	/** Coluna que informa a coluna que representa o boolean para selecinar */
	private int columnSelectIdx;
	
	/**
	 * @param tableModel
	 * @param columnIndex índice da coluna que representa o boolean para selecionar
	 */
	public CheckBoxAllListener(TableModel tableModel, int columnSelectIdx)
	{
		this.tableModel = tableModel;
		this.columnSelectIdx = columnSelectIdx;
	}
	
	/**
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent evt) 
	{
		boolean isSelected = ItemEvent.SELECTED == evt.getStateChange();
		changeTableValue(isSelected);
	}
	
	/**
	 * @param value
	 */
	private void changeTableValue(boolean value)
	{
		for(int rowIndex = 0; rowIndex < this.tableModel.getRowCount(); rowIndex++)
		{
			this.tableModel.setValueAt(value, rowIndex, this.columnSelectIdx);
		}
	}

}

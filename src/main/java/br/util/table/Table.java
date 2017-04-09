package br.util.table;

import java.util.ArrayList;

/**
 * Representa uma tabela
 * @author Felipe Lino
 */
public class Table extends ArrayList<Row>
{
	/** default seriar Version UID */
	private static final long serialVersionUID = 1L;

	/** Quantidade de colunas */
	private int columnSize;
	
	/**
	 * Construtor
	 * @param columnSize
	 */
	public Table(int columnSize)
	{
		super();
		this.columnSize = columnSize;
	}
	
	/**
	 * Retorna linha da tabela
	 * @param rowIndex
	 * @return
	 */
	public Row getRow(int rowIndex)
	{
		return this.get(rowIndex);
	}
	
	/**
	 * Retorna elemento da tabela
	 * @param rowIndex
	 * @param columnIndex
	 * @return
	 */
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		Row row = this.getRow(rowIndex);
		Object value = row.getColumn(columnIndex);
		return value;
	}
	
	/**
	 * Adiciona linha à tabela
	 * @param row
	 * @throws AddRowException 
	 */
	public void add(Object[] objArr) throws AddRowException
	{
		Row row = new Row(this.getColumnSize(), objArr);
		super.add(row);
	}
	
	/**
	 * Seta o valor em determinada posição da tabela
	 * @param value
	 * @param rowIndex
	 * @param columnIndex
	 */
	public void set(Object value, int rowIndex, int columnIndex)
	{
		Row row = this.getRow(rowIndex);
		row.set(columnIndex, value);
	}
	
	/**
	 * Remove linha da tabela
	 * @param rowIndex
	 */
	public void removeRow(int rowIndex)
	{
		this.remove(rowIndex);
	}
	
	/**
	 * Remove todas as linhas da tabela
	 */
	public void removeAll()
	{
		super.removeRange(0, this.size());
	}

	/**
	 * Quantidade de colunas da tabela
	 * @return
	 */
	public int getColumnSize() {
		return this.columnSize;
	}
	
	/**
	 * Retorna os dados como Array
	 * @return
	 */
	public Object[][] getData()
	{
		/* Varre as linhas */
		Object[][] data = new Object[this.size()][this.getColumnSize()];
		for(int rowIndex = 0; rowIndex < this.size(); rowIndex++)
		{
			Row row = this.getRow(rowIndex);
			for(int columnIndex = 0; columnIndex < this.getColumnSize(); columnIndex++)
			{
				data[rowIndex][columnIndex] = row.getColumn(columnIndex);
			}
		}
		return data;
	}
}

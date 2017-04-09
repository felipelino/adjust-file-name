package br.util.table;

import java.util.ArrayList;

public class Row extends ArrayList<Object> 
{
	/** Default serial Version UID */
	private static final long serialVersionUID = 1L;
	
	/** Quantidade de colunas da linha */ 
	private int columnSize;
	
	/**
	 * @param columnSize
	 * @param row
	 * @throws AddRowException
	 */
	protected Row(int columnSize, Object[] objArr) throws AddRowException
	{
		super(columnSize);
		this.columnSize = columnSize;
		if(objArr.length > columnSize)
		{
			throw new AddRowException("Row exceed columnSize");
		}
		
		for(Object obj : objArr)
		{
			this.add(obj);
		}
	}
	
	/**
	 * @param columnIndex
	 * @return
	 */
	public Object getColumn(int columnIndex)
	{
		if(columnIndex > this.columnSize){
			throw new ArrayIndexOutOfBoundsException(columnIndex);
		}
		return this.get(columnIndex);
	}

	/**
	 * @see java.util.AbstractCollection#toString()
	 */
	@Override
	public String toString()
	{
		return "Row:["+super.toString()+"]";
	}
}

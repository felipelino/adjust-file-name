package br.adjustfn.gui.table;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.log4j.Logger;

import br.adjustfn.engine.AdjustFileName;
import br.util.FileNameComparator;
import br.util.FileUtil;
import br.util.table.AddRowException;
import br.util.table.Table;

/**
 * Modelo da tabela com o conteúdo
 */
public class FileTableModel extends AbstractTableModel
{
	public static final String MAIN_WINDOW_TABLE_FILE_NAME_KEY = "main.window.table.fileName";

	public static final String COLUMN_NAME_FILE_NAME = "File Name";

	public static final String COLUMN_NAME_SELECT = "Select";
	
	/** Logger da classe */
	private static final Logger log = Logger.getLogger(FileTableModel.class);
	
	/** Default SerialVersionUID */
	private static final long serialVersionUID = 1L;

	/** Lista de Lista representando uma tabela semelhante à table[][] 
	 * A primeira lista é a linha e a segunda as colunas daquela linha */
	private Table table;
	
	/** CheckBox informando se deve ou não aceitar expressões regulares */
	private JCheckBox chkBoxRegex;
	
	/** TextField com a expressão regular */
	private JTextField fieldRegex;
	
	/** TextField com o path para o diretório */
	private JTextField fieldPath;
	
	/** Extensão de arquivo */
	private JComboBox cbExtension;
	
	/**
	 * Construtor
	 * @param cbExtension 
	 * @param dir
	 */
	public FileTableModel(JCheckBox chkRegex, JTextField fieldRegex, JTextField fielPath, JComboBox cbExtension)
	{
		super();
		this.table = new Table(2);
		this.chkBoxRegex = chkRegex;
		this.fieldRegex = fieldRegex;
		this.fieldPath = fielPath;
		this.cbExtension = cbExtension;
		this.refresh();
	}
	
	/**
	 * Atualiza a tabela de resultados com os arquivos
	 * @throws AddRowException 
	 */
	public void refresh()
	{
		int rowCount = this.getRowCount();
		try
		{
			File dir = FileUtil.getDirectory(this.fieldPath.getText());
			/* Caso tenha sido colocada uma URL de arquivo ao invés de diretório 
			 * corrige para selecionar o diretório onde se encontra o arquivo */
			this.fieldPath.setText(dir.getAbsolutePath());
			
			this.table.removeAll();
			AdjustFileName adjfn = null;
			
			String extension = ObjectUtils.toString(cbExtension.getSelectedItem());
			if("all".equalsIgnoreCase(extension))
			{
				extension = null;
			}
			String regex = null;
			if(chkBoxRegex.isSelected())
			{
				regex = this.fieldRegex.getText();
			}
			adjfn = new AdjustFileName(extension, regex);
			
			Collection<File> fileSet = adjfn.getFilterFiles(dir);
			log.info("FileSet:"+fileSet);
			List<File> fileList = new ArrayList<File>(fileSet);
			Collections.sort(fileList, new FileNameComparator());
			for(File file : fileList)
			{
				if(file.isFile())
				{
					FileColumnData data = new FileColumnData(file);
					this.table.add(new Object[]{Boolean.FALSE, data});
				}
			}
		}
		catch(AddRowException exc)
		{
			log.error("Failed when tried to update table results", exc);
		}
		if(this.getRowCount() > rowCount)
		{
			rowCount = this.getRowCount();
		}
		this.fireTableRowsUpdated(0, rowCount);
	}
	
	/**
	 * Retorna os arquivos selecionados
	 * @return
	 */
	public Collection<File> getSelectedFiles()
	{
		Collection<File> files = new ArrayList<File>();
		for(int rowIndex = 0; rowIndex < this.getRowCount(); rowIndex++)
		{
			Boolean isSelected 	= (Boolean) getValueAt(rowIndex, 0);
			if(isSelected)
			{
				FileColumnData data = (FileColumnData) getValueAt(rowIndex, 1);
				File file = data.getFile();
				files.add(file);
			}
		}
		return files;
	}

	/**
	 * @see javax.swing.table.TableModel#getColumnCount()
	 */
	@Override
	public int getColumnCount() {
		return table.getColumnSize();
	}

	/**
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return this.table.size();
	}

	/**
	 * @see javax.swing.table.TableModel#getValueAt(int, int)
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) 
	{
		//return table.getData()[rowIndex][columnIndex];
		return table.getRow(rowIndex).getColumn(columnIndex);
	}
	
	/**
	 * @param value
	 * @param rowIndex
	 * @param columnIndex
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) 
	{
		table.set(value, rowIndex, columnIndex);
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Class getColumnClass(int columnIndex) 
	{
		Object value = getValueAt(0, columnIndex);
		if(value != null)
		{
			return value.getClass();
		}
		else
		{
			return Object.class;
		}
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int column) 
	{
		String[] columnNames = {COLUMN_NAME_SELECT, COLUMN_NAME_FILE_NAME};
		return columnNames[column];
	}

	/**
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int row, int col) 
	{
		return (col == 0);
	}
	
}

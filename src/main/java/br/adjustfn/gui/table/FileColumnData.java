package br.adjustfn.gui.table;

import java.io.File;

/**
 * @author Felipe Lino
 *
 */
public class FileColumnData 
{
	/** Arquivo a ser armazenado na tabela */
	private File file;

	/**
	 * Construtor
	 * @param file
	 */
	public FileColumnData(File file) {
		super();
		this.file = file;
	}

	/**
	 * Retorno o arquivo
	 * @return
	 */
	public File getFile() {
		return file;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() 
	{
		return this.getFile().getName();
	}

}

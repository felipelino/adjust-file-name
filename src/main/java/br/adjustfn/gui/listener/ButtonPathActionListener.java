package br.adjustfn.gui.listener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.JTextField;

import br.adjustfn.gui.table.FileTableModel;
import br.util.FileUtil;

/**
 * Listener para tratar os eventos do botão de abrir Path
 * @author Felipe Lino
 */
public class ButtonPathActionListener implements ActionListener 
{
	/** Parent component */
	private Component parent;
	
	/** TextField que exibe o Path */
	private JTextField pathField;
	
	/** Modelo da tabela de resultados */
	private FileTableModel fileTableModel;
	
	/** Tabela com o conteúdo */
	private JTable tableContent;
	
	/**
	 * Construtor
	 * @param parent
	 * @param pathField
	 * @param fileTableModel
	 */
	public ButtonPathActionListener(Component parent, 
									JTextField pathField,
									FileTableModel fileTableModel, 
									JTable tableContent) 
	{
		super();
		this.parent = parent;
		this.pathField = pathField;
		this.fileTableModel = fileTableModel;
		this.tableContent = tableContent;
	}
	
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        int returnVal = fileChooser.showOpenDialog(this.parent);
        if (returnVal == JFileChooser.APPROVE_OPTION);
        {
            File file = fileChooser.getSelectedFile();
            if( file != null)
            {
	            File dir = FileUtil.getDirectory(file);
	            this.pathField.setText(dir.getAbsolutePath());
	            this.fileTableModel.refresh();
	            this.tableContent.revalidate();
            }
        } 
	}
}

package br.adjustfn.gui.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Collection;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import br.adjustfn.engine.AdjustFileName;
import br.adjustfn.gui.table.FileTableModel;
import br.util.language.LanguageTool;
import br.util.table.CheckBoxHeader;

/**
 * Executa a ação do botão de submit
 * @author Felipe Lino
 */
public class ButtonSubmitActionListener implements ActionListener 
{
	/** Representa a tabela com resultados */
	private FileTableModel fileTableModel;

	/** Text Field para prefixo */
	private JTextField fieldPrefix;
	
	/** Spinner numérico */
	private JSpinner spinnerNum;
	
	/** Check Box para expressao regular */
	private JCheckBox chkRegex;
	
	/** Check Box Header da tabela */
	private CheckBoxHeader chkHeader;
	
	/**
	 * Construtor
	 * @param fileTableModel
	 * @param spinnerNum 
	 * @param fieldPrefix 
	 * @param chkRegex
	 * @param chkHeader
	 */
	public ButtonSubmitActionListener(FileTableModel fileTableModel, 
									 JTextField fieldPrefix, 
									 JSpinner spinnerNum, 
									 JCheckBox chkRegex, 
									 CheckBoxHeader chkHeader) 
	{
		this.fileTableModel = fileTableModel;
		this.fieldPrefix = fieldPrefix;
		this.spinnerNum = spinnerNum;
		this.chkRegex = chkRegex;
		this.chkHeader = chkHeader;
	}

	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		Collection<File> files = this.fileTableModel.getSelectedFiles();
		
		String baseName = StringUtils.trim(this.fieldPrefix.getText());
		String baseCountStr = ObjectUtils.toString(this.spinnerNum.getValue());
		Integer baseCount = Integer.valueOf(baseCountStr);
		
		int totalFiles = files.size();
		
		if(totalFiles == 0)
		{
			String title = LanguageTool.getString("submit.dialog.title", "Confirm update");
			String message = LanguageTool.getString("submit.noaction.message", "Zero file selected. Nothing to do.");
			JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
		}
		else
		{
			String defaultMsg = "%d files will be renamed using the prefix '%s' with counter starting in %d.\n" +
			"Are you sure that you want to follow with changes?";

			String format = LanguageTool.getString("submit.confirm.message", defaultMsg);
			String message = String.format(format, new Object[]{totalFiles, baseName, baseCount});

			Object[] options = {LanguageTool.getString("submit.dialog.yes", "Yes"), 
								LanguageTool.getString("submit.dialog.no", "No")};
			String title = LanguageTool.getString("submit.dialog.title", "Confirm update");
			int answer = JOptionPane.showOptionDialog(null,
			    message,
			    title,
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[0]);
			
			if(JOptionPane.YES_OPTION == answer)
			{
				AdjustFileName adjfn = new AdjustFileName(baseName, baseCount.intValue());
				adjfn.renameFiles(files);
				
				this.chkRegex.setSelected(Boolean.FALSE);
				
				this.chkHeader.setSelected(Boolean.FALSE);
				this.fileTableModel.refresh();
				
				String finalMsg = LanguageTool.getString("submit.final.message", "Update done with success.");
				JOptionPane.showMessageDialog(null, finalMsg, title, JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}

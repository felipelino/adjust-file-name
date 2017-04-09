package br.adjustfn.gui;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableColumn;

import org.apache.commons.lang3.StringUtils;

import br.adjustfn.config.ConfigKey;
import br.adjustfn.gui.listener.ButtonPathActionListener;
import br.adjustfn.gui.listener.ButtonSubmitActionListener;
import br.adjustfn.gui.listener.CheckBoxRegexListener;
import br.adjustfn.gui.listener.GenericListener;
import br.adjustfn.gui.listener.menu.HelpContentActionListener;
import br.adjustfn.gui.table.FileTableModel;
import br.util.FileUtil;
import br.util.WindowUtil;
import br.util.language.LanguageTool;
import br.util.language.gui.listener.AbstractChangeLanguageWindow;
import br.util.language.gui.listener.ChangeLanguageWindow;
import br.util.table.CheckBoxHeader;

import com.jgoodies.forms.builder.DefaultFormBuilder;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

/**
 * @author Felipe Lino
 */
public class AdjustFileNameWindow extends AbstractChangeLanguageWindow
{
	/** Default Serial Version UID */
	private static final long serialVersionUID = 1L;

	/* Menu */
	/** Barra de de Menu */
	private JMenuBar menuBar;
	
	/** Menu de Opções */
	private JMenu menuConfig;
	
	/** Opção de Idioma */
	private JMenu mCfgLanguage;
	
	/** Menu de Ajuda */
	private JMenu menuHelp;
	
	/** Menu item de Sobre */
	private JMenuItem mHelpAbout;
	
	/* Tela */
	/** Exibe o Path para a pasta onde os arquivos serão renomeados */
	private JTextField fieldPath;
	
	/** Botão para abrir pasta */
	private JButton btPath;
	
	/** Scroll para a tabela que exibe conteúdo das pastas */
	private JScrollPane scrollContent;
	
	/** Tabela para exibir o conteúdo das pastas */
	private JTable tableContent;
	
	/** Modelo da tabela para exibir os conteudos das pastas */
	private FileTableModel fileTableModel;
	
	/** Check Box header da tabela */
	private CheckBoxHeader chkHeader; 

	/** Rótulo para Expressão Regular */
	private JLabel lbRegex;
	
	/** Check Box para Expressão Regular */
	private JCheckBox chkRegex;
	
	/** Informa a expressão regular */
	private JTextField fieldRegex;
	
	/** ComboBox para extensão de arquivo */
	private JComboBox cbExtension;
	
	/** Rótulo do Prefixo */
	private JLabel lbPrefix;
	
	/** Informar o novo prefixo */
	private JTextField fieldPrefix;
	
	/** Rótulo para inicio da numeração */
	private JLabel lbSpinnerNum;
	
	/** Spinner para numeração inicial */
	private JSpinner spinnerNum;
	
	/** Botão para aplicar as alterações */
	private JButton btSubmit;
	
	
	/* Constantes */
	static final String MAIN_WINDOW_TITLE_KEY 	= "main.window.title";
	static final String MAIN_WINDOW_TITLE_VALUE = "Adjust File Name";
	static final String MAIN_WINDOW_MENU_LAGUAGE= "main.window.menu.language";
	static final String MAIN_WINDOW_MENU_CFG 	= "main.window.menu.cfg";
	static final String MAIN_WINDOW_BT_FOLDER	= "main.window.bt.folder";
	static final String MAIN_WINDOW_LB_REGEX 	= "main.window.lb.regex";
	static final String MAIN_WINDOW_LB_PREFIX	= "main.window.lb.prefix";
	static final String MAIN_WINDOW_LB_START	= "main.window.lb.start";
	static final String MAIN_WINDOW_BT_SUBMIT	= "main.window.bt.submit";
	static final String MAIN_WINDOW_MENU_HELP			= "main.window.menu.help";
	static final String MAIN_WINDOW_MENU_HELP_ABOUT 	= "main.window.menu.help.about";
	static final String MAIN_WINDOW_MENU_HELP_CONTENT 	= "main.window.menu.help.content";
	
	/**
	 * Construtor
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public AdjustFileNameWindow() throws FileNotFoundException, IOException 
	{
		super(ConfigKey.CFG_FILE_NAME);
		setSize(500,640);
		setLocation(0,0);
		setResizable(false);
		String fileName = FileUtil.findFile("img/camera_icon.png").getAbsolutePath();
		Image icon = Toolkit.getDefaultToolkit().getImage(fileName);
		this.setIconImage(icon);
		
		LanguageTool.addString(MAIN_WINDOW_TITLE_KEY, MAIN_WINDOW_TITLE_VALUE);
		this.setTitle(LanguageTool.getString(MAIN_WINDOW_TITLE_KEY));
		
		instantiateComponents();
		
		buildPane();
		buildMenu();
		refreshLabels();
	}
	
	/**
	 * Monta a tabela a ser exibida com os arquivos passíveis de serem alterados
	 */
	private void buildTable()
	{
		this.fileTableModel = new FileTableModel(this.chkRegex, this.fieldRegex, this.fieldPath, this.cbExtension);
		
		this.tableContent = new JTable(this.fileTableModel);
		
		this.tableContent.getColumnModel().getColumn(0).setIdentifier(FileTableModel.COLUMN_NAME_SELECT);
		this.tableContent.getColumnModel().getColumn(1).setIdentifier(FileTableModel.COLUMN_NAME_FILE_NAME);
		
		TableColumn tbColumn = this.tableContent.getColumn(FileTableModel.COLUMN_NAME_SELECT);
		CheckBoxAllListener chkHeaderListener = new CheckBoxAllListener(this.fileTableModel, 0);
		chkHeader = new CheckBoxHeader(chkHeaderListener);
		
		tbColumn.setHeaderRenderer(chkHeader);
		tbColumn.setMaxWidth(5);
		tbColumn.setPreferredWidth(5);
		
		this.tableContent.setAutoscrolls(true);
		this.scrollContent = new JScrollPane(this.tableContent);
		this.scrollContent.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.scrollContent.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	}
	
	/**
	 * Instância os componentes da tela
	 */
	private void instantiateComponents()
	{
		this.fieldPath = new JTextField(25);
		this.fieldPath.setText(FileUtil.getUserHomeDirectory());
		
		this.btPath = super.createButton(MAIN_WINDOW_BT_FOLDER, "Open");
		Icon btPathIcon = WindowUtil.createImageIcon("img/folder.png",null);
		if(btPathIcon != null)
		{
			this.btPath.setIcon(btPathIcon);
			this.btPath.setText("");
		}
		
		this.lbRegex 	= super.createLabel(MAIN_WINDOW_LB_REGEX, "Regex:");
		this.fieldRegex = new JTextField();
		this.fieldRegex.setText("[.]*.jpg");
		this.fieldRegex.setEnabled(Boolean.FALSE);
		this.chkRegex	= new JCheckBox();
		
		String extensions = "all;" + this.properties.getProperty("adjustfn.extensions", "");
		String[] extArr = extensions.split(";"); 
		this.cbExtension = new JComboBox(extArr);
		
		this.lbPrefix 	= super.createLabel(MAIN_WINDOW_LB_PREFIX, "Prefix:");
		this.fieldPrefix= new JTextField();
		this.fieldPrefix.setText("prefix_");
		
		this.lbSpinnerNum = super.createLabel(MAIN_WINDOW_LB_START, "Start in:");
		
		this.spinnerNum	= new JSpinner(new SpinnerNumberModel(1, 0, Integer.MAX_VALUE, 1));
		this.btSubmit	= super.createButton(MAIN_WINDOW_BT_SUBMIT, "OK");
		Icon btSubmitIcon= WindowUtil.createImageIcon("img/submit.gif", null);
		if(btSubmitIcon != null)
		{
			this.btSubmit.setIcon(btSubmitIcon);
			this.btSubmit.setText(null);
		}
		
		this.buildTable();
		GenericListener genericListener = new GenericListener(this.fileTableModel, this.tableContent);
		this.fieldPath.addActionListener(genericListener);
		this.fieldPath.addPropertyChangeListener(genericListener);
		this.fieldPath.addFocusListener(genericListener);
		
		this.btPath.addActionListener(new ButtonPathActionListener(this, this.fieldPath, this.fileTableModel, this.tableContent));
		this.chkRegex.addItemListener(new CheckBoxRegexListener(this.fieldRegex, this.fileTableModel, this.tableContent));
		this.cbExtension.addActionListener(genericListener);
		this.fieldRegex.addActionListener(genericListener);
		this.fieldRegex.addPropertyChangeListener(genericListener);
		this.fieldRegex.addFocusListener(genericListener);
		ButtonSubmitActionListener btSubmitActionListener = new ButtonSubmitActionListener(this.fileTableModel, 
																							this.fieldPrefix, 
																							this.spinnerNum, 
																							this.chkRegex, 
																							this.chkHeader);
		this.btSubmit.addActionListener(btSubmitActionListener);
		
	}
	
	/**
	 * Monta a Linha que apresenta os dados de Path
	 * @return
	 */
	private JPanel buildPathPanel()
	{
		FormLayout layout1 = new FormLayout(
				//Colunas
				"pref:grow," +
				"3dlu," +
				"min," +
				"",
				//Linhas
				"pref" +
				"");
		//DefaultFormBuilder builder1 = new DefaultFormBuilder(layout1, new FormDebugPanel());
		DefaultFormBuilder builder1 = new DefaultFormBuilder(layout1);
		CellConstraints cc = new CellConstraints();
		builder1.add(this.fieldPath, 	cc.xyw(1,1,1));
		builder1.add(this.btPath, 		cc.xyw(3,1,1));
		return builder1.getPanel();
	}
	
	/**
	 * Monta a Linha que apresenta os resultados com as pastas 
	 * @return
	 */
	private JPanel buildPathResultPanel()
	{
		FormLayout layout2 = new FormLayout(
				//Colunas
				"pref:grow," +
				"",
				//Linhas
				"pref:grow," + 	
				"");			
		
		DefaultFormBuilder builder2 = new DefaultFormBuilder(layout2);
		CellConstraints cc2 = new CellConstraints();
		builder2.add(this.scrollContent, cc2.xy(1, 1));
		return builder2.getPanel();
	}
	
	/**
	 * Monta a Linha de Expressão Regular
	 * @return
	 */
	private JPanel buildRegexPanel()
	{
		FormLayout layout3 = new FormLayout(
				//Colunas
				"min," +
				"3dlu," +
				"pref," +
				"3dlu," +
				"pref:grow," +
				"3dlu," +
				"min," +
				"",
				//Linhas
				"pref," + 	
				"");			
		
		DefaultFormBuilder builder3 = new DefaultFormBuilder(layout3);
		CellConstraints cc3 = new CellConstraints();
		builder3.add(this.chkRegex,  cc3.xy(1, 1));
		builder3.add(this.lbRegex, 	 cc3.xy(3, 1));
		builder3.add(this.fieldRegex,cc3.xy(5, 1));
		builder3.add(this.cbExtension,cc3.xy(7, 1));
		return builder3.getPanel();
	}
	
	/**
	 * Monta a linha de Prefixo
	 * @return
	 */
	private JPanel buildPrefixPanel()
	{
		FormLayout layout4 = new FormLayout(
				//Colunas
				"min," +
				"3dlu," +
				"pref:grow," +
				"3dlu," +
				"min," +
				"3dlu," +
				"min," +
				"",
				//Linhas
				"pref," + 	
				"");			
		
		DefaultFormBuilder builder4 = new DefaultFormBuilder(layout4);
		CellConstraints cc4 = new CellConstraints();
		builder4.add(this.lbPrefix,  	cc4.xy(1, 1));
		builder4.add(this.fieldPrefix, 	cc4.xy(3, 1));
		builder4.add(this.lbSpinnerNum, cc4.xy(5, 1));
		builder4.add(this.spinnerNum, 	cc4.xy(7, 1));
		return builder4.getPanel();
	}
	
	/**
	 * Monta a linha de Submit
	 * @return
	 */
	private JPanel buildSubmitPanel()
	{
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(this.btSubmit);
		return panel;
	}
	
	/**
	 * Constrói a tela
	 */
	private void buildPane()
	{
		JPanel pathPanel 		= buildPathPanel(); 
		JPanel pathResultPanel 	= buildPathResultPanel();
		JPanel regexPanel 		= buildRegexPanel();
		JPanel prefixPanel		= buildPrefixPanel();
		JPanel submitPanel		= buildSubmitPanel();
		
		FormLayout layoutFinal = new FormLayout(
				//Colunas
				"pref," +
				"",
				//Linhas
				"pref," +
				"3dlu," +
				"pref," +
				"3dlu," +
				"pref," +
				"3dlu," +
				"pref," +
				"3dlu," +
				"pref," +
				"");
		//DefaultFormBuilder builderFinal = new DefaultFormBuilder(layoutFinal, new FormDebugPanel());
		DefaultFormBuilder builderFinal = new DefaultFormBuilder(layoutFinal);
		CellConstraints cc = new CellConstraints();
		builderFinal.add(pathPanel, 		cc.xy(1, 1));
		builderFinal.add(pathResultPanel, 	cc.xy(1, 3));
		builderFinal.add(regexPanel, 		cc.xy(1, 5));
		builderFinal.add(prefixPanel, 		cc.xy(1, 7));
		
		/* Coluna , Linha, Celulas */
		this.setLayout(new FlowLayout());
		this.add(builderFinal.getPanel());
		this.add(submitPanel);
		
	}
	
	/**
	 * Constrói os Menus
	 */
	private void buildMenu()
	{
		/* Inclusao dos Menus */
		this.mCfgLanguage = super.createJMenu(MAIN_WINDOW_MENU_LAGUAGE, "Language"); 
		String propertyOpts = properties.getProperty(ChangeLanguageWindow.LANGUAGE_OPTIONS);
		String[] languageOptions = StringUtils.split(propertyOpts, ';');
		
		for(String language : languageOptions)
		{
			JMenuItem m2Opt = new JMenuItem(language);
			m2Opt.addActionListener(languageListener);
			m2Opt.setName(language);
			this.mCfgLanguage.add(m2Opt);
		}
		
		this.menuConfig = super.createJMenu(MAIN_WINDOW_MENU_CFG, "Configuration");
		this.menuConfig.add(this.mCfgLanguage);
		
		this.mHelpAbout 	= super.createJMenuItem(MAIN_WINDOW_MENU_HELP_ABOUT, 	"About");
		this.mHelpAbout.addActionListener(new HelpContentActionListener(this));
		this.menuHelp	 	= super.createJMenu(MAIN_WINDOW_MENU_HELP, "Help");
		this.menuHelp.add(this.mHelpAbout);
		
		this.menuBar = new JMenuBar();
		this.menuBar.add(menuConfig);
		this.menuBar.add(menuHelp);
		this.setJMenuBar(menuBar);
	}

	/**
	 * @see br.util.language.gui.listener.AbstractChangeLanguageWindow#refreshLabels()
	 */
	@Override
	public void refreshLabels() 
	{
		TableColumn tbColumn = this.tableContent.getColumn(FileTableModel.COLUMN_NAME_FILE_NAME);
		tbColumn.setHeaderValue(LanguageTool.getString(FileTableModel.MAIN_WINDOW_TABLE_FILE_NAME_KEY, FileTableModel.COLUMN_NAME_FILE_NAME));
		super.refreshLabels();
	}
}

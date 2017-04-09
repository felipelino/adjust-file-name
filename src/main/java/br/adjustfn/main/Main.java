package br.adjustfn.main;

import br.adjustfn.gui.AdjustFileNameWindow;

public class Main 
{
	public static void main(String[] args) throws Exception
	{
		AdjustFileNameWindow jMain;
		AdjustFileNameWindow.setDefaultLookAndFeelDecorated(true);
		jMain = new AdjustFileNameWindow();
		
		jMain.setDefaultCloseOperation(AdjustFileNameWindow.EXIT_ON_CLOSE);
		jMain.setFocusable(true);
		jMain.setVisible(true);
	}

}

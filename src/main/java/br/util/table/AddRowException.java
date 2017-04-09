package br.util.table;

/**
 * Exceção que representa falha ao adicionar uma linha
 * @author Felipe Lino
 */
public class AddRowException extends Exception 
{
	/** Default Serial Version UID  */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Construtor
	 * @param message
	 */
	public AddRowException(String message) 
	{
		super(message);
	}	
}

package Exceptions;

public class EmptyFieldException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public EmptyFieldException() {
		super("You must fill all the fields!");

	}

}

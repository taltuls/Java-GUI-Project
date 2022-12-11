package Exceptions;

public class ShortPasswordException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ShortPasswordException() {
		super("The password is too short!");

	}
}

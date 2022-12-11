package Exceptions;
public class SameSectionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public SameSectionException() {
		super("The chosen section is the same as the old one!");

	}
}

package Exceptions;

public class UserLoginException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public UserLoginException() {

		super("Oops...Incorrect user");
	}
}

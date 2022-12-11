package Exceptions;

public class IncompatibleDiscount extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public IncompatibleDiscount() {
		super("The discount does not match the ticket type.");
	}
	

}

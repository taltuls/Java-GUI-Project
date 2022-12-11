package Exceptions;

public class DuplicatedValues extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DuplicatedValues() {
		super("This person is already a zoo employee");
	}
	

}

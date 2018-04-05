package by.asrohau.iShop.controller.exception;

public class ControllerException extends Exception {
	
	/**
	 *  what is serial version UID???? TODO find out!!
	 */
	private static final long serialVersionUID = 1L;

	public ControllerException() {
		super();
	}
	
	public ControllerException(String message) {
		super(message);
	}
	
	public ControllerException(Exception e) {
		super(e);
	}
	
	public ControllerException(String message, Exception e) {
		super(message, e);
	}
	
}

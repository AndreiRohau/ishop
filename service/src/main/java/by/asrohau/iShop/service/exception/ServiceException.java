package by.asrohau.iShop.service.exception;

public class ServiceException extends Exception{

	/**
	 * finally find out what is this UID
	 */
	private static final long serialVersionUID = 6765533323601079474L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}
	
	

}

package by.asrohau.iShop.service.exception;

import org.apache.log4j.Logger;

public class ServiceException extends Exception{

	final static Logger logger = Logger.getLogger(ServiceException.class);

	private static final long serialVersionUID = 6765533323601079474L;

	public ServiceException() {
		super();
		logger.error("(*_*)=ERROR_OCCUPIED=(*_*)");
	}

	public ServiceException(String message, Exception e) {
		super(message, e);
		logger.error(message);
		logger.error(e);
	}

	public ServiceException(String message) {
		super(message);
		logger.error(message);
	}

	public ServiceException(Exception e) {
		super(e);
		logger.error(e);
	}
	
	

}

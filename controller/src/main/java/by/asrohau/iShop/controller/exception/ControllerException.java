package by.asrohau.iShop.controller.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ControllerException extends Exception {
	private final static Logger logger = LoggerFactory.getLogger(ControllerException.class);

	private static final long serialVersionUID = 1L;

	public ControllerException() {
		super();
		logger.error("(*_*)=ERROR_OCCUPIED=(*_*)");
	}
	
	public ControllerException(String message) {
		super(message);
		logger.error(message);
	}
	
	public ControllerException(Exception e) {
		super(e);
		logger.error(e.toString());
	}
	
	public ControllerException(String message, Exception e) {
		super(message, e);
		logger.error(message, e);

	}
	
}

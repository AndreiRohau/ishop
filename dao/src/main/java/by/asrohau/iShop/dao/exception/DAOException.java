package by.asrohau.iShop.dao.exception;

public class DAOException extends Exception{
	
	/**
	 * незнаю что за прайвэт файнал лонг
	 */
	private static final long serialVersionUID = 1L;

	public DAOException() {
		super();
	}
	
	public DAOException(String message) {
		super(message);
	}
	
	public DAOException(Exception e) {
		super(e);
	}
	
	public DAOException(String message, Exception e) {
		super(message, e);
	}
}

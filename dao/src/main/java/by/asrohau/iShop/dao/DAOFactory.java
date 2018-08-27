package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.impl.OrderDAOImpl;
import by.asrohau.iShop.dao.impl.ProductDAOImpl;
import by.asrohau.iShop.dao.impl.ReserveDAOImpl;
import by.asrohau.iShop.dao.impl.UserDAOImpl;
import by.asrohau.iShop.dao.databaseConnectionImpl.ConnectionPoolImpl;
import by.asrohau.iShop.dao.databaseConnectionImpl.DatabaseConfigReaderImpl;

public class DAOFactory {

	private static final DatabaseConfigReader databaseConfigReader = new DatabaseConfigReaderImpl();
	private static final ConnectionPool connectionPool = new ConnectionPoolImpl();
	private static final DAOFactory INSTANCE = new DAOFactory();
	private final UserDAO userDAO = new UserDAOImpl(connectionPool);
	private final ProductDAO productDAO = new ProductDAOImpl(connectionPool);
	private final ReserveDAO reserveDAO = new ReserveDAOImpl(connectionPool);
	private final OrderDAO orderDAO = new OrderDAOImpl(connectionPool);

	private DAOFactory() {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public ReserveDAO getReserveDAO() {
		return reserveDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public static DatabaseConfigReader getDatabaseConfigReader() {
		return databaseConfigReader;
	}

	public static DAOFactory getInstance(){
		return INSTANCE;
	}

}

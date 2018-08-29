package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.impl.OrderDAOImpl;
import by.asrohau.iShop.dao.impl.ProductDAOImpl;
import by.asrohau.iShop.dao.impl.ReserveDAOImpl;
import by.asrohau.iShop.dao.impl.UserDAOImpl;
import by.asrohau.iShop.dao.databaseConnectionImpl.ConnectionPoolImpl;
import by.asrohau.iShop.dao.databaseConnectionImpl.DatabaseConfigReaderImpl;

public class DAOFactory {

	private static final DatabaseConfigReader databaseConfigReader = new DatabaseConfigReaderImpl();
	private static final ConnectionPool connectionPool = new ConnectionPoolImpl(databaseConfigReader);

	private static final DAOFactory INSTANCE = new DAOFactory();
	private final UserDAO userDAO;
	private final ProductDAO productDAO;
	private final ReserveDAO reserveDAO;
	private final OrderDAO orderDAO;

	private DAOFactory() {
		this.userDAO = new UserDAOImpl(connectionPool);
		this.productDAO = new ProductDAOImpl(connectionPool);
		this.reserveDAO = new ReserveDAOImpl(connectionPool);
		this.orderDAO = new OrderDAOImpl(connectionPool);
	}
	
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

	public static DAOFactory getInstance(){
		return INSTANCE;
	}

}

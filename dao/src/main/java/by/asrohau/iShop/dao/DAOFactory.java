package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.impl.AdminDAOImpl;
import by.asrohau.iShop.dao.impl.OrderDAOImpl;
import by.asrohau.iShop.dao.impl.ProductDAOImpl;
import by.asrohau.iShop.dao.impl.UserDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory INSTANCE = new DAOFactory();
	
	private final UserDAO userDAO = new UserDAOImpl();
	private final AdminDAO adminDAO = new AdminDAOImpl();
	private final ProductDAO productDAO = new ProductDAOImpl();
	private final OrderDAO orderDAO = new OrderDAOImpl();

	private DAOFactory() {}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public ProductDAO getProductDAO() {
		return productDAO;
	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public OrderDAO getOrderDAO() {
		return orderDAO;
	}

	public static DAOFactory getInstance(){
		return INSTANCE;
	}

}

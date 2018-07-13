package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.impl.AdminDAOImpl;
import by.asrohau.iShop.dao.impl.OrderDAOImpl;
import by.asrohau.iShop.dao.impl.ProductDAOImpl;
import by.asrohau.iShop.dao.impl.ClientDAOImpl;

public class DAOFactory {
	
	private static final DAOFactory INSTANCE = new DAOFactory();
	
	private final ClientDAO clientDAO = new ClientDAOImpl();
	private final AdminDAO adminDAO = new AdminDAOImpl();
	private final ProductDAO productDAO = new ProductDAOImpl();
	private final OrderDAO orderDAO = new OrderDAOImpl();

	private DAOFactory() {}
	
	public ClientDAO getClientDAO() {
		return clientDAO;
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

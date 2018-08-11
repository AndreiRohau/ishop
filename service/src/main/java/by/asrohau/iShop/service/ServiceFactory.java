package by.asrohau.iShop.service;

import by.asrohau.iShop.service.impl.OrderServiceImpl;
import by.asrohau.iShop.service.impl.ProductServiceImpl;
import by.asrohau.iShop.service.impl.ReserveServiceImpl;
import by.asrohau.iShop.service.impl.UserServiceImpl;

public class ServiceFactory {

	private static final ServiceFactory INSTANCE = new ServiceFactory();

	private final UserService userService = new UserServiceImpl();
	private final ProductService productService = new ProductServiceImpl();
	private final ReserveService reserveService = new ReserveServiceImpl();
	private final OrderService orderService = new OrderServiceImpl();

	private ServiceFactory() {}

	public static ServiceFactory getInstance() {
		return INSTANCE;
	}

	public UserService getUserService() {
		return userService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public ReserveService getReserveService() {
		return reserveService;
	}

	public OrderService getOrderService() {
		return orderService;
	}
}

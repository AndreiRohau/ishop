package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.dao.ProductDAO;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.service.util.ServiceValidator.validation;

public class ProductServiceImpl implements ProductService {

	private final ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	
	public ProductServiceImpl(){}

	@Override
	public boolean addNewProduct(Product newProduct) throws ServiceException {
		if (!validation(newProduct)) { return false;}
		try {
			return productDAO.save(newProduct);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> getProducts(int row) throws ServiceException {
		try {
			return productDAO.findAll(row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public long countProducts() throws ServiceException {
		try {
			return productDAO.countAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Product findProductWithId(long id) throws ServiceException {
		try {
			return productDAO.findOne(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> findProductsWithIds(List<Reserve> reservations) throws ServiceException {
		try {
			List<Product> products = new ArrayList<>();
			for(Reserve reservation : reservations){
				Product product = productDAO.findOne(reservation.getProductId());
				product.setReserveId(reservation.getId());
				products.add(product);
			}
			return products;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateProduct(Product product) throws ServiceException {
		if (!validation(product)) {
			return false;
		}
		try {
			return productDAO.update(product);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean deleteProduct(long id) throws ServiceException {
		if(!validation(id)){
			return false;
		}
		try {
			return productDAO.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public long countProductsLike(Product product) throws ServiceException {
		try {
			return productDAO.countProductsLike(product);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> getProductsLike(Product product, int row) throws ServiceException { //ArrayList
		try {
			return productDAO.findProductsLike(product, row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}


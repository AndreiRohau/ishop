package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.dao.ProductDAO;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

import static by.asrohau.iShop.service.util.ServiceValidator.validation;

public class ProductServiceImpl implements ProductService {

	private final ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	
	public ProductServiceImpl(){}

	@Override
	public Product findProduct(Product product) throws ServiceException {
		if (!validation(product)) {
			return null;
		}
		try {
			return productDAO.find(product);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

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
	public List<Product> getAllProducts(int row) throws ServiceException { //ArrayList
		try {
			return productDAO.findAll(row);
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
	public boolean deleteProduct(Product product) throws ServiceException {
		if(!validation(product)){
			return false;
		}
		try {
			return productDAO.delete(product.getId());
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
	public long countProductsComprehensive(Product product) throws ServiceException {
		try {
			return productDAO.countProductsComprehensive(product);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> findProductsComprehensive(Product product, int row) throws ServiceException { //ArrayList
		try {
			return productDAO.findProductsComprehensive(product, row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}


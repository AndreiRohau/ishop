package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.ProductDAO;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.service.ProductService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {

	private final ProductDAO productDAO = DAOFactory.getInstance().getProductDAO();
	
	public ProductServiceImpl(){}

	@Override
	public boolean validation(Product product) {
		String toCompare = "";
		return (!toCompare.equals(product.getCompany()) &&
				!toCompare.equals(product.getName()) &&
				!toCompare.equals(product.getType()) &&
				!toCompare.equals(product.getPrice()));
	}

	@Override
	public Product findProduct(Product product) throws ServiceException {
		// validation!!! stub
		if (validation(product)) {
			try {
				return productDAO.findProduct(product);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return null;
	}

	@Override
	public boolean addNewProduct(Product newProduct) throws ServiceException {
		// validation!!! stub
		if (!validation(newProduct)) { return false;}

		try {
			if (productDAO.findProduct(newProduct) == null) {
				return productDAO.addProduct(newProduct);
			}
			Product product = productDAO.findProduct(newProduct); //for test
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	    return true;
	}

	@Override
	public List<Product> getAllProducts(int row) throws ServiceException { //ArrayList
		try {
			return productDAO.selectAllProducts(row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Product findProductWithId(Product product) throws ServiceException {
		//validation!!! stub

		try {
			return productDAO.findProductWithId(product);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateProduct(Product product) throws ServiceException {
		// validation!!! stub
		if (validation(product)) {
			try {
				Product productCheck = productDAO.findProduct(product);
				return (((productCheck == null) || (productCheck.getId() == product.getId())) && productDAO.updateProduct(product));
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return false;
	}

	@Override
	public boolean deleteProduct(Product product) throws ServiceException {
		// validation!!! stub
		try {
			if (productDAO.findProductWithId(product) != null) {
				return productDAO.deleteProduct(product);
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return false;
	}

	@Override
	public int countProducts() throws ServiceException {
		try {
			return productDAO.countProducts();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int countProductsComprehensive(Product product) throws ServiceException {
		try {
			return productDAO.countProductsComprehensive(product);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Product> findProductsComprehensive(Product product, int row) throws ServiceException { //ArrayList
		try {
			return productDAO.selectProductsComprehensive(product, row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}

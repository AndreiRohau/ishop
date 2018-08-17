package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO extends EntityFacadeFootprint<Product> {

	/*
	find products
	 */
	List<Product> findProductsComprehensive(Product product, int row) throws DAOException;

	/*
	count products
	 */
	long countProductsComprehensive(Product product) throws DAOException;
}

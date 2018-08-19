package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO extends EntityFacadeFootprint<Product> {

	/*
	find products like parameter product
	 */
	List<Product> findProductsLike(Product product, int row) throws DAOException;

	/*
	count products like parameter product
	 */
	long countProductsLike(Product product) throws DAOException;
}

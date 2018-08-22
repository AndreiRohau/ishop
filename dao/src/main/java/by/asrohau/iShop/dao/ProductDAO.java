package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Order;
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

	/*
	find products by string of ids
	 */
	List<Product> findProductsByIds(long [] ids) throws DAOException;

	/*
	updates products in order to a latest (used in cases when products not exists in the shop though are in order)
	 */
	void updateProductsInOrder(Order order) throws DAOException;
}

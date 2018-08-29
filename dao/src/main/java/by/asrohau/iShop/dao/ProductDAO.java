package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO extends EntityFacadeFootprint<Product> {

	/**
	 * find products like parameter product
	 * @param product includes criteria for search
	 * @param row determines the row for limit
	 * @return list of found products
	 * @throws DAOException is a module exception
	 */
	List<Product> findProductsLike(Product product, int row) throws DAOException;

	/**
	 * count products like parameter product
	 * @param product includes criteria for search
	 * @return number of products according to criteria
	 * @throws DAOException is a module exception
	 */
	long countProductsLike(Product product) throws DAOException;

	/**
	 * find products by long[] array of ids
	 * @param ids long[] array
	 * @return list of products
	 * @throws DAOException is a module exception
	 */
	List<Product> findProductsByIds(long [] ids) throws DAOException;

	/**
	 * updates products in order to a latest (used in cases when products not exists in the shop though are in order)
	 * @param order includes information about order
	 * @throws DAOException is a module exception
	 */
	void updateProductsInOrder(Order order) throws DAOException;
}

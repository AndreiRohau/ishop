package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.entity.Page;
import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface ProductService {

	/**
	 * saving new product to database
	 * @param newProduct is a Product to save
	 * @return true if successful, otherwise false
	 * @throws ServiceException is a module exception
	 */
	boolean addNewProduct(Product newProduct) throws ServiceException;

	/**
	 * simple getting of all products, limit
	 * @param row for pagination
	 * @return list of Products
	 * @throws ServiceException is a module exception
	 */
	List<Product> getProducts(int row) throws ServiceException;

	/**
	 * simple counting amount of all products
	 * @return number of products
	 * @throws ServiceException is a module exception
	 */
	long countProducts() throws  ServiceException;

	/**
	 * finds product by Id
	 * @param id of the Product to be found
	 * @return found Product
	 * @throws ServiceException is a module exception
	 */
	Product findProductWithId(long id) throws ServiceException;

	/**
	 * finds products by Ids
	 * @param reservations is a list of reservations which include ids of searching products
	 * @return list of Products
	 * @throws ServiceException is a module exception
	 */
	List<Product> findProductsWithIds(List<Reserve> reservations) throws ServiceException;

	/**
	 * finds products by Ids
	 * @param order includes a String of Products' ids separated by comma
	 * @return list of Products
	 * @throws ServiceException is a module exception
	 */
	List<Product> findProductsByOrder(Order order) throws ServiceException;

	/**
	 * choose appropriate part of products according to page
	 * @param products is a list of all products in certain Order
	 * @param page required for pagination
	 * @return list of Products (for a certain page)
	 * @throws ServiceException is a module exception
	 */
	List<Product> subList(List<Product> products, Page page) throws ServiceException;

	/**
	 * admin can change product information with this method
	 * @param product to be updated
	 * @return true if successful, otherwise false
	 * @throws ServiceException is a module exception
	 */
	boolean updateProduct(Product product) throws ServiceException;

	/**
	 * admin can delete product by Id
	 * @param id of deleting product
	 * @return true if successful, otherwise false
	 * @throws ServiceException is a module exception
	 */
	boolean deleteProduct(long id) throws ServiceException;

	/**
	 * finds products default or containing characters
	 * @param product includes information about criteria for searching
	 * @param row required for pagination
	 * @return list of Products
	 * @throws ServiceException is a module exception
	 */
	List<Product> getProductsLike(Product product, int row) throws  ServiceException;

	/**
	 * counts products default or containing characters
	 * @param product includes information about criteria for searching
	 * @return number of Products
	 * @throws ServiceException is a module exception
	 */
	long countProductsLike(Product product) throws  ServiceException;
}

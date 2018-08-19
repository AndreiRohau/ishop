package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface ProductService {

	/*
	saving new product to database
	 */
	boolean addNewProduct(Product newProduct) throws ServiceException;

	/*
	simple getting of all products, limit
	 */
	List<Product> getProducts(int row) throws ServiceException;

	/*
	simple counting amount of all products
	 */
	long countProducts() throws  ServiceException;

	/*
	finds product by Id
	 */
	Product findProductWithId(long id) throws ServiceException;

	/*
	finds products by Ids
	 */
	List<Product> findProductsWithIds(List<Reserve> reservations) throws ServiceException;

	/*
	admin can change product information with this method
	 */
	boolean updateProduct(Product product) throws ServiceException;

	/*
	admin can delete product by Id
	 */
	boolean deleteProduct(long id) throws ServiceException;

	/*
	finds products default or containing characters
	 */
	List<Product> getProductsLike(Product product, int row) throws  ServiceException;

	/*
	counts products default or containing characters
	 */
	long countProductsLike(Product product) throws  ServiceException;
}

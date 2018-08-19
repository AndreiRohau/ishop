package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface ProductService {

	/*
	saving new product to database
	 */
	boolean addNewProduct(Product newProduct) throws ServiceException;

	/*
	simple returning of all products, limit
	 */
	List<Product> getProducts(int row) throws ServiceException;

	/*

	 */
	Product findProductWithId(long id) throws ServiceException;



	boolean updateProduct(Product product) throws ServiceException;

	Product findProduct(Product product) throws ServiceException;
	boolean deleteProduct(Product product) throws ServiceException;
	long countProducts() throws  ServiceException;

	long countProductsComprehensive(Product product) throws  ServiceException;
	List<Product>  findProductsComprehensive(Product product, int row) throws  ServiceException; //ArrayList
}

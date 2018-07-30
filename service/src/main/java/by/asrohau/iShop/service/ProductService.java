package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public interface ProductService {

	boolean addNewProduct(Product newProduct) throws ServiceException;
	List<Product> getAllProducts(int row) throws ServiceException; //ArrayList
	Product findProductWithId(Product product) throws ServiceException;
	boolean updateProduct(Product product) throws ServiceException;

	Product findProduct(Product product) throws ServiceException;
	boolean deleteProduct(Product product) throws ServiceException;
	int countProducts() throws  ServiceException;

	int countProductsComprehensive(Product product) throws  ServiceException;
	List<Product>  findProductsComprehensive(Product product, int row) throws  ServiceException; //ArrayList
}

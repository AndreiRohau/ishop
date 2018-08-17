package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface ProductService {

	boolean addNewProduct(Product newProduct) throws ServiceException;
	List<Product> getAllProducts(int row) throws ServiceException; //ArrayList
	Product findProductWithId(Product product) throws ServiceException;
	boolean updateProduct(Product product) throws ServiceException;

	Product findProduct(Product product) throws ServiceException;
	boolean deleteProduct(Product product) throws ServiceException;
	long countProducts() throws  ServiceException;

	long countProductsComprehensive(Product product) throws  ServiceException;
	List<Product>  findProductsComprehensive(Product product, int row) throws  ServiceException; //ArrayList
}

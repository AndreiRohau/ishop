package by.asrohau.iShop.dao;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO {

	Product findProduct(Product product) throws DAOException;
	boolean addProduct(Product product) throws DAOException;
	List<Product> selectAllProducts(int row) throws DAOException; //arraylist
	Product findProductWithId(Product product) throws DAOException;
	boolean updateProduct(Product product) throws DAOException;
	boolean deleteProduct(Product product) throws DAOException;
	int countProducts() throws DAOException;

    int countProductsComprehensive(Product product) throws DAOException;
	List<Product> selectProductsComprehensive(Product product, int row) throws DAOException; //arraylist

}

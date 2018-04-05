package by.asrohau.iShop.dao;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.ArrayList;

public interface ProductDAO {

	Product findProduct(Product product) throws DAOException;
	boolean addProduct(Product product) throws DAOException;
	ArrayList<Product> selectAllProducts(int row) throws DAOException;
	Product findProductWithId(Product product) throws DAOException;
	boolean updateProduct(Product product) throws DAOException;
	boolean deleteProduct(Product product) throws DAOException;
	int countProducts() throws DAOException;

    int countProductsComprehensive(Product product) throws DAOException;
	ArrayList<Product> selectProductsComprehensive(Product product, int row) throws DAOException;

}

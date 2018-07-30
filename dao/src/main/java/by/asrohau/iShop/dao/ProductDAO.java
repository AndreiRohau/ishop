package by.asrohau.iShop.dao;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface ProductDAO extends AbstractDAO<Product> {

	Product findProductWithId(Product product) throws DAOException;

    int countProductsComprehensive(Product product) throws DAOException;
	List<Product> findProductsComprehensive(Product product, int row) throws DAOException; //arraylist

}

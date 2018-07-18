package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.AbstractConnection;
import by.asrohau.iShop.dao.ProductDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.dao.util.DAOFinals.*;

public class ProductDAOImpl extends AbstractConnection implements ProductDAO {

	private final static Logger logger = Logger.getLogger(UserDAOImpl.class);

	private PreparedStatement preparedStatement = null;
	private Connection connection = null;
	private ResultSet resultSet = null;

	/*
	save new Product
	 */
	@Override
	public boolean save(Product product) throws DAOException {
		if(find(product) != null){
			return false;
		}
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(ADD_NEW_PRODUCT_QUERY.inString);
			preparedStatement.setString(1, product.getCompany());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getType());
			preparedStatement.setString(4, product.getPrice());
			preparedStatement.setString(5, product.getDescription());

			int result = preparedStatement.executeUpdate();
			connection.commit();
			return (result != 0);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new DAOException(EXCEPTION_WHILE_ROLL_BACK.inString, ex);
			}
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	/*
	find Product by Company, Name, Type, Price
	 */
	@Override
	public Product find(Product product) throws DAOException {
		try {
			connection = getConnection();
			preparedStatement = getConnection().prepareStatement(FIND_EQUAL_PRODUCT_QUERY.inString);
			preparedStatement.setString(1, product.getCompany());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getType());
			preparedStatement.setString(4, product.getPrice());
			resultSet = preparedStatement.executeQuery();
			Product foundProduct = new Product();

			while (resultSet.next()) {
				foundProduct.setId(resultSet.getInt(1));
				foundProduct.setCompany(resultSet.getString(2));
				foundProduct.setName(resultSet.getString(3));
				foundProduct.setType(resultSet.getString(4));
				foundProduct.setPrice(resultSet.getString(5));
				foundProduct.setDescription(resultSet.getString(6));
			}

			if (foundProduct.getName() != null) {
				return foundProduct;
			}
			logger.info(CANNOT_IDENTIFY_PRODUCT.inString);
			return null;
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	/*
	update product
	 */
	@Override
	public boolean update(Product product) throws DAOException {
		Product productCheck = find(product);
		if(productCheck != null && productCheck.getId() != product.getId()){
			return false;
		}
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_QUERY.inString);
			preparedStatement.setString(1, product.getCompany());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getType());
			preparedStatement.setString(4, product.getPrice());
			preparedStatement.setString(5, product.getDescription());
			preparedStatement.setInt(6, product.getId());

			int result = preparedStatement.executeUpdate();
			connection.commit();
			return (result != 0);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new DAOException(EXCEPTION_WHILE_ROLL_BACK.inString, ex);
			}
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(preparedStatement, connection);
		}
	}

	/*
	delete existing product
	 */
	@Override
	public boolean delete(Product product) throws DAOException {
		if(find(product) == null){
			return false;
		}
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_PRODUCT_QUERY.inString);
			preparedStatement.setInt(1, product.getId());

			int result = preparedStatement.executeUpdate();
			connection.commit();
			return (result != 0);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new DAOException(EXCEPTION_WHILE_ROLL_BACK.inString, ex);
			}
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(preparedStatement, connection);
		}

	}

	/*
	find all Products
	 */
	@Override
	public List<Product> findAll(int row) throws DAOException {
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS_QUERY.inString);
			preparedStatement.setInt(1, row);
			preparedStatement.setInt(2, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
			ArrayList<Product> productArrayList = new ArrayList<Product>();
			resultSet = preparedStatement.executeQuery();
			Product product;

			int id;
			String company;
			String name;
			String type;
			String price;
			String description;
			while (resultSet.next()) {
				id = resultSet.getInt(1);
				company = resultSet.getString(2);
				name = resultSet.getString(3);
				type = resultSet.getString(4);
				price = resultSet.getString(5);
				description = resultSet.getString(6);
				product = new Product(id, company, name, type, price, description);
				productArrayList.add(product);
			}

			return productArrayList;
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	/*
	count amount of Products in the table 'products'
	 */
	@Override
	public long countAll() throws DAOException {
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(COUNT_PRODUCTS_QUERY.inString);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public Product findProductWithId(Product product) throws DAOException {
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_PRODUCT_WITH_ID_QUERY.inString);
			preparedStatement.setInt(1, product.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				product.setId(resultSet.getInt(1));
				product.setCompany(resultSet.getString(2));
				product.setName(resultSet.getString(3));
				product.setType(resultSet.getString(4));
				product.setPrice(resultSet.getString(5));
				product.setDescription(resultSet.getString(6));
			}

			if (product.getName() != null) {
				return product;
			}
			logger.info(CANNOT_IDENTIFY_PRODUCT_BY_ID.inString);
			return null;
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	//comprehensive part
	@Override
	public int countProductsComprehensive(Product product) throws DAOException {
		String query = COUNT_PRODUCTS_COMPREHENSIVE_QUERY.inString;
		if(product.getCompany() != null){
			query += " AND company = \'" + product.getCompany() + "\'";
		}
		if(product.getName() != null){
			query += " AND inString = \'" + product.getName() + "\'";
		}
		if(product.getType() != null){
			query += " AND type = \'" + product.getType() + "\'";
		}
		if(product.getPrice() != null){
			query += " AND price = \'" + product.getPrice() + "\'";
		}

		logger.info("- - - QUERY is : [" + query + "]");
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getInt(1);
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public List<Product> findProductsComprehensive(Product product, int row) throws DAOException {
		String query = SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY.inString;
		if(product.getCompany() != null){
			query += " AND company = \'" + product.getCompany() + "\'";
		}
		if(product.getName() != null){
			query += " AND inString = \'" + product.getName() + "\'";
		}
		if(product.getType() != null){
			query += " AND type = \'" + product.getType() + "\'";
		}
		if(product.getPrice() != null){
			query += " AND price = \'" + product.getPrice() + "\'";
		}
		query += " LIMIT ?,?";
		logger.info("- - - QUERY is : [" + query + "]");
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, row);
			preparedStatement.setInt(2, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
			List<Product> productArrayList = new ArrayList<Product>();
			resultSet = preparedStatement.executeQuery();
			Product productFound;

			int id;
			String company;
			String name;
			String type;
			String price;
			String description;
			while (resultSet.next()) {
				id = resultSet.getInt(1);
				company = resultSet.getString(2);
				name = resultSet.getString(3);
				type = resultSet.getString(4);
				price = resultSet.getString(5);
				description = resultSet.getString(6);
				productFound = new Product(id, company, name, type, price, description);
				productArrayList.add(productFound);
			}
			return productArrayList;
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}
}

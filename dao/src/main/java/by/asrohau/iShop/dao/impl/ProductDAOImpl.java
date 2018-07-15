package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.ProductDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.util.DAOFinals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl extends AbstractDAO<Product> implements ProductDAO {

	@Override
	public Product findProduct(Product product) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(DAOFinals.FIND_EQUAL_PRODUCT_QUERY.inString)) {
			preparedStatement.setString(1, product.getCompany());
			preparedStatement.setString(2, product.getName());
			preparedStatement.setString(3, product.getType());
			preparedStatement.setString(4, product.getPrice());
			ResultSet resultSet = preparedStatement.executeQuery();
			Product foundProduct = new Product();

			while (resultSet.next()) {
				foundProduct.setId(resultSet.getInt(1));
				foundProduct.setCompany(resultSet.getString(2));
				foundProduct.setName(resultSet.getString(3));
				foundProduct.setType(resultSet.getString(4));
				foundProduct.setPrice(resultSet.getString(5));
				foundProduct.setDescription(resultSet.getString(6));
			}
			preparedStatement.close();
			connection.close();

			if (foundProduct.getName() != null) {
				System.out.println("foundProduct.getName() != null : " + foundProduct.toString());
				return foundProduct;
			}
			System.out.println("Did not find = " + product.toString());
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean addProduct(Product product) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.ADD_NEW_PRODUCT_QUERY.inString)) {
			statement.setString(1, product.getCompany());
			statement.setString(2, product.getName());
			statement.setString(3, product.getType());
			statement.setString(4, product.getPrice());
			statement.setString(5, product.getDescription());

			int result = statement.executeUpdate();
			statement.close();
			connection.close();
			return (result != 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public ArrayList<Product> selectAllProducts(int row) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(DAOFinals.SELECT_ALL_PRODUCTS_QUERY.inString)) {
			preparedStatement.setInt(1, row);
			preparedStatement.setInt(2, 15);
			ArrayList<Product> productArrayList = new ArrayList<Product>();
			ResultSet resultSet = preparedStatement.executeQuery();
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
			preparedStatement.close();
			connection.close();
			return productArrayList;

		} catch (SQLException e) {
			System.out.println("dao exception while get all products");
			throw new DAOException(e);
		}
	}

	@Override
	public Product findProductWithId(Product product) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(DAOFinals.FIND_PRODUCT_WITH_ID_QUERY.inString)) {
			preparedStatement.setInt(1, product.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				product.setId(resultSet.getInt(1));
				product.setCompany(resultSet.getString(2));
				product.setName(resultSet.getString(3));
				product.setType(resultSet.getString(4));
				product.setPrice(resultSet.getString(5));
				product.setDescription(resultSet.getString(6));
			}
			preparedStatement.close();
			connection.close();

			if (product.getName() != null) {
				return product;
			}
			System.out.println("Did not find Product with id = " + product.getId());
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean updateProduct(Product product) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.UPDATE_PRODUCT_QUERY.inString)) {
			statement.setString(1, product.getCompany());
			statement.setString(2, product.getName());
			statement.setString(3, product.getType());
			statement.setString(4, product.getPrice());
			statement.setString(5, product.getDescription());
			statement.setInt(6, product.getId());

			int result = statement.executeUpdate();
			statement.close();
			connection.close();
			return (result != 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean deleteProduct(Product product) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.DELETE_PRODUCT_QUERY.inString)) {
			statement.setInt(1, product.getId());

			int result = statement.executeUpdate();
			statement.close();
			connection.close();
			return (result != 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public int countProducts() throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.COUNT_PRODUCTS_QUERY.inString)) {
			ResultSet resultSet = statement.executeQuery();

			resultSet.next();
			int i = resultSet.getInt(1);
			statement.close();
			connection.close();
			return i;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public int countProductsComprehensive(Product product) throws DAOException {
		if(product.getCompany() != null){
			DAOFinals.COUNT_PRODUCTS_COMPREHENSIVE_QUERY.inString += " AND company = \'" + product.getCompany() + "\'";
		}
		if(product.getName() != null){
			DAOFinals.COUNT_PRODUCTS_COMPREHENSIVE_QUERY.inString += " AND inString = \'" + product.getName() + "\'";
		}
		if(product.getType() != null){
			DAOFinals.COUNT_PRODUCTS_COMPREHENSIVE_QUERY.inString += " AND type = \'" + product.getType() + "\'";
		}
		if(product.getPrice() != null){
			DAOFinals.COUNT_PRODUCTS_COMPREHENSIVE_QUERY.inString += " AND price = \'" + product.getPrice() + "\'";
		}

		System.out.println("- - - QUERY is : [" + DAOFinals.COUNT_PRODUCTS_COMPREHENSIVE_QUERY.inString + "]");
		try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.COUNT_PRODUCTS_COMPREHENSIVE_QUERY.inString)) {

			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int i = resultSet.getInt(1);
			statement.close();
			connection.close();
			DAOFinals.COUNT_PRODUCTS_COMPREHENSIVE_QUERY.inString = "SELECT COUNT(*) FROM shop.products WHERE id";
			return i;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public ArrayList<Product> selectProductsComprehensive(Product product, int row) throws DAOException {
		if(product.getCompany() != null){
			DAOFinals.SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY.inString += " AND company = \'" + product.getCompany() + "\'";
		}
		if(product.getName() != null){
			DAOFinals.SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY.inString += " AND inString = \'" + product.getName() + "\'";
		}
		if(product.getType() != null){
			DAOFinals.SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY.inString += " AND type = \'" + product.getType() + "\'";
		}
		if(product.getPrice() != null){
			DAOFinals.SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY.inString += " AND price = \'" + product.getPrice() + "\'";
		}
		DAOFinals.SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY.inString += " LIMIT ?,?";
		System.out.println("- - - QUERY is : [" + DAOFinals.SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY.inString + "]");

		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(DAOFinals.SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY.inString)) {
			preparedStatement.setInt(1, row);
			preparedStatement.setInt(2, 15);
			ArrayList<Product> productArrayList = new ArrayList<Product>();
			ResultSet resultSet = preparedStatement.executeQuery();
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

			preparedStatement.close();
			connection.close();
			DAOFinals.SELECT_ALL_PRODUCTS_COMPREHENSIVE_QUERY.inString = "SELECT * FROM shop.products WHERE id";
			return productArrayList;

		} catch (SQLException e) {
			System.out.println("dao exception while get all products");
			throw new DAOException(e);
		}
	}
}

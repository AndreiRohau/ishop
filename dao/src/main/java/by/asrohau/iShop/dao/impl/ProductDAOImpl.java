package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.ConnectionPool;
import by.asrohau.iShop.dao.ProductDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.dao.DAOFinals.MAX_ROWS_AT_PAGE;

public class ProductDAOImpl extends AbstractDAO implements ProductDAO {
	private final static Logger logger = LoggerFactory.getLogger(ProductDAOImpl.class);
	/*
    ProductDAO queries
     */
	private static final String FIND_EQUAL_PRODUCT_QUERY = "SELECT * FROM shop.products WHERE company = ? AND name = ? AND type = ? AND price = ?";
	private static final String SAVE_PRODUCT_QUERY = "INSERT INTO shop.products (company, name, type, price, description) VALUES (?,?,?,?,?)";
	private static final String FIND_PRODUCT_BY_ID_QUERY = "SELECT * FROM shop.products WHERE id = ?";
	private static final String UPDATE_PRODUCT_BY_ID_QUERY = "UPDATE shop.products SET company = ?, name = ?, type = ?, price = ?, description = ? WHERE id = ?";
	private static final String DELETE_PRODUCT_BY_ID_QUERY = "DELETE FROM shop.products WHERE id = ?";
	private static final String DELETE_RESERVATIONS_BY_PRODUCT_ID_QUERY = "DELETE FROM shop.reserve WHERE productId = ?";
	private static final String FIND_PRODUCTS_LIMIT_QUERY = "SELECT * FROM shop.products LIMIT ?, ?";
	private static final String COUNT_PRODUCTS_QUERY = "SELECT COUNT(*) FROM shop.products";
	private static final String COUNT_PRODUCTS_LIKE_QUERY = "SELECT COUNT(*) FROM shop.products WHERE company LIKE ? AND name LIKE ? AND type LIKE ? AND price LIKE ?";
	private static final String FIND_PRODUCTS_LIKE_QUERY = "SELECT * FROM shop.products WHERE company LIKE ? AND name LIKE ? AND type LIKE ? AND price LIKE ? LIMIT ?,?";

	public ProductDAOImpl(ConnectionPool connectionPool) {
		super(connectionPool);
	}

	/*
        save new Product
         */
	@Override
	public boolean save(Product product) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			int result = 0;
			connection = getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(FIND_EQUAL_PRODUCT_QUERY);
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

			if (foundProduct.getName() == null) {
				preparedStatement = connection.prepareStatement(SAVE_PRODUCT_QUERY);
				preparedStatement.setString(1, product.getCompany());
				preparedStatement.setString(2, product.getName());
				preparedStatement.setString(3, product.getType());
				preparedStatement.setString(4, product.getPrice());
				preparedStatement.setString(5, product.getDescription());
				result = preparedStatement.executeUpdate();
			}

			connection.commit();
			return (result != 0);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new DAOException("Error during rollback", ex);
			}
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			returnConnection(connection);
		}
	}

	/*
	find Product by Company, Name, Type, Price
	 */
	@Override
	public Product find(Product product) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_EQUAL_PRODUCT_QUERY);
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
			logger.info("Can not identify Product");
			return null;
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			returnConnection(connection);
		}
	}

	/*
	find product by ID
	 */
	@Override
	public Product findOne(long id) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID_QUERY);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			Product product = new Product();
			while (resultSet.next()) {
				product.setId(resultSet.getLong(1));
				product.setCompany(resultSet.getString(2));
				product.setName(resultSet.getString(3));
				product.setType(resultSet.getString(4));
				product.setPrice(resultSet.getString(5));
				product.setDescription(resultSet.getString(6));
			}

			if (product.getId() != 0) {
				return product;
			}
			logger.info("Can not identify Product by id");
			return null;
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			returnConnection(connection);
		}
	}

	/*
	update product
	 */
	@Override
	public boolean update(Product product) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			int result = 0;
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(FIND_EQUAL_PRODUCT_QUERY);
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
			if(foundProduct.getId() == 0 || foundProduct.getId() == product.getId()){
				preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_BY_ID_QUERY);
				preparedStatement.setString(1, product.getCompany());
				preparedStatement.setString(2, product.getName());
				preparedStatement.setString(3, product.getType());
				preparedStatement.setString(4, product.getPrice());
				preparedStatement.setString(5, product.getDescription());
				preparedStatement.setLong(6, product.getId());
				result = preparedStatement.executeUpdate();
			}

			connection.commit();
			return (result != 0);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new DAOException("Error during rollback", ex);
			}
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			returnConnection(connection);
		}
	}

	/*
	delete existing product by id
	 */
	@Override
	public boolean delete(long id) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_RESERVATIONS_BY_PRODUCT_ID_QUERY);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();
			preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID_QUERY);
			preparedStatement.setLong(1, id);
			int result = preparedStatement.executeUpdate();
			connection.commit();
			return (result != 0);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new DAOException("Error during rollback", ex);
			}
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(null, preparedStatement);
			returnConnection(connection);
		}
	}

	/*
	find all Products, limit
	 */
	@Override
	public List<Product> findAll(int row) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_PRODUCTS_LIMIT_QUERY);
			preparedStatement.setInt(1, row);
			preparedStatement.setInt(2, MAX_ROWS_AT_PAGE);
			ArrayList<Product> products = new ArrayList<>();
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Product productFound = new Product(resultSet.getLong(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5),
						resultSet.getString(6));
				products.add(productFound);
			}

			return products;
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			returnConnection(connection);
		}
	}

	/*
	count amount of Products in the table 'products'
	 */
	@Override
	public long countAll() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(COUNT_PRODUCTS_QUERY);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getLong(1);
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			returnConnection(connection);
		}
	}

	@Override
	public List<Product> findProductsLike(Product product, int row) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_PRODUCTS_LIKE_QUERY);
			preparedStatement.setString(1, "%" + product.getCompany() + "%");
			preparedStatement.setString(2, "%" + product.getName() + "%");
			preparedStatement.setString(3, "%" + product.getType() + "%");
			preparedStatement.setString(4, "%" + product.getPrice() + "%");
			preparedStatement.setInt(5, row);
			preparedStatement.setInt(6, MAX_ROWS_AT_PAGE);
			resultSet = preparedStatement.executeQuery();

			List<Product> products = new ArrayList<>();
			while (resultSet.next()) {
				Product productFound = new Product(resultSet.getLong(1),
						resultSet.getString(2),
						resultSet.getString(3),
						resultSet.getString(4),
						resultSet.getString(5),
						resultSet.getString(6));
				products.add(productFound);
			}
			return products;
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			returnConnection(connection);
		}
	}

	@Override
	public long countProductsLike(Product product) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(COUNT_PRODUCTS_LIKE_QUERY);
			preparedStatement.setString(1, "%" + product.getCompany() + "%");
			preparedStatement.setString(2, "%" + product.getName() + "%");
			preparedStatement.setString(3, "%" + product.getType() + "%");
			preparedStatement.setString(4, "%" + product.getPrice() + "%");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getLong(1);
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			returnConnection(connection);
		}
	}

	@Override
	public List<Product> findProductsByIds(long [] ids) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			List<Product> products = new ArrayList<>();
			for (long id : ids) {
				preparedStatement = connection.prepareStatement(FIND_PRODUCT_BY_ID_QUERY);
				preparedStatement.setLong(1, id);
				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					Product productFound = new Product(resultSet.getLong(1),
							resultSet.getString(2),
							resultSet.getString(3),
							resultSet.getString(4),
							resultSet.getString(5),
							resultSet.getString(6));
					products.add(productFound);
				}
			}
			connection.commit();
			return products;
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			returnConnection(connection);
		}
	}

	@Override
	public void updateProductsInOrder(Order order) throws DAOException {
		PreparedStatement preparedStatement = null;
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("UPDATE shop.orders SET products = ? WHERE id = ?");
			preparedStatement.setString(1, order.getProductIds());
			preparedStatement.setLong(2, order.getId());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new DAOException("Error during rollback", ex);
			}
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(null, preparedStatement);
			returnConnection(connection);
		}
	}
}

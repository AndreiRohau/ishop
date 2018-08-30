package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.ConnectionPool;
import by.asrohau.iShop.dao.UserDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.dao.DAOFinals.MAX_ROWS_AT_PAGE;

public class UserDAOImpl extends AbstractDAO implements UserDAO {

	private final static Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);
	/**
	 * UserDAO queries
	 */
	private static final String SAVE_USER_QUERY = "INSERT INTO shop.users (login, password) VALUES (?,?)";
	private static final String FIND_USER_BY_ID_QUERY = "SELECT * FROM shop.users WHERE id = ?";
	private static final String FIND_USER_BY_LOGIN_QUERY = "SELECT * FROM shop.users WHERE login = ?";
	private static final String FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY = "SELECT * FROM shop.users WHERE login = ? AND password = ?";
	private static final String FIND_USERS_LIMIT_QUERY = "SELECT * FROM shop.users LIMIT ?,?";
	private static final String COUNT_USERS_QUERY = "SELECT COUNT(*) FROM shop.users";
	private static final String UPDATE_USER_BY_ID_QUERY = "UPDATE shop.users SET login = ?, password = ? WHERE id = ?";
	private static final String UPDATE_PASSWORD_BY_LOGIN_AND_PASSWORD_QUERY = "UPDATE shop.users SET password = ? WHERE login = ? AND password = ?";
	private static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM shop.users WHERE id = ? AND role = 'user'";
	private static final String DELETE_ORDERS_BY_USER_ID_QUERY = "DELETE FROM shop.orders WHERE user = ?";
	private static final String DELETE_RESERVATIONS_BY_USER_ID_QUERY = "DELETE FROM shop.reserve WHERE userId = ?";

	public UserDAOImpl(ConnectionPool connectionPool) {
		super(connectionPool);
	}

	/**
	 * save new User
	 */
	@Override
	public boolean save(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			int result = 0;
			connection = connectionPool.provide();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_QUERY);
			preparedStatement.setString(1, user.getLogin());
			resultSet = preparedStatement.executeQuery();
			User userCheck = new User();
			while (resultSet.next()) {
				userCheck.setId(resultSet.getLong(1));
				userCheck.setLogin(resultSet.getString(2));
				userCheck.setRole(resultSet.getString(4));
			}

			if (userCheck.getLogin() == null) {
				preparedStatement = connection.prepareStatement(SAVE_USER_QUERY);
				preparedStatement.setString(1, user.getLogin());
				preparedStatement.setString(2, user.getPassword());
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
			connectionPool.retrieve(connection);
		}
	}

	/**
	 * find by login and password
	 */
	@Override
	public User find(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.provide();
			preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD_QUERY);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			resultSet = preparedStatement.executeQuery();
			user = new User();
			while (resultSet.next()) {
				user.setId(resultSet.getLong(1));
				user.setLogin(resultSet.getString(2));
				user.setRole(resultSet.getString(4));
			}
			if (user.getLogin() != null) {
				return user;
			}
			logger.info("Can not identify User by login and password");
			return null;
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			connectionPool.retrieve(connection);
		}
	}

	/**
	 * find user by id
	 */
	@Override
	public User findOne(long id) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.provide();
			preparedStatement = connection.prepareStatement(FIND_USER_BY_ID_QUERY);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();

			User user = new User();
			while (resultSet.next()) {
				user.setId(resultSet.getLong(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(resultSet.getString(4));
			}

			if (user.getLogin() != null) {
				return user;
			}
			logger.info("Can not identify User with id");
			return null;
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			connectionPool.retrieve(connection);
		}
	}

	/**
	 * change user's credentials where id=?
	 */
	@Override
	public boolean update(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			int result = 0;
			connection = connectionPool.provide();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_QUERY);
			preparedStatement.setString(1, user.getLogin());
			resultSet = preparedStatement.executeQuery();
			User userCheck = new User();
			while (resultSet.next()) {
				userCheck.setId(resultSet.getLong(1));
				userCheck.setLogin(resultSet.getString(2));
				userCheck.setRole(resultSet.getString(4));
			}

			if(userCheck.getId() == 0 || user.getId() == userCheck.getId()) {
				preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID_QUERY);
				preparedStatement.setString(1, user.getLogin());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setLong(3, user.getId());
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
			connectionPool.retrieve(connection);
		}
	}

	/**
	 * delete User by id - all his reserved products and all orders, NO EFFECTS FOR ADMIN
	 */
	@Override
	public boolean delete(long id) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.provide();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(DELETE_ORDERS_BY_USER_ID_QUERY);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement(DELETE_RESERVATIONS_BY_USER_ID_QUERY);
			preparedStatement.setLong(1, id);
			preparedStatement.executeUpdate();

			preparedStatement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY);
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
			connectionPool.retrieve(connection);
		}
	}

	/**
	 * find all Users limit
	 */
	@Override
	public List<User> findAll(int row) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.provide();
			preparedStatement = connection.prepareStatement(FIND_USERS_LIMIT_QUERY);
			preparedStatement.setInt(1, row);
			preparedStatement.setInt(2, MAX_ROWS_AT_PAGE);
			List<User> users = new ArrayList<>();
			resultSet = preparedStatement.executeQuery();
			User user;

			long id;
			String login;
			String password;
			String role;
			while (resultSet.next()) {
				id = resultSet.getLong(1);
				login = resultSet.getString(2);
				password = resultSet.getString(3);
				role = resultSet.getString(4);
				user = new User(id, login, password, role);
				users.add(user);
			}
			return users;
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			connectionPool.retrieve(connection);
		}
	}

	/**
	 * count amount of user in the table 'users'
	 */
	@Override
	public long countAll() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.provide();
			preparedStatement = connection.prepareStatement(COUNT_USERS_QUERY);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getLong(1);
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			connectionPool.retrieve(connection);
		}
	}

	@Override
	public UserDTO findUserByLogin(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionPool.provide();
			preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_QUERY);
			preparedStatement.setString(1, user.getLogin());
			resultSet = preparedStatement.executeQuery();
			UserDTO userDTO = new UserDTO();

			while (resultSet.next()) {
				userDTO.setId(resultSet.getLong(1));
				userDTO.setLogin(resultSet.getString(2));
				userDTO.setRole(resultSet.getString(4));
			}

			if (userDTO.getLogin() != null) {
				return userDTO;
			}
			logger.info("Can not identify User by login and password");
			return null;
		} catch (SQLException e) {
			throw new DAOException("Error in DAO method", e);
		} finally {
			close(resultSet, preparedStatement);
			connectionPool.retrieve(connection);
		}
	}

	@Override
	public boolean changeUserPassword(User user, String newPassword) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.provide();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE_PASSWORD_BY_LOGIN_AND_PASSWORD_QUERY);
			preparedStatement.setString(1, newPassword);
			preparedStatement.setString(2, user.getLogin());
			preparedStatement.setString(3, user.getPassword());

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
			connectionPool.retrieve(connection);
		}
	}

}

package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.AbstractConnection;
import by.asrohau.iShop.dao.UserDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.dao.util.DAOFinals.*;

public class UserDAOImpl extends AbstractConnection implements UserDAO {

	private final static Logger logger = Logger.getLogger(UserDAOImpl.class);

	private PreparedStatement preparedStatement = null;
	private Connection connection = null;
	private ResultSet resultSet = null;

	/*
	save new User
	 */
	@Override
	public boolean save(User user) throws DAOException {
		if(findUserWithLogin(user) != null){
			return false;
		}
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SAVE_USER_QUERY.inString);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getRole());
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
	find with login and password
	 */
	@Override
	public User find(User user) throws DAOException {
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_USER_WITH_LOGIN_PASSWORD_QUERY.inString);
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
			logger.info(CANNOT_IDENTIFY_USER_BY_LOGIN_AND_PASSWORD.inString);
			return null;
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	/*
	change user's credentials
	 */
	@Override
	public boolean update(User user) throws DAOException {
		UserDTO userCheck = findUserWithLogin(user);
		if(userCheck != null && userCheck.getId() != user.getId()){
			return false;
		}
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE_USER_QUERY.inString);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setLong(3, user.getId());

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
	delete User
	 */
	@Override
	public boolean delete(User user) throws DAOException {
		if(find(user) == null){
			return false;
		}
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(DELETE_USER_QUERY.inString);
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());

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
	find all Users
	 */
	@Override
	public List<User> findAll(int row) throws DAOException {
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_QUERY.inString);
			preparedStatement.setInt(1, row);
			preparedStatement.setInt(2, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
			List<User> userArrayList = new ArrayList<>();
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
				userArrayList.add(user);
			}

			return userArrayList;
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	/*
	count amount of user in the table 'users'
	 */
	@Override
	public long countAll() throws DAOException {
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(COUNT_USERS_QUERY.inString);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			return resultSet.getLong(1);
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public UserDTO findUserWithLogin(User user) throws DAOException {
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_USER_WITH_LOGIN_QUERY.inString);
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
			logger.info(CANNOT_IDENTIFY_USER_BY_LOGIN_AND_PASSWORD.inString);
			return null;
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public User findUserWithId(User user) throws DAOException {
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(FIND_USER_WITH_ID_QUERY.inString);
			preparedStatement.setLong(1, user.getId());
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user.setId(resultSet.getLong(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
				user.setRole(resultSet.getString(4));
			}

			if (user.getLogin() != null) {
				return user;
			}
			logger.info(CANNOT_IDENTIFY_USER_BY_ID.inString);
			return null;
		} catch (SQLException e) {
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(resultSet, preparedStatement, connection);
		}
	}

	@Override
	public boolean renewPassword(User user, String newPassword) throws DAOException {
		if(find(user) == null){
			return false;
		}
		try {
			connection = getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(CHANGE_PASSWORD_QUERY.inString);
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
				throw new DAOException(EXCEPTION_WHILE_ROLL_BACK.inString, ex);
			}
			throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
		} finally {
			close(preparedStatement, connection);
		}
	}

}

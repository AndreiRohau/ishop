package by.asrohau.iShop.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.UserDAO;
import by.asrohau.iShop.dao.exception.DAOException;

public class UserDAOImpl extends AbstractDAO<User> implements UserDAO {

	private String FIND_USER_WITH_LOGIN_PASSWORD_QUERY = "SELECT * FROM shop.users WHERE login = ? AND password = ?";
	private String FIND_USER_WITH_LOGIN_QUERY = "SELECT * FROM shop.users WHERE login = ?";
	private String SAVE_USER_QUERY = "INSERT INTO shop.users (login, password) VALUES (?,?)";
	private String CHANGE_PASSWORD_QUERY = "UPDATE shop.users SET password = ? WHERE login = ? AND password = ?";
	private String DELETE_USER_QUERY = "DELETE FROM shop.users WHERE login = ? AND password = ?";
	private String SELECT_ALL_USERS_QUERY = "SELECT * FROM shop.users LIMIT ?,?";
	private String FIND_USER_WITH_ID_QUERY = "SELECT * FROM shop.users WHERE id = ?";
	private String UPDATE_USER_QUERY = "UPDATE shop.users SET login = ?, password = ? WHERE id = ?";
	private String COUNT_USERS_QUERY = "SELECT COUNT(*) FROM shop.users";

	@Override
	public UserDTO findUserWithLoginAndPassword(User user) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(FIND_USER_WITH_LOGIN_PASSWORD_QUERY)) {
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			ResultSet resultSet = preparedStatement.executeQuery();
			UserDTO userDTO = new UserDTO();

			while (resultSet.next()) {
				userDTO.setId(resultSet.getInt(1));
				userDTO.setLogin(resultSet.getString(2));
			}
			preparedStatement.close();
			connection.close();

			if (userDTO.getLogin() != null) {
				return userDTO;
			}
			System.out.println("Did not find User with login = " + user.getLogin());
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public UserDTO findUserWithLogin(User user) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_USER_WITH_LOGIN_QUERY)) {
			preparedStatement.setString(1, user.getLogin());
			ResultSet resultSet = preparedStatement.executeQuery();
			UserDTO userDTO = new UserDTO();

			while (resultSet.next()) {
				userDTO.setId(resultSet.getInt(1));
				userDTO.setLogin(resultSet.getString(2));
			}
			preparedStatement.close();
			connection.close();

			if (userDTO.getLogin() != null) {
				return userDTO;
			}
			System.out.println("Did not find User with login = " + user.getLogin());
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	@Override
	public boolean saveUser(User user) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(SAVE_USER_QUERY)) {
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());

			statement.executeUpdate();
			statement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean changePassword(User user) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(CHANGE_PASSWORD_QUERY)) {
			statement.setString(1, user.getNewPassword());
			statement.setString(2, user.getLogin());
			statement.setString(3, user.getPassword());

			int result = statement.executeUpdate();
			statement.close();
			connection.close();
			// check the executed UPDATE
			return (result != 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean deleteUser(User user) throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(DELETE_USER_QUERY)) {
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());

			int result = statement.executeUpdate();
			statement.close();
			connection.close();
			return (result != 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public ArrayList<User> selectAllUsers(int row) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(SELECT_ALL_USERS_QUERY)) {
			preparedStatement.setInt(1, row);
			preparedStatement.setInt(2, 15);
			ArrayList<User> userArrayList = new ArrayList<User>();
			ResultSet resultSet = preparedStatement.executeQuery();
			User user;

			int id;
			String login;
			String password;
			while (resultSet.next()) {
				id = resultSet.getInt(1);
				login = resultSet.getString(2);
				password = resultSet.getString(3);
				user = new User(id, login, password);
				userArrayList.add(user);
			}
			preparedStatement.close();
			connection.close();
			return userArrayList;

		} catch (SQLException e) {
			System.out.println("dao exception while get all users");
			throw new DAOException(e);
		}
	}

	@Override
	public User findUserWithId(User user) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(FIND_USER_WITH_ID_QUERY)) {
			preparedStatement.setInt(1, user.getId());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setLogin(resultSet.getString(2));
				user.setPassword(resultSet.getString(3));
			}
			preparedStatement.close();
			connection.close();

			if (user.getLogin() != null) {
				return user;
			}
			System.out.println("Did not find User with id = " + user.getId());
			return null;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public boolean updateUser(User user) throws DAOException {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(UPDATE_USER_QUERY)) {
			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setInt(3, user.getId());

			int result = preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close();
			return (result != 0);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public int countProducts() throws DAOException {
		try (PreparedStatement statement = getConnection().prepareStatement(COUNT_USERS_QUERY)) {
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
}

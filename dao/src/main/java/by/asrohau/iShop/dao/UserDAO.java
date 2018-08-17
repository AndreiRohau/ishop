package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import by.asrohau.iShop.dao.exception.DAOException;

public interface UserDAO extends EntityFacadeFootprint<User> {

	/*
	find user by login
	 */
	UserDTO findUserByLogin(User user) throws DAOException;

	/*
	change user's password by login and password
	 */
	boolean changeUserPassword(User user, String newPassword) throws DAOException;
}

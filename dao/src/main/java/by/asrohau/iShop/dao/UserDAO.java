package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import by.asrohau.iShop.dao.exception.DAOException;

public interface UserDAO extends EntityFacadeFootprint<User> {

	/**
	 * <p>finds user by login</p>
	 * @param user module entity
	 * @return user as a UserDTO
	 * @throws DAOException module exception
	 */
	UserDTO findUserByLogin(User user) throws DAOException;

	/**
	 * <p>changes user's password by login and password</p>
	 * @param user module entity
	 * @param newPassword String
	 * @return true when method worked correctly
	 * @throws DAOException module exception
	 */
	boolean changeUserPassword(User user, String newPassword) throws DAOException;
}

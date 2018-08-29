package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface UserService {

	/**
	 * Register new User
	 * @param user to be saved
	 * @param password2 to match saving password
	 * @return true if successful, otherwise false
	 * @throws ServiceException is a module exception
	 */
	boolean registration(User user, String password2) throws ServiceException;

	/**
	 * log in User
	 * @param user to be logged in
	 * @return UserDTO
	 * @throws ServiceException is a module exception
	 */
	UserDTO logination(User user) throws ServiceException;

	/**
	 * finds User by id
	 * @param id of the searching User
	 * @return found User
	 * @throws ServiceException is a module exception
	 */
	User findUserWithId(long id) throws ServiceException;

	/**
	 * general user update
	 * @param user to be updated
	 * @return true if successful, otherwise false
	 * @throws ServiceException is a module exception
	 */
	boolean updateUser(User user) throws ServiceException;

	/**
	 * changes user's password
	 * @param user to change password
	 * @param newPassword new Password
	 * @param sessionLogin should match the parameter User's login
	 * @return true if successful, otherwise false
	 * @throws ServiceException is a module exception
	 */
	boolean changePassword(User user, String newPassword, String sessionLogin) throws ServiceException;

	/**
	 * deleting user
	 * @param user to be deleted
	 * @param isUser validates if the command comes from user
	 * @return true if successful, otherwise false
	 * @throws ServiceException is a module exception
	 */
	boolean deleteUser(User user, boolean isUser) throws ServiceException;

	/**
	 * returns all users
	 * @param row required for pagination
	 * @return list of Users
	 * @throws ServiceException is a module exception
	 */
	List<User> getUsers(int row) throws ServiceException;

	/**
	 * amount of users at table shop.users
	 * @return number of Users in table users
	 * @throws ServiceException is a module exception
	 */
	long countUsers() throws ServiceException;

	/**
	 * finds user by login
	 * @param user to be found
	 * @return UserDTO
	 * @throws ServiceException is a module exception
	 */
	UserDTO findUserDTOWithLogin(User user) throws ServiceException;
}

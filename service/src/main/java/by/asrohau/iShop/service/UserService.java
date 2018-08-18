package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface UserService {

	/*
	register new User
	 */
	boolean registration(User user, String password2) throws ServiceException;

	/*
	log in User
	 */
	UserDTO logination(User user) throws ServiceException;

	/*
	finds User Info
	 */
	User findUserWithId(long id) throws ServiceException;

	/*
	general user update
	 */
	boolean updateUser(User user) throws ServiceException;

	/*
	change user's password
	 */
	boolean changePassword(User user, String newPassword) throws ServiceException;

	/*
	deleting user
	 */
	boolean deleteUser(User user, boolean isUser) throws ServiceException;


	List<User> getAllUsers(int row) throws ServiceException; // ArrayList
	long countUsers() throws ServiceException;
	UserDTO findUserDTOWithLogin(User user) throws ServiceException;
}

package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
	
	UserDTO logination(User user) throws ServiceException;
	boolean registration(User user) throws ServiceException;
	boolean changePassword(User user, String newPassword) throws ServiceException;
	boolean deleteUser(User user) throws ServiceException;
	List<User> getAllUsers(int row) throws ServiceException; // ArrayList
	User findUserWithId(User user) throws ServiceException;
	boolean updateUser(User user) throws ServiceException;
	long countUsers() throws ServiceException;
	UserDTO findIdWithLogin(User user) throws ServiceException;
}

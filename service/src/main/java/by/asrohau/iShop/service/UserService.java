package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;

public interface UserService {
	
	boolean validation(User user);
	UserDTO logination(User user) throws ServiceException;
	boolean registration(User user) throws ServiceException;
	boolean changePassword(User user) throws ServiceException;
	boolean deleteUser(User user) throws ServiceException;
	ArrayList<User> getAllUsers(int row) throws ServiceException;
	User findUserWithId(User user) throws ServiceException;
	boolean updateUser(User user) throws ServiceException;
	int countUsers() throws ServiceException;
	UserDTO findIdWithLogin(User user) throws ServiceException;
}

package by.asrohau.iShop.dao;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.ArrayList;

public interface UserDAO {
	
	UserDTO findUserWithLoginAndPassword(User user) throws DAOException;
	UserDTO findUserWithLogin(User user) throws DAOException;
	boolean saveUser(User user) throws DAOException;
	boolean changePassword(User user) throws DAOException;
	boolean deleteUser(User user) throws DAOException;
	User findUserWithId(User user) throws DAOException;
	ArrayList<User> selectAllUsers(int row) throws DAOException;
	boolean updateUser(User user) throws DAOException;
	int countProducts() throws DAOException;
	
}

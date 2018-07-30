package by.asrohau.iShop.dao;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.exception.DAOException;

public interface UserDAO extends AbstractDAO<User> {

	UserDTO findUserWithLogin(User user) throws DAOException;
	User findUserWithId(User user) throws DAOException;

	boolean renewPassword(User user, String newPassword) throws DAOException;

}

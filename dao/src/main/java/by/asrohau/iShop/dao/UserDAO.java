package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import by.asrohau.iShop.dao.exception.DAOException;

public interface UserDAO extends EntityFacadeFootprint<User> {
	UserDTO findUserByLogin(User user) throws DAOException;
	boolean renewPassword(User user, String newPassword) throws DAOException;
}

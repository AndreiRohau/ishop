package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.UserDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

import static by.asrohau.iShop.service.util.ServiceValidator.validation;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	public UserServiceImpl() {
	}

	@Override
	public UserDTO logination(User user) throws ServiceException {
		if (!validation(user)) {
			return null;
		}
		try {
			user = userDAO.find(user);
			return user == null ? null : new UserDTO(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean registration(User user) throws ServiceException {
		if (!validation(user)) {
			return false;
		}
		try {
			return userDAO.save(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean changePassword(User user, String newPassword) throws ServiceException {
		if (!validation(user) || "".equals(newPassword)) {
			return false;
		}
		try {
			return userDAO.renewPassword(user,newPassword);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean deleteUser(User user) throws ServiceException {
		if (!validation(user)) {
			return false;
		}
		try {
			return userDAO.delete(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<User> getAllUsers(int row) throws ServiceException { // ArrayList
		if (!validation(row)){
			return null;
		}
		try {
			return userDAO.findAll(row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User findUserWithId(User user) throws ServiceException {
		if (!validation(user.getId())){
			return null;
		}
		try {
			return userDAO.findUserWithId(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateUser(User user) throws ServiceException {
		if (!validation(user)) {
			return false;
		}
		try {
			return userDAO.update(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public long countUsers() throws ServiceException {
		try {
			return userDAO.countAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UserDTO findIdWithLogin(User user) throws ServiceException {
		if (user.getLogin().trim().equals("")){
			return null;
		}
		try {
			return userDAO.findUserWithLogin(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}

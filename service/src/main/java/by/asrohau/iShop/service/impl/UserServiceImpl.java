package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.entity.User;
import by.asrohau.iShop.entity.UserDTO;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.UserDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

import static by.asrohau.iShop.service.util.ServiceValidator.validation;

public class UserServiceImpl implements UserService {

	private static final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	public UserServiceImpl() {
	}

	@Override
	public boolean registration(User user, String password2) throws ServiceException {
		if (!validation(user)) {
			return false;
		}
		boolean passwordEquals = password2.equals(user.getPassword());
		boolean notAuthorized = "null".equals(user.getRole());
		try {
			return passwordEquals && notAuthorized && userDAO.save(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UserDTO logination(User user) throws ServiceException {
		if (!validation(user)) {
			return null;
		}
		try {
			user = userDAO.find(user);
			return (user == null) ? null : new UserDTO(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User findUserWithId(long id) throws ServiceException {
		if (!validation(id)){
			return null;
		}
		try {
			return userDAO.findOne(id);
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
			return userDAO.changeUserPassword(user,newPassword);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean deleteUser(User user, boolean isUser) throws ServiceException {
		if (!validation(user)) {
			return false;
		}
		try {
			if (isUser) {
				User userCheck = userDAO.findOne(user.getId());
				if (userCheck.getLogin().equals(user.getLogin()) && userCheck.getPassword().equals(user.getPassword())){
					return userDAO.delete(user.getId());
				}
			} else {
				return userDAO.delete(user.getId());
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return false;
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
	public UserDTO findUserDTOWithLogin(User user) throws ServiceException {
		if (user.getLogin().trim().equals("")){
			return null;
		}
		try {
			return userDAO.findUserByLogin(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}

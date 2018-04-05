package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.UserDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {

	private final UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

	public UserServiceImpl() {
	}

	@Override
	public boolean validation(User user) {
		String toCompare = "";
		if (!toCompare.equals(user.getLogin()) && !toCompare.equals(user.getPassword())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public UserDTO logination(User user) throws ServiceException {

		// validation!!! stub
		if (validation(user)) {

			try {
				return userDAO.findUserWithLoginAndPassword(user);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}

		}

		return null;
	}

	@Override
	public boolean registration(User user) throws ServiceException {

		// validation!!! stub
		if (validation(user)) {

			try {
				if (userDAO.findUserWithLogin(user) == null) {
					return userDAO.saveUser(user);
				}

			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}

		return false;
	}

	@Override
	public boolean changePassword(User user) throws ServiceException {

		// validation!!! stub
		if (validation(user) && !user.getNewPassword().trim().equals("")) {

			try {
				if (userDAO.findUserWithLoginAndPassword(user) != null) {
					return userDAO.changePassword(user);
				}
				
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}

		return false;
	}

	@Override
	public boolean deleteUser(User user) throws ServiceException {
		// validation!!! stub
		if (validation(user)) {
			try {
				if (userDAO.findUserWithLoginAndPassword(user) != null) {
					return userDAO.deleteUser(user);
				}
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return false;
	}

	@Override
	public ArrayList<User> getAllUsers(int row) throws ServiceException {
		try {
			return userDAO.selectAllUsers(row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User findUserWithId(User user) throws ServiceException {
		//validation!!! stub

		try {
			return userDAO.findUserWithId(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateUser(User user) throws ServiceException {
		// validation!!! stub
		if (validation(user)) {
			try {
				UserDTO userCheck = userDAO.findUserWithLogin(user);
				return (((userCheck == null) || (userCheck.getId() == user.getId())) && userDAO.updateUser(user));
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return false;
	}

	@Override
	public int countUsers() throws ServiceException {
		try {
			return userDAO.countProducts();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UserDTO findIdWithLogin(User user) throws ServiceException {

		if (!user.getLogin().trim().equals("")){
			try {
				return userDAO.findUserWithLogin(user);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return null;

	}
}

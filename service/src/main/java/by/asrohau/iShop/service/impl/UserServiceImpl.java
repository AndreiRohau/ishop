package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.ClientDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.service.UserService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

	private final ClientDAO clientDAO = DAOFactory.getInstance().getClientDAO();

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
				return clientDAO.findUserWithLoginAndPassword(user);
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
				if (clientDAO.findUserWithLogin(user) == null) {
					return clientDAO.saveUser(user);
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
				if (clientDAO.findUserWithLoginAndPassword(user) != null) {
					return clientDAO.changePassword(user);
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
				if (clientDAO.findUserWithLoginAndPassword(user) != null) {
					return clientDAO.deleteUser(user);
				}
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return false;
	}

	@Override
	public List<User> getAllUsers(int row) throws ServiceException { // ArrayList
		try {
			return clientDAO.selectAllUsers(row);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public User findUserWithId(User user) throws ServiceException {
		//validation!!! stub

		try {
			return clientDAO.findUserWithId(user);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateUser(User user) throws ServiceException {
		// validation!!! stub
		if (validation(user)) {
			try {
				UserDTO userCheck = clientDAO.findUserWithLogin(user);
				return (((userCheck == null) || (userCheck.getId() == user.getId())) && clientDAO.updateUser(user));
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return false;
	}

	@Override
	public int countUsers() throws ServiceException {
		try {
			return clientDAO.countProducts();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public UserDTO findIdWithLogin(User user) throws ServiceException {

		if (!user.getLogin().trim().equals("")){
			try {
				return clientDAO.findUserWithLogin(user);
			} catch (DAOException e) {
				throw new ServiceException(e);
			}
		}
		return null;

	}
}

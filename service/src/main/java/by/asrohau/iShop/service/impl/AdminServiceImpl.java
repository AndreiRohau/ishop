package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.AdminDAO;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.service.AdminService;
import by.asrohau.iShop.service.exception.ServiceException;

public class AdminServiceImpl implements AdminService {

    private final AdminDAO adminDAO = DAOFactory.getInstance().getAdminDAO();

    public AdminServiceImpl() {
    }

    @Override
    public boolean validation(User user) {
        String toCompare = "";
        return !toCompare.equals(user.getLogin()) && !toCompare.equals(user.getPassword());
    }

    @Override
    public UserDTO logination(User user) throws ServiceException {
        // validation!!! stub
        if (validation(user)) {
            try {
                return adminDAO.findUserWithLoginAndPassword(user);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

}

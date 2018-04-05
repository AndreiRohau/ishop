package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.service.exception.ServiceException;

public interface AdminService {
    boolean validation(User user);
    UserDTO logination(User user) throws ServiceException;
}

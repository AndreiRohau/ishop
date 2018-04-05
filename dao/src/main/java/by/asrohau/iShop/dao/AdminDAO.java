package by.asrohau.iShop.dao;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.ArrayList;

public interface AdminDAO {

    UserDTO findUserWithLoginAndPassword(User user) throws DAOException;

}

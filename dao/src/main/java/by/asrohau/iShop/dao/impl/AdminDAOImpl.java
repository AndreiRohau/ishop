package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.AdminDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.util.DAOFinals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AdminDAOImpl extends AbstractDAO<User> implements AdminDAO {

    @Override
    public UserDTO findUserWithLoginAndPassword(User user) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DAOFinals.FIND_ADMIN_WITH_LOGIN_PASSWORD_QUERY.inString)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            UserDTO userDTO = new UserDTO();

            while (resultSet.next()) {
                userDTO.setLogin(resultSet.getString(2));
            }
            preparedStatement.close();
            connection.close();

            if (userDTO.getLogin() != null) {
                return userDTO;
            }
            System.out.println("Did not find Admin with login = " + user.getLogin());//todo
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

//    @Override
//    public boolean save(User user) throws DAOException {
//        return false;
//    }
//
//    @Override
//    public User find(User user) throws DAOException {
//        try (PreparedStatement preparedStatement = getConnection()
//                .prepareStatement(DAOFinals.FIND_ADMIN_WITH_LOGIN_PASSWORD_QUERY.inString)) {
//            preparedStatement.setString(1, user.getLogin());
//            preparedStatement.setString(2, user.getPassword());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            user = new User();
//
//            while (resultSet.next()) {
//                user.setLogin(resultSet.getString(2));
//            }
//            preparedStatement.close();
//            connection.close();
//
//            if (user.getLogin() != null) {
//                return user;
//            }
//            System.out.println("Did not find Admin with login = " + user.getLogin());//todo
//            return null;
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }    }
//
//    @Override
//    public boolean update(User user) throws DAOException {
//        return false;
//    }
//
//    @Override
//    public boolean delete(User user) throws DAOException {
//        return false;
//    }
//
//    @Override
//    public List<User> findAll(User user) throws DAOException {
//        return null;
//    }
//
//    @Override
//    public long countAll() throws DAOException {
//        return 0;
//    }
}


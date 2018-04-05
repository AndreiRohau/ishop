package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.bean.User;
import by.asrohau.iShop.bean.UserDTO;
import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.AdminDAO;
import by.asrohau.iShop.dao.exception.DAOException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOImpl extends AbstractDAO<UserDTO> implements AdminDAO {

    private String FIND_ADMIN_WITH_LOGIN_PASSWORD_QUERY = "SELECT * FROM shop.admins WHERE login = ? AND password = ?";
    private String SELECT_ALL_USERS_QUERY = "SELECT * FROM shop.users";
    private String FIND_USER_WITH_ID_QUERY = "SELECT * FROM shop.users WHERE id = ?";
    private String UPDATE_USER_QUERY = "UPDATE shop.users SET login = ?, password = ? WHERE id = ?";



    @Override
    public UserDTO findUserWithLoginAndPassword(User user) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(FIND_ADMIN_WITH_LOGIN_PASSWORD_QUERY)) {
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
            System.out.println("Did not find Admin with login = " + user.getLogin());
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }




}


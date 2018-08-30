package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.dao.ConnectionPool;
import by.asrohau.iShop.dao.DAOFinals;
import by.asrohau.iShop.dao.UserDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.entity.User;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;

public class UserDAOImplTest {

    /*
    ?????
    необходимо ли проверка еще чего нибудь
    выброс СКЛ исключения?
    рол бэк?
     */
    @Test
    public void save() throws Exception {
        User user = new User("Andy", "12345", "user");

        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        PreparedStatement preparedStatement1 = Mockito.mock(PreparedStatement.class);
        PreparedStatement preparedStatement2 = Mockito.mock(PreparedStatement.class);
        String query1 = "SELECT * FROM shop.users WHERE login = ?";
        String query2 = "INSERT INTO shop.users (login, password) VALUES (?,?)";

        Mockito.when(connectionPool.provide()).thenReturn(connection);

        Mockito.when(connection.prepareStatement(query1)).thenReturn(preparedStatement1);
        Mockito.doNothing().when(preparedStatement1).setString(1, user.getLogin());
        Mockito.when(preparedStatement1.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getLong(1)).thenReturn(0L);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(4)).thenReturn(null);

        Mockito.when(connection.prepareStatement(query2)). thenReturn(preparedStatement2);
        Mockito.doNothing().when(preparedStatement2).setString(1, user.getLogin());
        Mockito.doNothing().when(preparedStatement2).setString(2, user.getPassword());
        Mockito.when(preparedStatement2.executeUpdate()).thenReturn(1);
        Mockito.doNothing().when(connection).commit();
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.save(user);

        Mockito.verify(connection).setAutoCommit(false);
        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(query1);
        Mockito.verify(preparedStatement1, times(1)).setString(1, user.getLogin());
        Mockito.verify(preparedStatement1, times(1)).executeQuery();
        Mockito.verify(resultSet, times(2)).next();
        Mockito.verify(resultSet).getLong(1);
        Mockito.verify(resultSet).getString(2);
        Mockito.verify(resultSet).getString(4);

        Mockito.verify(connection).prepareStatement(query2);
        Mockito.verify(preparedStatement2, times(1)).setString(1, user.getLogin());
        Mockito.verify(preparedStatement2, times(1)).setString(2, user.getPassword());
        Mockito.verify(preparedStatement2).executeUpdate();

        Mockito.verify(connection, times(1)).commit();
        Mockito.verify(connectionPool).retrieve(connection);

    }

    @Test
    public void find() throws Exception {
        User user = new User("Andy", "12345", "user");

        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String query = "SELECT * FROM shop.users WHERE login = ? AND password = ?";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, user.getLogin());
        Mockito.doNothing().when(preparedStatement).setString(2, user.getPassword());
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getLong(1)).thenReturn(anyLong());
        Mockito.when(resultSet.getString(2)).thenReturn(user.getLogin());
        Mockito.when(resultSet.getString(4)).thenReturn(user.getRole());
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.find(user);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(query);
        Mockito.verify(preparedStatement, times(1)).setString(1, user.getLogin());
        Mockito.verify(preparedStatement, times(1)).setString(2, user.getPassword());
        Mockito.verify(preparedStatement, times(1)).executeQuery();
        Mockito.verify(resultSet, times(2)).next();
        Mockito.verify(resultSet).getLong(1);
        Mockito.verify(resultSet).getString(2);
        Mockito.verify(resultSet).getString(4);

        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void findOne() throws Exception {
        long userId = 1L;

        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String query = "SELECT * FROM shop.users WHERE id = ?";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setLong(1, userId);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getString(2)).thenReturn(anyString());
        Mockito.when(resultSet.getString(3)).thenReturn(anyString());
        Mockito.when(resultSet.getString(4)).thenReturn(anyString());
        Mockito.when(resultSet.getLong(1)).thenReturn(userId);
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.findOne(userId);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(query);
        Mockito.verify(preparedStatement, times(1)).setLong(1, userId);
        Mockito.verify(preparedStatement, times(1)).executeQuery();
        Mockito.verify(resultSet, times(2)).next();
        Mockito.verify(resultSet, times(1)).getLong(1);
        Mockito.verify(resultSet, times(1)).getString(2);
        Mockito.verify(resultSet, times(1)).getString(3);
        Mockito.verify(resultSet, times(1)).getString(4);

        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void update() throws Exception {
        User user = new User(1L, "Andy", "12345", "user");

        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        PreparedStatement preparedStatement1 = Mockito.mock(PreparedStatement.class);
        PreparedStatement preparedStatement2 = Mockito.mock(PreparedStatement.class);
        String query1 = "SELECT * FROM shop.users WHERE login = ?";
        String query2 = "UPDATE shop.users SET login = ?, password = ? WHERE id = ?";

        Mockito.when(connectionPool.provide()).thenReturn(connection);

        Mockito.when(connection.prepareStatement(query1)).thenReturn(preparedStatement1);
        Mockito.doNothing().when(preparedStatement1).setString(1, user.getLogin());
        Mockito.when(preparedStatement1.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getLong(1)).thenReturn(0L);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(4)).thenReturn(null);

        Mockito.when(connection.prepareStatement(query2)). thenReturn(preparedStatement2);
        Mockito.doNothing().when(preparedStatement2).setString(1, user.getLogin());
        Mockito.doNothing().when(preparedStatement2).setString(2, user.getPassword());
        Mockito.doNothing().when(preparedStatement2).setLong(3, user.getId());
        Mockito.when(preparedStatement2.executeUpdate()).thenReturn(1);
        Mockito.doNothing().when(connection).commit();
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.update(user);

        Mockito.verify(connection).setAutoCommit(false);
        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(query1);
        Mockito.verify(preparedStatement1, times(1)).setString(1, user.getLogin());
        Mockito.verify(preparedStatement1, times(1)).executeQuery();
        Mockito.verify(resultSet, times(2)).next();
        Mockito.verify(resultSet).getLong(1);
        Mockito.verify(resultSet).getString(2);
        Mockito.verify(resultSet).getString(4);

        Mockito.verify(connection).prepareStatement(query2);
        Mockito.verify(preparedStatement2, times(1)).setString(1, user.getLogin());
        Mockito.verify(preparedStatement2, times(1)).setString(2, user.getPassword());
        Mockito.verify(preparedStatement2, times(1)).setLong(3, user.getId());
        Mockito.verify(preparedStatement2).executeUpdate();

        Mockito.verify(connection, times(1)).commit();
        Mockito.verify(connectionPool).retrieve(connection);

    }

    @Test
    public void delete() throws Exception {
        long userId = 1L;

        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement1 = Mockito.mock(PreparedStatement.class);
        PreparedStatement preparedStatement2 = Mockito.mock(PreparedStatement.class);
        PreparedStatement preparedStatement3 = Mockito.mock(PreparedStatement.class);
        String query1 = "DELETE FROM shop.orders WHERE user = ?";
        String query2 = "DELETE FROM shop.reserve WHERE userId = ?";
        String query3 = "DELETE FROM shop.users WHERE id = ? AND role = 'user'";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.doNothing().when(connection).setAutoCommit(Boolean.FALSE);
        Mockito.when(connection.prepareStatement(query1)).thenReturn(preparedStatement1);
        Mockito.doNothing().when(preparedStatement1).setLong(1, userId);
        Mockito.when(preparedStatement1.executeUpdate()).thenReturn(1);

        Mockito.when(connection.prepareStatement(query2)).thenReturn(preparedStatement1);
        Mockito.doNothing().when(preparedStatement2).setLong(1, userId);
        Mockito.when(preparedStatement2.executeUpdate()).thenReturn(1);

        Mockito.when(connection.prepareStatement(query3)).thenReturn(preparedStatement1);
        Mockito.doNothing().when(preparedStatement3).setLong(1, userId);
        Mockito.when(preparedStatement3.executeUpdate()).thenReturn(1);

        Mockito.doNothing().when(connection).commit();
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.delete(userId);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).setAutoCommit(false);
        Mockito.verify(connection).prepareStatement(query1);
        Mockito.verify(preparedStatement1, times(3)).setLong(1, userId);
        Mockito.verify(preparedStatement1, times(3)).executeUpdate();

        Mockito.verify(connection, times(1)).commit();
        Mockito.verify(connectionPool).retrieve(connection);
    }

    /*
    problem with anyString() can't be stubbed
     */
    @Test
    public void findAll() throws Exception {
        int row = 0;
        final int maxItems = DAOFinals.MAX_ROWS_AT_PAGE;

        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String query = "SELECT * FROM shop.users LIMIT ?,?";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setInt(1, row);
        Mockito.doNothing().when(preparedStatement).setInt(2, maxItems);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getLong(1)).thenReturn(anyLong());
        Mockito.when(resultSet.getString(2)).thenReturn(anyString());
        Mockito.when(resultSet.getString(3)).thenReturn(anyString());
        Mockito.when(resultSet.getString(4)).thenReturn("");
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.findAll(row);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(query);
        Mockito.verify(preparedStatement, times(1)).setInt(1, row);
        Mockito.verify(preparedStatement, times(1)).setInt(2, maxItems);
        Mockito.verify(preparedStatement, times(1)).executeQuery();
        Mockito.verify(resultSet, times(2)).next();
        Mockito.verify(resultSet).getLong(1);
        Mockito.verify(resultSet).getString(2);
        Mockito.verify(resultSet).getString(3);
        Mockito.verify(resultSet).getString(4);

        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void countAll() throws Exception {
        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String query = "SELECT COUNT(*) FROM shop.users";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        //todo ?? return anyLong()
        Mockito.when(resultSet.getLong(1)).thenReturn(15L);

        //todo cant be stubbed
        //Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.countAll();

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(query);
        Mockito.verify(preparedStatement, times(1)).executeQuery();
        Mockito.verify(resultSet, times(1)).next();
        Mockito.verify(resultSet).getLong(1);

        //Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void findUserByLogin() throws Exception {
        User user = new User("Andy", "12345", "user");

        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        ResultSet resultSet = Mockito.mock(ResultSet.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String query = "SELECT * FROM shop.users WHERE login = ?";

        Mockito.when(connectionPool.provide()).thenReturn(connection);

        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, user.getLogin());
        Mockito.when(preparedStatement.executeQuery()).thenReturn(resultSet);
        Mockito.when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
        Mockito.when(resultSet.getLong(1)).thenReturn(0L);
        Mockito.when(resultSet.getString(2)).thenReturn(null);
        Mockito.when(resultSet.getString(4)).thenReturn(null);

        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.findUserByLogin(user);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).prepareStatement(query);
        Mockito.verify(preparedStatement, times(1)).setString(1, user.getLogin());
        Mockito.verify(preparedStatement, times(1)).executeQuery();
        Mockito.verify(resultSet, times(2)).next();
        Mockito.verify(resultSet).getLong(1);
        Mockito.verify(resultSet).getString(2);
        Mockito.verify(resultSet).getString(4);

        Mockito.verify(connectionPool).retrieve(connection);
    }

    @Test
    public void changeUserPassword() throws Exception {
        User user = new User("Andy", "12345", "user");
        String newPassword = "new12345";

        ConnectionPool connectionPool = Mockito.mock(ConnectionPool.class);
        Connection connection = Mockito.mock(Connection.class);
        PreparedStatement preparedStatement = Mockito.mock(PreparedStatement.class);
        String query = "UPDATE shop.users SET password = ? WHERE login = ? AND password = ?";

        Mockito.when(connectionPool.provide()).thenReturn(connection);
        Mockito.doNothing().when(connection).setAutoCommit(false);
        Mockito.when(connection.prepareStatement(query)).thenReturn(preparedStatement);
        Mockito.doNothing().when(preparedStatement).setString(1, newPassword);
        Mockito.doNothing().when(preparedStatement).setString(2, user.getLogin());
        Mockito.doNothing().when(preparedStatement).setString(3, user.getPassword());
        Mockito.when(preparedStatement.executeUpdate()).thenReturn(1);
        Mockito.doNothing().when(connection).commit();
        Mockito.doNothing().when(connectionPool).retrieve(connection);

        UserDAO userDAO = new UserDAOImpl(connectionPool);
        userDAO.changeUserPassword(user, newPassword);

        Mockito.verify(connectionPool).provide();
        Mockito.verify(connection).setAutoCommit(false);
        Mockito.verify(connection).prepareStatement(query);
        Mockito.verify(preparedStatement, times(1)).setString(1, newPassword);
        Mockito.verify(preparedStatement, times(1)).setString(2, user.getLogin());
        Mockito.verify(preparedStatement, times(1)).setString(3, user.getPassword());
        Mockito.verify(preparedStatement, times(1)).executeUpdate();

        Mockito.verify(connection, times(1)).commit();
        Mockito.verify(connectionPool).retrieve(connection);
    }

}
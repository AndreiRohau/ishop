package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.dao.AbstractConnection;
import by.asrohau.iShop.dao.OrderDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.dao.util.DAOFinals.*;


public class OrderDAOImpl extends AbstractConnection implements OrderDAO {

    private final static Logger logger = Logger.getLogger(OrderDAOImpl.class);

    private PreparedStatement preparedStatement = null;
    private Connection connection = null;
    private ResultSet resultSet = null;

    /*
        create new Order
         */
    @Override
    public boolean save(Order order) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SAVE_NEW_ORDER_QUERY.inString);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setString(2, order.getProductIds());
            preparedStatement.setString(3, order.getUserAddress());
            preparedStatement.setString(4, order.getUserPhone());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setDate(6, order.getDateCreated());
            int result = preparedStatement.executeUpdate();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    find Order by id
     */
    @Override
    public Order find(Order order) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ORDER_WITH_ID_QUERY.inString);
            preparedStatement.setLong(1, order.getId());

            resultSet = preparedStatement.executeQuery();
            Order foundOrder = new Order();

            while (resultSet.next()) {
                foundOrder.setId(resultSet.getLong(1));
                foundOrder.setUserId(resultSet.getLong(2));
                foundOrder.setProductIds(resultSet.getString(3));
                foundOrder.setUserAddress(resultSet.getString(4));
                foundOrder.setUserPhone(resultSet.getString(5));
                foundOrder.setStatus(resultSet.getString(6));
                foundOrder.setDateCreated(resultSet.getDate(7));
            }

            if (foundOrder.getStatus() != null) {
                return foundOrder;
            }
            logger.info(CANNOT_IDENTIFY_ORDER_BY_ID.inString);
            return null;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    updates list of products in Order
     */
    @Override
    public boolean update(Order order) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ORDERS_PRODUCTS_QUERY.inString);
            preparedStatement.setString(1, order.getProductIds());
            preparedStatement.setLong(2, order.getId());

            int result = preparedStatement.executeUpdate();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    delete Order by Id
     */
    @Override
    public boolean delete(Order order) throws DAOException {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID_QUERY.inString);
            preparedStatement.setLong(1, order.getId());

            int result = preparedStatement.executeUpdate();
            connection.commit();
            return (result != 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException(EXCEPTION_WHILE_ROLL_BACK.inString, ex);
            }
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }

    }

    /*
    find all Orders in schema:shop table:orders
     */
    @Override
    public List<Order> findAll(int row) throws DAOException {
            try {
                connection = getConnection();
                preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS_QUERY.inString);
                preparedStatement.setInt(1, row);
                preparedStatement.setInt(2, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
                List<Order> productList = new ArrayList<>();
                resultSet = preparedStatement.executeQuery();
                long orderId;
                long userId;
                String productIds;
                String userAddress;
                String userPhone;
                String foundStatus;
                Date dateCreated;
                Order order;
                while (resultSet.next()) {
                    orderId = resultSet.getLong(1);
                    userId = resultSet.getLong(2);
                    productIds = resultSet.getString(3);
                    userAddress =  resultSet.getString(4);
                    userPhone =  resultSet.getString(5);
                    foundStatus =  resultSet.getString(6);
                    dateCreated =  resultSet.getDate(7);
                    order = new Order(orderId, userId, productIds, userAddress, userPhone, foundStatus, dateCreated);
                    productList.add(order);
                }
                return productList;
            } catch (SQLException e) {
                throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
            } finally {
                close(resultSet, preparedStatement, connection);
            }
    }

    /*
    count All Orders in schema:shop table:orders
     */
    @Override
    public long countAll() throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_All_ORDERS_QUERY.inString);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    delete all Orders of a certain User by userId
     */
    @Override
    public boolean deleteAllOrders(long userId) throws DAOException {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ALL_ORDERS_WITH_USER_ID_QUERY.inString);
            preparedStatement.setLong(1, userId);

            int result = preparedStatement.executeUpdate();
            connection.commit();
            return (result != 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException(EXCEPTION_WHILE_ROLL_BACK.inString, ex);
            }
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    finding out the amount of all Orders sorting by Status
     */
    @Override
    public long countOrders(String status) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_All_ORDERS_STATUS_QUERY.inString);
            preparedStatement.setString(1, status);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    finds all Orders by Status
     */
    @Override
    public List<Order> findAllOrders(int row, String status) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_ORDERS_STATUS_QUERY.inString);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            List<Order> productList = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            long orderId;
            long userId;
            String productIds;
            String userAddress;
            String userPhone;
            String foundStatus;
            Date dateCreated;
            Order order;
            while (resultSet.next()) {
                orderId = resultSet.getLong(1);
                userId = resultSet.getLong(2);
                productIds = resultSet.getString(3);
                userAddress =  resultSet.getString(4);
                userPhone =  resultSet.getString(5);
                foundStatus =  resultSet.getString(6);
                dateCreated =  resultSet.getDate(7);
                order = new Order(orderId, userId, productIds, userAddress, userPhone, foundStatus, dateCreated);
                productList.add(order);
            }
            return productList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    changes certain Order's status
    */
    @Override
    public boolean update(Order order, String status) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_SET_ORDER_STATUS_QUERY.inString);
            preparedStatement.setString(1, status);
            preparedStatement.setLong(2, order.getId());

            int result = preparedStatement.executeUpdate();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    finding out the amount of all Orders of a certain User by userId
     */
    @Override
    public long countUserOrders(long userId) throws DAOException {
        try {
            preparedStatement = getConnection().prepareStatement(COUNT_USER_ORDERS_QUERY.inString);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    finding out the amount of all OrdersByStatus of a certain User by userId
     */
    @Override
    public long countUserOrdersByStatus(Order order) throws DAOException {
        try {
            preparedStatement = getConnection().prepareStatement(COUNT_USER_ORDERS_BY_STATUS_QUERY.inString);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setString(2, order.getStatus());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    finds all Orders of a certain User by userId
     */
    @Override
    public List<Order> findUserOrders(int row, long userId) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_ORDERS_QUERY.inString);
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            List<Order> orderList = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            long orderId;
            userId = 0;
            String productIds;
            String userAddress;
            String userPhone;
            String status;
            Date dateCreated;
            Order order;
            while (resultSet.next()) {
                orderId = resultSet.getLong(1);
                userId = resultSet.getLong(2);
                productIds = resultSet.getString(3);
                userAddress =  resultSet.getString(4);
                userPhone =  resultSet.getString(5);
                status =  resultSet.getString(6);
                dateCreated = resultSet.getDate(7);
                order = new Order(orderId, userId, productIds, userAddress, userPhone, status, dateCreated);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    /*
    finds all OrdersByStatus of a certain User by userId
     */
    @Override
    public List<Order> findUserOrdersByStatus(int row, Order order) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_USER_ORDERS_BY_STATUS_QUERY.inString);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setString(2, order.getStatus());
            preparedStatement.setInt(3, row);
            preparedStatement.setInt(4, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            List<Order> orderList = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            long orderId;
            long userId = 0;
            String productIds;
            String userAddress;
            String userPhone;
            String status;
            Date dateCreated;
            Order foundOrder;
            while (resultSet.next()) {
                orderId = resultSet.getLong(1);
                userId = resultSet.getLong(2);
                productIds = resultSet.getString(3);
                userAddress =  resultSet.getString(4);
                userPhone =  resultSet.getString(5);
                status =  resultSet.getString(6);
                dateCreated = resultSet.getDate(7);
                foundOrder = new Order(orderId, userId, productIds, userAddress, userPhone, status, dateCreated);
                orderList.add(foundOrder);
            }
            return orderList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }
}

package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.OrderDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static by.asrohau.iShop.dao.util.DAOFinals.MAX_ROWS_AT_PAGE;


public class OrderDAOImpl extends AbstractDAO implements OrderDAO {

    private final static Logger logger = LoggerFactory.getLogger(OrderDAOImpl.class);
    /*
    OrderDAO queries
     */
    private static final String SAVE_NEW_ORDER_QUERY = "INSERT INTO shop.orders (user, products, address, phone, status, dateCreated) VALUES (?,?,?,?,?,?)";
    private static final String FIND_ORDER_BY_ID_USER_PRODUCTS_ADDRESS_PHONE_QUERY = "SELECT * FROM shop.orders WHERE id = ? AND user = ? AND products = ? AND address = ? AND phone = ?";
    private static final String FIND_ORDER_BY_ID_QUERY = "SELECT * FROM shop.orders WHERE id = ?";
    private static final String FIND_ORDERS_LIMIT_QUERY = "SELECT * FROM shop.orders LIMIT ?, ?";
    private static final String FIND_ORDERS_BY_STATUS_LIMIT_QUERY = "SELECT * FROM shop.orders WHERE status = ? LIMIT ?, ?";
    private static final String FIND_ORDERS_BY_USER_ID_LIMIT_QUERY = "SELECT * FROM shop.orders WHERE user = ? LIMIT ?, ?";
    private static final String FIND_ORDERS_BY_USER_ID_AND_STATUS_LIMIT_QUERY = "SELECT * FROM shop.orders WHERE user = ? AND status = ? LIMIT ?, ?";
    private static final String COUNT_ORDERS_QUERY = "SELECT COUNT(*) FROM shop.orders";
    private static final String COUNT_ORDERS_BY_STATUS_QUERY = "SELECT COUNT(*) FROM shop.orders WHERE status = ?";
    private static final String COUNT_ORDERS_BY_USER_ID_QUERY = "SELECT COUNT(*) FROM shop.orders WHERE user = ?";
    private static final String COUNT_ORDERS_BY_USER_ID_AND_STATUS_QUERY = "SELECT COUNT(*) FROM shop.orders WHERE user = ? AND status = ?";
    private static final String UPDATE_ORDER_STATUS_QUERY = "UPDATE shop.orders SET status = ? WHERE id = ?";
    private static final String UPDATE_ORDER_PRODUCTS_QUERY = "UPDATE shop.orders SET products = ? WHERE id = ?";
    private static final String DELETE_ORDERS_BY_USER_ID_QUERY = "DELETE FROM shop.orders WHERE user = ?";
    private static final String DELETE_ORDER_BY_ID_QUERY = "DELETE FROM shop.orders WHERE id = ?";

    /*
    create new Order - this is a transactional method with roleback
     */
    @Override
    public boolean save(Order order) throws DAOException {
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SAVE_NEW_ORDER_QUERY);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setString(2, order.getProductIds());
            preparedStatement.setString(3, order.getUserAddress());
            preparedStatement.setString(4, order.getUserPhone());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setDate(6, order.getDateCreated());
            int result = preparedStatement.executeUpdate();

            preparedStatement2 = connection.prepareStatement("DELETE FROM shop.reserve WHERE userId = ?");
            preparedStatement2.setLong(1, order.getUserId());
            int result2 = preparedStatement2.executeUpdate();

            connection.commit();

            return (result > 0) && (result2 > 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Error during rollback", ex);
            }
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(null, preparedStatement);
            close(null, preparedStatement2);
            returnConnection(connection);
        }
    }

    /*
    find Order equals
     */
    @Override
    public Order find(Order order) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID_USER_PRODUCTS_ADDRESS_PHONE_QUERY);
            preparedStatement.setLong(1, order.getId());
            preparedStatement.setLong(2, order.getUserId());
            preparedStatement.setString(3, order.getProductIds());
            preparedStatement.setString(4, order.getUserAddress());
            preparedStatement.setString(5, order.getUserPhone());
            resultSet = preparedStatement.executeQuery();
            order = new Order();

            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
                order.setUserId(resultSet.getLong(2));
                order.setProductIds(resultSet.getString(3));
                order.setUserAddress(resultSet.getString(4));
                order.setUserPhone(resultSet.getString(5));
            }

            if (order.getId() != 0) {
                return order;
            }
            logger.info("Can not identify Order");
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }
    
    /*
    find Order by id
     */
    @Override
    public Order findOne(long id) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
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
            logger.info("Can not identify Order with id");
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    /*
    updates list of products in Order
     */
    @Override
    public boolean update(Order order) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_PRODUCTS_QUERY);
            preparedStatement.setString(1, order.getProductIds());
            preparedStatement.setLong(2, order.getId());

            int result = preparedStatement.executeUpdate();
            return (result > 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Error during rollback", ex);
            }
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(null, preparedStatement);
            returnConnection(connection);
        }
    }

    /*
    delete Order by Id
     */
    @Override
    public boolean delete(long id) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_ID_QUERY);
            preparedStatement.setLong(1, id);

            int result = preparedStatement.executeUpdate();
            connection.commit();
            return (result != 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Error during rollback", ex);
            }
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(null, preparedStatement);
            returnConnection(connection);
        }
    }

    /*
    find all Orders in schema:shop table:orders
     */
    @Override
    public List<Order> findAll(int row) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_ORDERS_LIMIT_QUERY);
            preparedStatement.setInt(1, row);
            preparedStatement.setInt(2, MAX_ROWS_AT_PAGE);
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
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    /*
    count All Orders in schema:shop table:orders
     */
    @Override
    public long countAll() throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_ORDERS_QUERY);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public boolean deleteUserOrders(long userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ORDERS_BY_USER_ID_QUERY);
            preparedStatement.setLong(1, userId);

            int result = preparedStatement.executeUpdate();
            connection.commit();
            return (result != 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Error during rollback", ex);
            }
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(null, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public long countOrders(String status) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_ORDERS_BY_STATUS_QUERY);
            preparedStatement.setString(1, status);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public List<Order> findOrders(int row, String status) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_STATUS_LIMIT_QUERY);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, MAX_ROWS_AT_PAGE);
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
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public boolean updateOrderStatus(long id, String status) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS_QUERY);
            preparedStatement.setString(1, status);
            preparedStatement.setLong(2, id);
            int result = preparedStatement.executeUpdate();
            connection.commit();
            return (result > 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Error during rollback", ex);
            }
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(null, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public long countUserOrders(long userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_ORDERS_BY_USER_ID_QUERY);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public long countUserOrdersByStatus(Order order) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_ORDERS_BY_USER_ID_AND_STATUS_QUERY);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setString(2, order.getStatus());
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public List<Order> findUserOrders(int row, long userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID_LIMIT_QUERY);
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, MAX_ROWS_AT_PAGE);
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
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public List<Order> findUserOrdersByStatus(int row, Order order) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_ORDERS_BY_USER_ID_AND_STATUS_LIMIT_QUERY);
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setString(2, order.getStatus());
            preparedStatement.setInt(3, row);
            preparedStatement.setInt(4, MAX_ROWS_AT_PAGE);
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
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }
}

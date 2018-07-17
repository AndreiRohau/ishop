package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.AbstractConnection;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.OrderDAO;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static by.asrohau.iShop.dao.util.DAOFinals.*;


public class OrderDAOImpl extends AbstractConnection implements OrderDAO {

    private final static Logger logger = Logger.getLogger(OrderDAOImpl.class);

    /*
    create new Order
     */
    @Override
    public boolean save(Order order) throws DAOException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(SAVE_NEW_ORDER_QUERY.inString);
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setString(2, order.getProductIds());
            preparedStatement.setString(3, order.getUserAddress());
            preparedStatement.setString(4, order.getUserPhone());
            preparedStatement.setString(5, order.getStatus());
            int result = preparedStatement.executeUpdate();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();

                    if(connection != null) {
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    find Order by id
     */
    @Override
    public Order find(Order order) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(SELECT_ORDER_WITH_ID_QUERY.inString);
            preparedStatement.setInt(1, order.getId());

            resultSet = preparedStatement.executeQuery();
            Order foundOrder = new Order();

            while (resultSet.next()) {
                foundOrder.setId(resultSet.getInt(1));
                foundOrder.setUserId(resultSet.getInt(2));
                foundOrder.setProductIds(resultSet.getString(3));
                foundOrder.setUserAddress(resultSet.getString(4));
                foundOrder.setUserPhone(resultSet.getString(5));
                foundOrder.setStatus(resultSet.getString(6));
            }

            if (foundOrder.getStatus() != null) {
                return foundOrder;
            }
            logger.info(CANNOT_IDENTIFY_ORDER_BY_ID.inString);
            return null;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    updates list of products in Order
     */
    @Override
    public boolean update(Order order) throws DAOException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(UPDATE_ORDERS_PRODUCTS_QUERY.inString);
            preparedStatement.setString(1, order.getProductIds());
            preparedStatement.setInt(2, order.getId());

            int result = preparedStatement.executeUpdate();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();

                    if(connection != null) {
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    delete Order by Id
     */
    @Override
    public boolean delete(Order order) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_NEW_ORDER_QUERY.inString);
            preparedStatement.setInt(1, order.getId());

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
            try {
                if(preparedStatement != null){
                    preparedStatement.close();

                    if(connection != null) {
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }

    }

    /*
    unfortunately never used
     */
    @Override
    public List<Order> findAll(int row) throws DAOException {
        return null;
    }

    /*
    unfortunately never used
     */
    @Override
    public long countAll() throws DAOException {
        return 0;
    }

    /*
    create new Reservation
     */
    @Override
    public boolean save(Reserve reserve) throws DAOException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(SAVE_RESERVATION_QUERY.inString);
            preparedStatement.setInt(1, reserve.getrUserId());
            preparedStatement.setInt(2, reserve.getrProductId());

            int result = preparedStatement.executeUpdate();

            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();

                    if(connection != null) {
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    finding out reservations of a certain user for a certain page
     */
    @Override
    public List<Product> findAllReserved(int userId, int row) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(SELECT_ALL_RESERVED_QUERY.inString);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            List<Product> productList = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            int reserveId;
            int productId;
            Product product;
            while (resultSet.next()) {
                reserveId = resultSet.getInt(1);
                productId = resultSet.getInt(3);
                product = new Product(productId, reserveId);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    finding out the amount of all reservations of a certain user by userId
     */
    @Override
    public int countReserved(int userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(COUNT_RESERVED_QUERY.inString);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    delete single reservation by its Id
     */
    @Override
    public boolean delete(int reserveId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_RESERVED_QUERY.inString);
            preparedStatement.setInt(1, reserveId);

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
            try {
                if(preparedStatement != null){
                    preparedStatement.close();

                    if(connection != null) {
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    get Ids of all user's Reservations
     */
    @Override
    public List<Integer> findAllReservedIds(int userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(SELECT_ALL_RESERVED_IDS_QUERY.inString);
            preparedStatement.setInt(1, userId);
            List<Integer> productIdsList = new LinkedList<>();
            resultSet = preparedStatement.executeQuery();
            int productId;
            while (resultSet.next()) {
                productId = resultSet.getInt(3);
                productIdsList.add(productId);
            }
            return productIdsList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
        delete all Reservations of a certain User by userId
     */
    @Override
    public boolean deleteAllReserved(int userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_ALL_RESERVED_QUERY.inString);
            preparedStatement.setInt(1, userId);

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
            try {
                if(preparedStatement != null){
                    preparedStatement.close();

                    if(connection != null) {
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    delete all Orders of a certain User by userId
     */
    @Override
    public boolean deleteAllOrders(int userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_ALL_ORDERS_WITH_USER_ID_QUERY.inString);
            preparedStatement.setInt(1, userId);

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
            try {
                if(preparedStatement != null){
                    preparedStatement.close();

                    if(connection != null) {
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    finding out the amount of all Orders sorting by Status
     */
    @Override
    public int countOrders(String status) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(COUNT_All_ORDERS_QUERY.inString);
            preparedStatement.setString(1, status);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    finds all Orders by Status
     */
    @Override
    public List<Order> findAllOrders(int row, String status) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(SELECT_ALL_ORDERS_QUERY.inString);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            List<Order> productList = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            int orderId;
            int userId;
            String productIds;
            String userAddress;
            String userPhone;
            String foundStatus;
            Order order;
            while (resultSet.next()) {

                orderId = resultSet.getInt(1);
                userId = resultSet.getInt(2);
                productIds = resultSet.getString(3);
                userAddress =  resultSet.getString(4);
                userPhone =  resultSet.getString(5);
                foundStatus =  resultSet.getString(6);
                order = new Order(orderId, userId, productIds, userAddress, userPhone, foundStatus);
                productList.add(order);
            }
            return productList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    changes certain Order's status
    */
    @Override
    public boolean update(Order order, String status) throws DAOException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getConnection().prepareStatement(UPDATE_SET_ORDER_STATUS_QUERY.inString);
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, order.getId());

            int result = preparedStatement.executeUpdate();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(preparedStatement != null){
                    preparedStatement.close();

                    if(connection != null) {
                        connection.close();
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    finds all active Orders for a certain page
     */
    @Override
    public List<Order> findAllActiveOrders(int row) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(SELECT_ALL_ACTIVE_ORDERS_QUERY.inString);
            preparedStatement.setInt(1, row);
            preparedStatement.setInt(2, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            List<Order> orderList = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            int orderId;
            int userId;
            String productIds;
            String userAddress;
            String userPhone;
            String status;
            Order order;
            while (resultSet.next()) {
                orderId = resultSet.getInt(1);
                userId = resultSet.getInt(2);
                productIds = resultSet.getString(3);
                userAddress =  resultSet.getString(4);
                userPhone =  resultSet.getString(5);
                status =  resultSet.getString(6);
                order = new Order(orderId, userId, productIds, userAddress, userPhone, status);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    finds all successful Orders for a certain page
     */
    @Override
    public List<Order> findAllSuccessOrders(int row) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(SELECT_ALL_SUCCESS_ORDERS_QUERY.inString);
            preparedStatement.setInt(1, row);
            preparedStatement.setInt(2, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            ArrayList<Order> orderList = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            int orderId;
            int userId;
            String productIds;
            String userAddress;
            String userPhone;
            String foundStatus;
            Order order;
            while (resultSet.next()) {

                orderId = resultSet.getInt(1);
                userId = resultSet.getInt(2);
                productIds = resultSet.getString(3);
                userAddress =  resultSet.getString(4);
                userPhone =  resultSet.getString(5);
                foundStatus =  resultSet.getString(6);
                order = new Order(orderId, userId, productIds, userAddress, userPhone, foundStatus);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    finding out the amount of all Orders of a certain User by userId
     */
    @Override
    public int countClientsOrders(int userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(COUNT_All_CLIENTS_ORDERS_QUERY.inString);
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();

            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }

    /*
    finds all Orders of a certain User by userId
     */
    @Override
    public List<Order> findAllClientsOrders(int row, int userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = getConnection().prepareStatement(SELECT_ALL_CLIENTS_ORDERS_QUERY.inString);
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            List<Order> orderList = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            int orderId;
            userId = 0;
            String productIds;
            String userAddress;
            String userPhone;
            String status;
            Order order;
            while (resultSet.next()) {
                orderId = resultSet.getInt(1);
                userId = resultSet.getInt(2);
                productIds = resultSet.getString(3);
                userAddress =  resultSet.getString(4);
                userPhone =  resultSet.getString(5);
                status =  resultSet.getString(6);
                order = new Order(orderId, userId, productIds, userAddress, userPhone, status);
                orderList.add(order);
            }
            return orderList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            try {
                if(resultSet != null) {
                    resultSet.close();

                    if(preparedStatement != null){
                        preparedStatement.close();

                        if(connection != null) {
                            connection.close();
                        }
                    }
                }
            } catch (SQLException e) {
                throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
            }
        }
    }
}

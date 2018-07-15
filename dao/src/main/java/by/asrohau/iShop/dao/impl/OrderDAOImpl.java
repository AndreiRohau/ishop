package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.OrderDAO;
import by.asrohau.iShop.dao.util.DAOFinals;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;


public class OrderDAOImpl extends AbstractDAO<Reserve> implements OrderDAO {

//    private String SAVE_RESERVATION_QUERY = "INSERT INTO shop.reserve (user_id, product_id) VALUES (?,?)";
//    private String SELECT_ALL_RESERVED_QUERY = "SELECT * FROM shop.reserve WHERE user_id = ? LIMIT ?, ?";
//    private String COUNT_RESERVED_QUERY = "SELECT COUNT(*) FROM shop.reserve WHERE user_id = ?";
//    private String DELETE_RESERVED_QUERY = "DELETE FROM shop.reserve WHERE id = ?";
//    private String SELECT_ALL_RESERVED_IDS_QUERY = "SELECT * FROM shop.reserve WHERE user_id = ?";
//    private String DELETE_ALL_RESERVED_QUERY = "DELETE FROM shop.reserve WHERE user_id = ?";
//    private String DELETE_ALL_ORDERS_WITH_USER_ID_QUERY = "DELETE FROM shop.orders WHERE user = ?";
//    private String SAVE_NEW_ORDER_QUERY = "INSERT INTO shop.orders (user, products, address, phone, status) VALUES (?,?,?,?,?)";
//    private String COUNT_All_ORDERS_QUERY = "SELECT COUNT(*) FROM shop.orders WHERE status = ?";
//    private String SELECT_ALL_ORDERS_QUERY = "SELECT * FROM shop.orders WHERE status = ? LIMIT ?, ?";
//    private String DELETE_NEW_ORDER_QUERY = "DELETE FROM shop.orders WHERE id = ?";
//    private String UPDATE_SET_ORDER_STATUS_QUERY = "UPDATE shop.orders SET status = ? WHERE id = ?";
//    private String SELECT_ORDER_WITH_ID_QUERY = "SELECT * FROM shop.orders WHERE id = ?";
//    private String UPDATE_ORDERS_PRODUCTS_QUERY = "UPDATE shop.orders SET products = ? WHERE id = ?";
//    private String SELECT_ALL_ACTIVE_ORDERS_QUERY = "SELECT * FROM shop.orders WHERE status = \'active\' LIMIT ?, ?";
//    private String SELECT_ALL_SUCCESS_ORDERS_QUERY = "SELECT * FROM shop.orders WHERE status = \'success\' LIMIT ?, ?";
//    private String COUNT_All_CLIENTS_ORDERS_QUERY = "SELECT COUNT(*) FROM shop.orders WHERE user = ?";
//    private String SELECT_ALL_CLIENTS_ORDERS_QUERY = "SELECT * FROM shop.orders WHERE user = ? LIMIT ?, ?";

    @Override
    public boolean saveNewReservation(Reserve reserve) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.SAVE_RESERVATION_QUERY.inString)) {
            statement.setInt(1, reserve.getrUserId());
            statement.setInt(2, reserve.getrProductId());

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public ArrayList<Product> selectAllReserved(int user_id, int row) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DAOFinals.SELECT_ALL_RESERVED_QUERY.inString)) {
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, 15);
            ArrayList<Product> productList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            int reserve_id;
            int product_id;
            Product product;
            while (resultSet.next()) {

                reserve_id = resultSet.getInt(1);
                product_id = resultSet.getInt(3);
                product = new Product(product_id, reserve_id);
                productList.add(product);
            }
            preparedStatement.close();
            connection.close();
            return productList;

        } catch (SQLException e) {
            System.out.println("dao exception while get all reserved");
            throw new DAOException(e);
        }
    }

    @Override
    public int countReserved(int user_id) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.COUNT_RESERVED_QUERY.inString)) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int i = resultSet.getInt(1);
            statement.close();
            connection.close();
            return i;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean deleteReserved(int reserveId) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.DELETE_RESERVED_QUERY.inString)) {
            statement.setInt(1, reserveId);

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result != 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public LinkedList<Integer> selectAllReservedIds(int user_id) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DAOFinals.SELECT_ALL_RESERVED_IDS_QUERY.inString)) {
            preparedStatement.setInt(1, user_id);
            LinkedList<Integer> productIdsList = new LinkedList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            int product_id;
            while (resultSet.next()) {
                product_id = resultSet.getInt(3);
                productIdsList.add(product_id);
            }
            preparedStatement.close();
            connection.close();
            return productIdsList;

        } catch (SQLException e) {
            System.out.println("dao exception while get all reserved IDs");
            throw new DAOException(e);
        }
    }

    @Override
    public boolean deleteAllReserved(int user_id) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.DELETE_ALL_RESERVED_QUERY.inString)) {
            statement.setInt(1, user_id);

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result != 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean deleteAllOrdersWithUserID(int user_id) throws DAOException {
        try (PreparedStatement statement =
                     getConnection().prepareStatement(DAOFinals.DELETE_ALL_ORDERS_WITH_USER_ID_QUERY.inString)) {
            statement.setInt(1, user_id);

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result != 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean insertNewOrder(Order order) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.SAVE_NEW_ORDER_QUERY.inString)) {
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getProductIds());
            statement.setString(3, order.getUserAddress());
            statement.setString(4, order.getUserPhone());
            statement.setString(5, order.getStatus());

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public int countOrders(String status) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.COUNT_All_ORDERS_QUERY.inString)) {
            statement.setString(1, status);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int i = resultSet.getInt(1);
            statement.close();
            connection.close();
            return i;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public ArrayList<Order> selectAllOrders(int row, String status) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DAOFinals.SELECT_ALL_ORDERS_QUERY.inString)) {

            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, 15);
            ArrayList<Order> productList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            int order_id;
            int user_id;
            String productIDs;
            String user_address;
            String user_phone;
            String found_status;
            Order order;
            while (resultSet.next()) {

                order_id = resultSet.getInt(1);
                user_id = resultSet.getInt(2);
                productIDs = resultSet.getString(3);
                user_address =  resultSet.getString(4);
                user_phone =  resultSet.getString(5);
                found_status =  resultSet.getString(6);
                order = new Order(order_id, user_id, productIDs, user_address, user_phone, found_status);
                productList.add(order);
            }
            preparedStatement.close();
            connection.close();
            return productList;

        } catch (SQLException e) {
            System.out.println("dao exception while get all orders, status - " + status);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean deleteOrder(int orderId) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.DELETE_NEW_ORDER_QUERY.inString)) {
            statement.setInt(1, orderId);

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result != 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean updateOrderSetStatus(int orderId, String status) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.UPDATE_SET_ORDER_STATUS_QUERY.inString)) {
            statement.setString(1, status);
            statement.setInt(2, orderId);

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public Order selectOrderWithID(int orderId) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DAOFinals.SELECT_ORDER_WITH_ID_QUERY.inString)) {
            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();
            Order foundOrder = new Order();

            while (resultSet.next()) {
                foundOrder.setId(resultSet.getInt(1));
                foundOrder.setUserId(resultSet.getInt(2));
                foundOrder.setProductIds(resultSet.getString(3));
                foundOrder.setUserAddress(resultSet.getString(4));
                foundOrder.setUserPhone(resultSet.getString(5));
                foundOrder.setStatus(resultSet.getString(6));
            }
            preparedStatement.close();
            connection.close();

            if (foundOrder.getStatus() != null) {
                System.out.println("foundOrder.getStatus() != null : " + foundOrder.toString());
                return foundOrder;
            }
            System.out.println("Did not find = " + foundOrder.toString());
            return null;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public boolean updateOrdersProducts(Order order) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.UPDATE_ORDERS_PRODUCTS_QUERY.inString)) {
            statement.setString(1, order.getProductIds());
            statement.setInt(2, order.getId());

            int result = statement.executeUpdate();
            statement.close();
            connection.close();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public ArrayList<Order> selectAllActiveOrders(int row) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DAOFinals.SELECT_ALL_ACTIVE_ORDERS_QUERY.inString)) {

            preparedStatement.setInt(1, row);
            preparedStatement.setInt(2, 15);
            ArrayList<Order> orderList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            int order_id;
            int user_id;
            String productIDs;
            String user_address;
            String user_phone;
            String status;
            Order order;
            while (resultSet.next()) {

                order_id = resultSet.getInt(1);
                user_id = resultSet.getInt(2);
                productIDs = resultSet.getString(3);
                user_address =  resultSet.getString(4);
                user_phone =  resultSet.getString(5);
                status =  resultSet.getString(6);
                order = new Order(order_id, user_id, productIDs, user_address, user_phone, status);
                orderList.add(order);
            }
            preparedStatement.close();
            connection.close();
            return orderList;

        } catch (SQLException e) {
            System.out.println("dao exception while get all active orders");
            throw new DAOException(e);
        }
    }

    @Override
    public ArrayList<Order> selectAllSuccessOrders(int row) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DAOFinals.SELECT_ALL_SUCCESS_ORDERS_QUERY.inString)) {

            preparedStatement.setInt(1, row);
            preparedStatement.setInt(2, 15);
            ArrayList<Order> orderList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            int order_id;
            int user_id;
            String productIDs;
            String user_address;
            String user_phone;
            String status;
            Order order;
            while (resultSet.next()) {

                order_id = resultSet.getInt(1);
                user_id = resultSet.getInt(2);
                productIDs = resultSet.getString(3);
                user_address =  resultSet.getString(4);
                user_phone =  resultSet.getString(5);
                status =  resultSet.getString(6);
                order = new Order(order_id, user_id, productIDs, user_address, user_phone, status);
                orderList.add(order);
            }
            preparedStatement.close();
            connection.close();
            return orderList;

        } catch (SQLException e) {
            System.out.println("dao exception while get all SUCCESS orders");
            throw new DAOException(e);
        }
    }

    @Override
    public int countClientsOrders(int user_id) throws DAOException {
        try (PreparedStatement statement = getConnection().prepareStatement(DAOFinals.COUNT_All_CLIENTS_ORDERS_QUERY.inString)) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();

            resultSet.next();
            int i = resultSet.getInt(1);
            statement.close();
            connection.close();
            return i;
        } catch (SQLException e) {
            throw new DAOException(e);
        }
    }

    @Override
    public ArrayList<Order> selectAllClientsOrders(int row, int user_id) throws DAOException {
        try (PreparedStatement preparedStatement = getConnection()
                .prepareStatement(DAOFinals.SELECT_ALL_CLIENTS_ORDERS_QUERY.inString)) {

            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, 15);
            ArrayList<Order> orderList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            int order_id;
            int user_ID;
            String productIDs;
            String user_address;
            String user_phone;
            String status;
            Order order;
            while (resultSet.next()) {

                order_id = resultSet.getInt(1);
                user_ID = resultSet.getInt(2);
                productIDs = resultSet.getString(3);
                user_address =  resultSet.getString(4);
                user_phone =  resultSet.getString(5);
                status =  resultSet.getString(6);
                order = new Order(order_id, user_ID, productIDs, user_address, user_phone, status);
                orderList.add(order);
            }
            preparedStatement.close();
            connection.close();
            return orderList;

        } catch (SQLException e) {
            System.out.println("dao exception while get all clients orders");
            throw new DAOException(e);
        }
    }
}

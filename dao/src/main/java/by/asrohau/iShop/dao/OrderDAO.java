package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface OrderDAO extends EntityFacadeFootprint<Order> {

    /**
     * finds all Orders by Status, limit
     * @param row required for pagenation
     * @param status of the searching orders
     * @return list of orders
     * @throws DAOException is a module exception
     */
    List<Order> findOrders(int row, String status) throws DAOException;

    /**
     * finding out the amount of all Orders sorting by Status
     * @param status of the searching orders
     * @return amount of orders according to status
     * @throws DAOException is a module exception
     */
    long countOrders(String status) throws DAOException;

    /**
     * finds all Orders of a certain User by userId, limit
     * @param row required for pagenation
     * @param userId is a User's id
     * @return list of orders
     * @throws DAOException is a module exception
     */
    List<Order> findUserOrders(int row, long userId) throws DAOException;

    /**
     * finding out the amount of all Orders of a certain User by userId
     * @param userId is a User's id
     * @return number of orders of certain user
     * @throws DAOException is a module exception
     */
    long countUserOrders(long userId) throws DAOException;

    /**
     * finds all OrdersByStatus of a certain User by userId, limit. Same as by user id and by status
     * @param row required for pagenation
     * @param order includes criteria for search: userId, status
     * @return list of orders according to criteria for a certain page
     * @throws DAOException is a module exception
     */
    List<Order> findUserOrdersByStatus(int row, Order order) throws DAOException;

    /**
     * finding out the amount of all Orders of a certain User by userId and orderStatus
     * @param order includes criteria for search
     * @return number of orders
     * @throws DAOException is a module exception
     */
    long countUserOrdersByStatus(Order order) throws DAOException;

    /**
     * changes status of a certain Order
     * @param id of the
     * @param status
     * @return true if successful, otherwise false
     * @throws DAOException is a module exception
     */
    boolean updateOrderStatus(long id, String status) throws DAOException;

    /**
     * delete all Orders of a certain User by userId
     * @param userId is a User's id
     * @return true if successful, otherwise false
     * @throws DAOException is a module exception
     */
    boolean deleteUserOrders(long userId) throws DAOException;
}

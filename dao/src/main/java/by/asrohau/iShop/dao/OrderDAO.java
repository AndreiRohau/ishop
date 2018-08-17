package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface OrderDAO extends EntityFacadeFootprint<Order> {

    /*
    finds all Orders by Status, limit
     */
    List<Order> findOrders(int row, String status) throws DAOException;

    /*
    finding out the amount of all Orders sorting by Status
     */
    long countOrders(String status) throws DAOException;

    /*
    finds all Orders of a certain User by userId, limit
     */
    List<Order> findUserOrders(int row, long userId) throws DAOException;

    /*
    finding out the amount of all Orders of a certain User by userId
     */
    long countUserOrders(long userId) throws DAOException;

    /*
    finds all OrdersByStatus of a certain User by userId, limit
     */
    List<Order> findUserOrdersByStatus(int row, Order order) throws DAOException;

    /*
    finding out the amount of all Orders of a certain User by userId and orderStatus
     */
    long countUserOrdersByStatus(Order order) throws DAOException;

    /*
    changes status of a certain Order
    */
    boolean update(Order order, String status) throws DAOException;

    /*
    delete all Orders of a certain User by userId
     */
    boolean deleteAllOrders(long userId) throws DAOException;
}

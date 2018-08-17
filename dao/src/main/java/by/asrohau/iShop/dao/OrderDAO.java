package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface OrderDAO extends EntityFacadeFootprint<Order> {

    List<Order> findOrders(int row, String status) throws DAOException;
    long countOrders(String status) throws DAOException;

    List<Order> findUserOrders(int row, long userId) throws DAOException;
    long countUserOrders(long userId) throws DAOException;

    List<Order> findUserOrdersByStatus(int row, Order order) throws DAOException;
    long countUserOrdersByStatus(Order order) throws DAOException;

    boolean update(Order order, String status) throws DAOException;
    boolean deleteAllOrders(long userId) throws DAOException;
}

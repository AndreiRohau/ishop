package by.asrohau.iShop.dao;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface OrderDAO extends AbstractDAO<Order> {

    boolean save(Reserve reserve) throws DAOException;

    List<Product> findAllReserved(int userId, int row) throws DAOException; //arraylist
    List<Integer> findAllReservedIds(int userId) throws DAOException; //linkedlist

    List<Order> findAllOrders(int row, String status) throws DAOException; //arraylist
    List<Order> findUserOrders(int row, int userId) throws DAOException; //arraylist
    List<Order> findUserOrdersByStatus(int row, Order order) throws DAOException; //arraylist

    boolean update(Order order, String status) throws DAOException;

    boolean deleteAllOrders(int userId) throws DAOException;
    boolean delete(int reserveId) throws DAOException;
    boolean deleteAllReserved(int userId) throws DAOException;

    int countReserved(int userId) throws DAOException;
    int countOrders(String status) throws DAOException;
    int countUserOrders(int userId) throws DAOException;
    int countUserOrdersByStatus(Order order) throws DAOException;
}

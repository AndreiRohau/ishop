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

    List<Order> findAllActiveOrders(int row) throws DAOException; //arraylist
    List<Order> findAllSuccessOrders(int row) throws DAOException; //arraylist
    List<Order> findAllClientsOrders(int row, int userId) throws DAOException; //arraylist

    boolean update(Order order, String status) throws DAOException;

    boolean deleteAllOrders(int userId) throws DAOException;
    boolean delete(int reserveId) throws DAOException;
    boolean deleteAllReserved(int userId) throws DAOException;

    int countReserved(int userId) throws DAOException;
    int countOrders(String status) throws DAOException;
    int countClientsOrders(int userId) throws DAOException;
}

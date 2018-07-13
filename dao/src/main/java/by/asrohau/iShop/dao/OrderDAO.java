package by.asrohau.iShop.dao;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface OrderDAO {

    boolean saveNewReservation(Reserve reserve) throws DAOException;
    List<Product> selectAllReserved(int user_id, int row) throws DAOException; //arraylist
    int countReserved(int user_id) throws DAOException;
    boolean deleteReserved(int reserveId) throws DAOException;

    List<Integer> selectAllReservedIds(int user_id) throws DAOException; //linkedlist

    boolean deleteAllReserved(int user_id) throws DAOException;
    boolean deleteAllOrdersWithUserID(int user_id) throws DAOException;

    boolean insertNewOrder(Order order) throws DAOException;

    int countOrders(String status) throws DAOException;

    List<Order> selectAllOrders(int row, String status) throws DAOException; //arraylist

    boolean deleteOrder(int orderId) throws DAOException;

    boolean updateOrderSetStatus(int orderId, String status) throws DAOException;

    Order selectOrderWithID(int orderId) throws DAOException;

    boolean updateOrdersProducts(Order order) throws DAOException;

    List<Order> selectAllActiveOrders(int row) throws DAOException; //arraylist

    List<Order> selectAllSuccessOrders(int row) throws DAOException; //arraylist

    int countClientsOrders(int user_id) throws DAOException;

    List<Order> selectAllClientsOrders(int row, int user_id) throws DAOException; //arraylist
}

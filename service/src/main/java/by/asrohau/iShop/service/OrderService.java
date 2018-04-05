package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.LinkedList;

public interface OrderService {
    boolean validation(Reserve reserve);
    boolean saveReserve(Reserve reserve) throws ServiceException;
    ArrayList<Product> getAllReserved(int user_id, int row) throws ServiceException;
    int countReserved(int user_id) throws ServiceException;
    boolean deleteReserved(int reserveId) throws ServiceException;

    LinkedList<Integer> getAllReservedIds(int user_id) throws ServiceException;

    boolean deleteAllReserved(int user_id) throws ServiceException;
    boolean deleteAllOrdersWithUserID(int user_id) throws ServiceException;

    boolean saveNewOrder(Order order) throws ServiceException;

    int countOrders(String status) throws ServiceException;

    ArrayList<Order> getAllOrders(int row, String status) throws ServiceException;

    boolean deleteOrder(int orderId) throws ServiceException;

    boolean orderSetStatus(int orderId, String status) throws ServiceException;

    Order findOrderWithID(int orderId) throws ServiceException;

    boolean deleteProductFromOrder(Order order) throws ServiceException;

    ArrayList<Order> getAllActiveOrders(int row) throws ServiceException;

    ArrayList<Order> getAllSuccessOrders(int row) throws ServiceException;

    int countClientOrders(int user_id) throws ServiceException;

    ArrayList<Order> getAllClientsOrders(int row, int user_id) throws ServiceException;
}

package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface OrderService {
    boolean validation(Reserve reserve);
    boolean saveReserve(Reserve reserve) throws ServiceException;
    List<Product> getAllReserved(int user_id, int row) throws ServiceException; //ArrayList
    int countReserved(int user_id) throws ServiceException;
    boolean deleteReserved(int reserveId) throws ServiceException;

    List<Integer> getAllReservedIds(int user_id) throws ServiceException; // LinkedList

    boolean deleteAllReserved(int user_id) throws ServiceException;
    boolean deleteAllOrdersWithUserID(int user_id) throws ServiceException;

    boolean saveNewOrder(Order order) throws ServiceException;

    int countOrders(String status) throws ServiceException;

    List<Order> getAllOrders(int row, String status) throws ServiceException; // ArrayList

    boolean deleteOrder(int orderId) throws ServiceException;

    boolean orderSetStatus(int orderId, String status) throws ServiceException;

    Order findOrderWithID(int orderId) throws ServiceException;

    boolean deleteProductFromOrder(Order order) throws ServiceException;

    List<Order> getAllActiveOrders(int row) throws ServiceException; // ArrayList

    List<Order> getAllSuccessOrders(int row) throws ServiceException; // ArrayList

    int countClientOrders(int user_id) throws ServiceException;

    List<Order> getAllClientsOrders(int row, int user_id) throws ServiceException; // ArrayList
}

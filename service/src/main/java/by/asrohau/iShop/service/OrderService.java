package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public interface OrderService {
    boolean saveReserve(Reserve reserve) throws ServiceException;
    List<Product> getAllReserved(int userId, int row) throws ServiceException; //ArrayList
    int countReserved(int userId) throws ServiceException;
    boolean deleteReserved(int reserveId) throws ServiceException;

    List<Integer> getAllReservedIds(int userId) throws ServiceException; // LinkedList

    boolean deleteAllReserved(int userId) throws ServiceException;
    boolean deleteAllOrdersWithUserId(int userId) throws ServiceException;

    boolean saveNewOrder(Order order) throws ServiceException;

    int countOrders(String status) throws ServiceException;

    List<Order> getAllOrders(int row, String status) throws ServiceException; // ArrayList

    boolean deleteOrder(Order order) throws ServiceException;

    boolean orderSetStatus(Order order, String status) throws ServiceException;

    Order findOrderWithID(Order order) throws ServiceException;

    boolean deleteProductFromOrder(Order order) throws ServiceException;

    List<Order> getAllActiveOrders(int row) throws ServiceException; // ArrayList

    List<Order> getAllSuccessOrders(int row) throws ServiceException; // ArrayList

    int countClientOrders(int userId) throws ServiceException;

    List<Order> getAllClientsOrders(int row, int userId) throws ServiceException; // ArrayList
}

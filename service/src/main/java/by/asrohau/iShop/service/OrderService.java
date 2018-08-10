package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.service.exception.ServiceException;

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
    boolean orderSetStatus(Order order, String status) throws ServiceException;
    List<Order> getOrders(int row) throws ServiceException; // ArrayList
    List<Order> getOrdersByStatus(int row, String status) throws ServiceException; // ArrayList
    List<Order> getUserOrders(int row, Order order) throws ServiceException; // ArrayList
    List<Order> getUserOrdersByStatus(int row, Order order) throws ServiceException; // ArrayList
    int countOrders() throws ServiceException;
    int countOrdersByStatus(String status) throws ServiceException;
    int countUserOrders(Order order) throws ServiceException;
    int countUserOrdersByStatus(Order order) throws ServiceException;

    Order findOrderWithID(Order order) throws ServiceException;

    boolean deleteOrder(Order order) throws ServiceException;

    boolean deleteProductFromOrder(Order order) throws ServiceException;
}

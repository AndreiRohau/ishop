package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    boolean deleteAllOrdersWithUserId(long userId) throws ServiceException;
    boolean saveNewOrder(Order order) throws ServiceException;
    boolean orderSetStatus(Order order, String status) throws ServiceException;
    List<Order> getOrders(int row) throws ServiceException; // ArrayList
    List<Order> getOrdersByStatus(int row, String status) throws ServiceException; // ArrayList
    List<Order> getUserOrders(int row, Order order) throws ServiceException; // ArrayList
    List<Order> getUserOrdersByStatus(int row, Order order) throws ServiceException; // ArrayList
    long countOrders() throws ServiceException;
    long countOrdersByStatus(String status) throws ServiceException;
    long countUserOrders(Order order) throws ServiceException;
    long countUserOrdersByStatus(Order order) throws ServiceException;

    Order findOrderWithID(Order order) throws ServiceException;
    boolean deleteOrder(Order order) throws ServiceException;
    boolean deleteProductFromOrder(Order order) throws ServiceException;
}

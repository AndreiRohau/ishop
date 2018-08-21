package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    /*
    creates (status:) new order, saving reserved products as a string
     */
    boolean saveNewOrder(Order order, List<Long> reservedProductIds) throws ServiceException;

    /*
    returns Order according to given id
     */
    Order findOrderById(long id) throws ServiceException;

    /*
    returns list of all Orders at table orders, limit
     */
    List<Order> getOrders(int row) throws ServiceException;

    /*
    returns list of Orders by Status at table orders, limit
     */
    List<Order> getOrdersByStatus(int row, String status) throws ServiceException;

    /*
    returns certain user's orders, limit
     */
    List<Order> getUserOrders(int row, long userId) throws ServiceException;

    /*
    returns orders by user Id and status , limit
     */
    List<Order> getUserOrdersByStatus(int row, Order order) throws ServiceException;

    /*
    counts amount of Orders at table orders
     */
    long countOrders() throws ServiceException;

    /*
    counts amount of Orders by Status at table orders
     */
    long countOrdersByStatus(String status) throws ServiceException;

    /*
    counts amount of orders by user Id and status
     */
    long countUserOrders(long userId) throws ServiceException;

    /*
    counts amount of orders by user Id and status
     */
    long countUserOrdersByStatus(Order order) throws ServiceException;

    /*
    changes order status
     */
    boolean orderSetStatus(long id, String status) throws ServiceException;

    /*

     */
    boolean deleteProductFromOrder(Order order) throws ServiceException;
    boolean removeProductFromOrder(Order order, String currentPage, String indexRemovingProduct) throws ServiceException;

    /*
    deletes order when there is now product in it
     */
    boolean deleteOrder(long id) throws ServiceException;

    //boolean deleteAllOrdersWithUserId(long userId) throws ServiceException;
}

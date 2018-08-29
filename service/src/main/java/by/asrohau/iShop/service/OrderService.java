package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface OrderService {

    /**
     * Creates (status:new) Order, saving reserved products as a string
     * @param order to save
     * @param reservedProductIds is a list of Products' ids for this Order
     * @return true if successful, otherwise false
     * @throws ServiceException is a module exception
     */
    boolean saveNewOrder(Order order, List<Long> reservedProductIds) throws ServiceException;

    /**
     * returns Order according to given id
     * @param id of order to be found
     * @return found Order
     * @throws ServiceException is a module exception
     */
    Order findOrderById(long id) throws ServiceException;

    /**
     * returns list of all Orders at table orders, limit
     * @param row for pagination
     * @return list of Orders
     * @throws ServiceException is a module exception
     */
    List<Order> getOrders(int row) throws ServiceException;

    /**
     * returns list of Orders by Status at table orders, limit
     * @param row for pagination
     * @param status is a criteria for search
     * @return List of Orders
     * @throws ServiceException is a module exception
     */
    List<Order> getOrdersByStatus(int row, String status) throws ServiceException;

    /**
     * returns certain user's orders, limit
     * @param row for pagination
     * @param userId as a criteria for search
     * @return list of Orders
     * @throws ServiceException is a module exception
     */
    List<Order> getUserOrders(int row, long userId) throws ServiceException;

    /**
     * returns orders by user Id and status , limit
     * @param row for pagination
     * @param order includes information about user id and order status, which are criteria for search
     * @return list of Orders
     * @throws ServiceException is a module exception
     */
    List<Order> getUserOrdersByStatus(int row, Order order) throws ServiceException;

    /**
     * counts amount of Orders at table orders
     * @return number of Orders
     * @throws ServiceException is a module exception
     */
    long countOrders() throws ServiceException;

    /**
     * counts amount of Orders by Status at table orders
     * @param status is a criteria for search
     * @return number of orders
     * @throws ServiceException is a module exception
     */
    long countOrdersByStatus(String status) throws ServiceException;

    /**
     * counts amount of orders by user Id and status
     * @param userId is a criteria for search
     * @return number of orders
     * @throws ServiceException is a module exception
     */
    long countUserOrders(long userId) throws ServiceException;

    /**
     * counts amount of orders by user Id and status
     * @param order includes information: user id and status, which are criteria for search
     * @return number of orders
     * @throws ServiceException is a module exception
     */
    long countUserOrdersByStatus(Order order) throws ServiceException;

    /**
     * changes order status
     * @param id of the order to be updated
     * @param status to be set on
     * @return true if successful, otherwise false
     * @throws ServiceException is a module exception
     */
    boolean orderSetStatus(long id, String status) throws ServiceException;

    /**
     * removes product from order
     * @param order to be updated
     * @param currentPage required to delete concrete product (not just some duplicate)
     * @param indexRemovingProduct required to delete concrete product (not just some duplicate)
     * @return true if successful, otherwise false
     * @throws ServiceException is a module exception
     */
    boolean removeProductFromOrder(Order order, String currentPage, String indexRemovingProduct) throws ServiceException;

    /**
     * deletes order when there is now product in it
     * @param id of deleting Order
     * @return true if successful, otherwise false
     * @throws ServiceException is a module exception
     */
    boolean deleteOrder(long id) throws ServiceException;
}

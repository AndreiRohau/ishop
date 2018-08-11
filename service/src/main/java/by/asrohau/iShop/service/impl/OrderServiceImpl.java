package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.OrderDAO;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

import static by.asrohau.iShop.service.util.ServiceValidator.validation;

public class OrderServiceImpl implements OrderService{

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    public OrderServiceImpl() {
    }

    @Override
    public boolean saveNewOrder(Order order) throws ServiceException {
        try {
            order.setDateCreated(new Date(System.currentTimeMillis()));
            return orderDAO.save(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }


    @Override
    public boolean deleteAllOrdersWithUserId(long userId) throws ServiceException {
        try {
            return orderDAO.deleteAllOrders(userId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public long countOrders() throws ServiceException {
        try {
            return (int) orderDAO.countAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long countOrdersByStatus(String status) throws ServiceException {
        try {
            return orderDAO.countOrders(status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrders(int row)  throws ServiceException{ // ArrayList
        try {
            return orderDAO.findAll(row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrdersByStatus(int row, String status)  throws ServiceException{ // ArrayList
        try {
            return orderDAO.findAllOrders(row, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteOrder(Order order) throws ServiceException {
        try {
            return orderDAO.delete(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean orderSetStatus(Order order, String status) throws ServiceException {
        try {
            return orderDAO.update(order, status);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findOrderWithID(Order order)  throws ServiceException{
        try {
            return orderDAO.find(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    //update list of products in Order
    @Override
    public boolean deleteProductFromOrder(Order order) throws ServiceException {
        try {
            return orderDAO.update(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getUserOrders(int row, Order order) throws ServiceException { // ArrayList
        try {
            return orderDAO.findUserOrders(row, order.getUserId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long countUserOrders(Order order) throws ServiceException {
        try {
            return orderDAO.countUserOrders(order.getUserId());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getUserOrdersByStatus(int row, Order order) throws ServiceException { // ArrayList
        try {
            return orderDAO.findUserOrdersByStatus(row, order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long countUserOrdersByStatus(Order order) throws ServiceException {
        try {
            return orderDAO.countUserOrdersByStatus(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}

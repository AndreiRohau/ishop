package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
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
    public boolean saveReserve(Reserve reserve) throws ServiceException {
        if(!validation(reserve)){
            return false;
        }
        try {
            return orderDAO.save(reserve);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
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
    public List<Product> getAllReserved(int userId, int row) throws ServiceException { //ArrayList
        try {
            return orderDAO.findAllReserved(userId, row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countReserved(int userId) throws ServiceException {
        try {
            return orderDAO.countReserved(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteReserved(int reserveId) throws ServiceException {
        try {
            return orderDAO.delete(reserveId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Integer> getAllReservedIds(int userId) throws ServiceException { // LinkedList
        try {
            return orderDAO.findAllReservedIds(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAllReserved(int userId) throws ServiceException {
        try {
            return orderDAO.deleteAllReserved(userId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }
    @Override
    public boolean deleteAllOrdersWithUserId(int userId) throws ServiceException {
        try {
            return orderDAO.deleteAllOrders(userId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public int countOrders() throws ServiceException {
        try {
            return (int) orderDAO.countAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countOrdersByStatus(String status) throws ServiceException {
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
    public int countUserOrders(Order order) throws ServiceException {
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
    public int countUserOrdersByStatus(Order order) throws ServiceException {
        try {
            return orderDAO.countUserOrdersByStatus(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


}

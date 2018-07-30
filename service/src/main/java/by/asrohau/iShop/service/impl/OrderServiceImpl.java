package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.OrderDAO;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.exception.ServiceException;

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
    public int countOrders(String status) throws ServiceException {
        try {
            return orderDAO.countOrders(status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllOrders(int row, String status)  throws ServiceException{ // ArrayList
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
    public List<Order> getAllActiveOrders(int row) throws ServiceException { // ArrayList
        try {
            return orderDAO.findAllActiveOrders(row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllSuccessOrders(int row) throws ServiceException { // ArrayList
        try {
            return orderDAO.findAllSuccessOrders(row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countClientOrders(int userId) throws ServiceException {
        try {
            return orderDAO.countClientsOrders(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllClientsOrders(int row, int user_id) throws ServiceException { // ArrayList
        try {
            return orderDAO.findAllClientsOrders(row, user_id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

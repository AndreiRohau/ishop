package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.bean.Order;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.OrderDAO;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    public OrderServiceImpl() {
    }

    @Override
    public boolean validation(Reserve reserve) {
        return reserve.getrUserId() != 0 && reserve.getrProductId() != 0;
    }

    @Override
    public boolean saveReserve(Reserve reserve) throws ServiceException {
        try {
            return validation(reserve) && orderDAO.saveNewReservation(reserve);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> getAllReserved(int user_id, int row) throws ServiceException { //ArrayList
        try {
            return orderDAO.selectAllReserved(user_id, row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countReserved(int user_id) throws ServiceException {
        try {
            return orderDAO.countReserved(user_id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteReserved(int reserveId) throws ServiceException {
        try {
            return orderDAO.deleteReserved(reserveId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Integer> getAllReservedIds(int user_id) throws ServiceException { // LinkedList
        try {
            return orderDAO.selectAllReservedIds(user_id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAllReserved(int user_id) throws ServiceException {
        try {
            return orderDAO.deleteAllReserved(user_id);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }
    @Override
    public boolean deleteAllOrdersWithUserID(int user_id) throws ServiceException {
        try {
            return orderDAO.deleteAllOrdersWithUserID(user_id);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean saveNewOrder(Order order) throws ServiceException {
        try {
            return orderDAO.insertNewOrder(order);
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
            return orderDAO.selectAllOrders(row, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }

    }

    @Override
    public boolean deleteOrder(int orderId) throws ServiceException {
        try {
            return orderDAO.deleteOrder(orderId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean orderSetStatus(int orderId, String status) throws ServiceException {
        try {
            return orderDAO.updateOrderSetStatus(orderId, status);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findOrderWithID(int orderId)  throws ServiceException{
        try {
            return orderDAO.selectOrderWithID(orderId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteProductFromOrder(Order order) throws ServiceException {
        try {
            return orderDAO.updateOrdersProducts(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllActiveOrders(int row) throws ServiceException { // ArrayList
        try {
            return orderDAO.selectAllActiveOrders(row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllSuccessOrders(int row) throws ServiceException { // ArrayList
        try {
            return orderDAO.selectAllSuccessOrders(row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int countClientOrders(int user_id) throws ServiceException {
        try {
            return orderDAO.countClientsOrders(user_id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getAllClientsOrders(int row, int user_id) throws ServiceException { // ArrayList
        try {
            return orderDAO.selectAllClientsOrders(row, user_id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}

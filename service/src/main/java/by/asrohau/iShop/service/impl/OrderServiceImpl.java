package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.OrderDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.entity.Order;
import by.asrohau.iShop.entity.Page;
import by.asrohau.iShop.service.OrderService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.sql.Date;
import java.util.List;

import static by.asrohau.iShop.service.util.ServiceValidator.validation;

public class OrderServiceImpl implements OrderService{

    private final OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    public OrderServiceImpl() {}

    @Override
    public boolean saveNewOrder(Order order, List<Long> reservedProductIds) throws ServiceException {
        try {
            order.setProductIds(reservedProductIds.toString().replaceAll("\\[|\\]| ", ""));
            order.setStatus("new");
            order.setDateCreated(new Date(System.currentTimeMillis()));
            return orderDAO.save(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public long countOrders() throws ServiceException {
        try {
            return orderDAO.countAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long countOrdersByStatus(String status) throws ServiceException {
        if (!validation(status)) {
            return 0;
        }
        try {
            return orderDAO.countOrders(status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrders(int row)  throws ServiceException{
        if (!validation(row)) {
            return null;
        }
        try {
            return orderDAO.findAll(row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getOrdersByStatus(int row, String status)  throws ServiceException{
        if (!validation(row)) {
            return null;
        }
        try {
            return orderDAO.findOrders(row, status);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteOrder(long id) throws ServiceException {
        if (!validation(id)) {
            return false;
        }
        try {
            return orderDAO.delete(id);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean orderSetStatus(long id, String status) throws ServiceException {
        if (!validation(id)) {
            return false;
        }
        try {
            String newStatus = "";
            switch (status) {
                case "new": newStatus = "active";
                    break;
                case "active": newStatus = "closed";
                    break;
                case "closed": newStatus = "new";
                    break;
            }
            return orderDAO.updateOrderStatus(id, newStatus);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public Order findOrderById(long id)  throws ServiceException{
        if (!validation(id)) {
            return null;
        }
        try {
            return orderDAO.findOne(id);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean removeProductFromOrder(Order order, String currentPage, String indexRemovingProduct) throws ServiceException {
        if (!validation(order)) {
            return false;
        }
        try {
            String[] productIdsArray = order.getProductIds().split(",");
            int cp = Integer.parseInt(currentPage);
            int iRemovingProduct = (cp - 1) * Page._MAX_ROWS_AT_PAGE + Integer.parseInt(indexRemovingProduct);

            StringBuilder finalIds = new StringBuilder();
            for(int i = 1; i <= productIdsArray.length; i++) {
                if(i != iRemovingProduct) {
                    finalIds.append(productIdsArray[i - 1]).append(",");
                }
            }
            order.setProductIds(finalIds.toString());

            return orderDAO.update(order);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getUserOrders(int row, long userId) throws ServiceException { // ArrayList
        if (!validation(userId) || !validation(row)) {
            return null;
        }
        try {
            return orderDAO.findUserOrders(row, userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long countUserOrders(long userId) throws ServiceException {
        if (!validation(userId)) {
            return 0;
        }
        try {
            return orderDAO.countUserOrders(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Order> getUserOrdersByStatus(int row, Order order) throws ServiceException { // ArrayList
        if (!validation(row)) {
            return null;
        }
        try {
            return orderDAO.findUserOrdersByStatus(row, order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public long countUserOrdersByStatus(Order order) throws ServiceException {
        if (!validation(order)) {
            return 0;
        }
        try {
            return orderDAO.countUserOrdersByStatus(order);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

}

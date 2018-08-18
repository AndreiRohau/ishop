package by.asrohau.iShop.service.impl;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.dao.DAOFactory;
import by.asrohau.iShop.dao.ReserveDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.service.ReserveService;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

import static by.asrohau.iShop.service.util.ServiceValidator.validation;

public class ReserveServiceImpl implements ReserveService {

    private final ReserveDAO reserveDAO = DAOFactory.getInstance().getReserveDAO();

    public ReserveServiceImpl() {
    }


    @Override
    public boolean saveReserve(Reserve reserve) throws ServiceException {
        if(!validation(reserve)){
            return false;
        }
        try {
            return reserveDAO.save(reserve);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Reserve> getAllReserved(long userId, int row) throws ServiceException { //ArrayList
        try {
            return reserveDAO.findReservationsByUserId(userId, row);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }


    @Override
    public long countReserved(long userId) throws ServiceException {
        try {
            return reserveDAO.countReservationsByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteReserved(Reserve reserve) throws ServiceException {
        try {
            return reserveDAO.delete(reserve.getId());
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Long> getAllReservedIds(long userId) throws ServiceException { // LinkedList
        try {
            return reserveDAO.findReservedProductIdsByUserId(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteAllReserved(long userId) throws ServiceException {
        try {
            return reserveDAO.deleteReservationsByUserId(userId);
        } catch(DAOException e){
            throw new ServiceException(e);
        }
    }

}

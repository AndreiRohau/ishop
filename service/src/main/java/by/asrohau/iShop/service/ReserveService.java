package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface ReserveService {

    boolean saveReserve(Reserve reserve) throws ServiceException;
    List<Reserve> getAllReserved(long userId, int row) throws ServiceException; //ArrayList
    long countReserved(long userId) throws ServiceException;
    boolean deleteReserved(Reserve reserve) throws ServiceException;
    List<Long> getAllReservedIds(long userId) throws ServiceException; // LinkedList
    boolean deleteAllReserved(long userId) throws ServiceException;

}

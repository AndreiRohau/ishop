package by.asrohau.iShop.service;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface ReserveService {

    boolean saveReserve(Reserve reserve) throws ServiceException;
    List<Product> getAllReserved(long userId, int row) throws ServiceException; //ArrayList
    long countReserved(long userId) throws ServiceException;
    boolean deleteReserved(Reserve reserve) throws ServiceException;
    List<Long> getAllReservedIds(long userId) throws ServiceException; // LinkedList
    boolean deleteAllReserved(long userId) throws ServiceException;

}

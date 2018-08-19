package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface ReserveService {

    /*
    reserves product : put product into user's basket
     */
    boolean saveReserve(Reserve reserve) throws ServiceException;

    /*
    products in user's basket
     */
    List<Reserve> getReservations(long userId, int row) throws ServiceException;

    /*
    counts reserved produts by userId
     */
    long countReserved(long userId) throws ServiceException;

    /*
    deletes a certain user's reservation
     */
    boolean deleteReserved(long id) throws ServiceException;

    /*

     */
    List<Long> getReservedProductIds(long userId) throws ServiceException;

}

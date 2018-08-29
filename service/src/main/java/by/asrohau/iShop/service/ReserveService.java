package by.asrohau.iShop.service;

import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.service.exception.ServiceException;

import java.util.List;

public interface ReserveService {

    /**
     * reserves product : put product into user's basket
     * @param reserve to be saved
     * @return true if successful, otherwise false
     * @throws ServiceException is a module exception
     */
    boolean saveReserve(Reserve reserve) throws ServiceException;

    /**
     * products in user's basket
     * @param userId required determing the user
     * @param row required for pagination
     * @return list of Reserve
     * @throws ServiceException is a module exception
     */
    List<Reserve> getReservations(long userId, int row) throws ServiceException;

    /**
     * counts reserved products by userId
     * @param userId required determing the user
     * @return number of Reserves
     * @throws ServiceException is a module exception
     */
    long countReserved(long userId) throws ServiceException;

    /**
     * deletes a certain user's reservation
     * @param id of deleting reservation
     * @return true if successful, otherwise false
     * @throws ServiceException is a module exception
     */
    boolean deleteReserved(long id) throws ServiceException;

    /**
     * prepairs a list of Products' ids for creation of a new Order
     * @param userId required determing the user
     * @return list of Products' ids
     * @throws ServiceException is a module exception
     */
    List<Long> getReservedProductIds(long userId) throws ServiceException;

}

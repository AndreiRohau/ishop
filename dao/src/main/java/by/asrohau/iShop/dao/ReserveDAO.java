package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface ReserveDAO extends EntityFacadeFootprint<Reserve> {

    /**
     * finds all reservations of a certain user, limit
     * @param userId is an id
     * @param row is a number for pagenation
     * @return list of reservations. Entity: Reserve
     * @throws DAOException is a module exception
     */
    List<Reserve> findReservationsByUserId(long userId, int row) throws DAOException;

    /**
     * finds reserved Product Ids of a certain user
     * @param userId is an id
     * @return list of ids, of the reservations
     * @throws DAOException is a module exception
     */
    List<Long> findReservedProductIdsByUserId(long userId) throws DAOException;

    /**
     * returns the number of all reservations of a certain user
     * @param userId is an id
     * @return amount of reservations of the certain user
     * @throws DAOException is a module exception
     */
    long countReservationsByUserId(long userId) throws DAOException;

}

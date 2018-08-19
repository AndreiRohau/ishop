package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface ReserveDAO extends EntityFacadeFootprint<Reserve> {

    /*
    finds all reservations of a certain user, limit
     */
    List<Reserve> findReservationsByUserId(long userId, int row) throws DAOException;

    /*
    finds reserved Product Ids of a certain user
     */
    List<Long> findReservedProductIdsByUserId(long userId) throws DAOException;

    /*
    returns the number of all reservations of a certain user
     */
    long countReservationsByUserId(long userId) throws DAOException;

}

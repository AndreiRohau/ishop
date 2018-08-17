package by.asrohau.iShop.dao;

import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

public interface ReserveDAO extends EntityFacadeFootprint<Reserve> {
    List<Product> findAllReserved(long userId, int row) throws DAOException;
    List<Long> findAllReservedIds(long userId) throws DAOException;
    long countReservedByUserId(long userId) throws DAOException;
    boolean deleteAllReserved(long userId) throws DAOException;
}

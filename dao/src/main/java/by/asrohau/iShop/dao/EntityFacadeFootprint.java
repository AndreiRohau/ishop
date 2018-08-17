package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;
/*
    abstract CRUD, findOne(long id), findAll(int row) and countAll()
 */
public interface EntityFacadeFootprint<T> {

    /*
    abstract create, same as SAVE
     */
    boolean save(T t) throws DAOException;

    /*
    abstract read, same as FIND
     */
    T find(T t) throws DAOException;

    /*
    abstract read, same as FIND
     */
    T findOne(long id) throws DAOException;

    /*
    update
     */
    boolean update(T t) throws DAOException;

    /*
    delete
     */
    boolean delete(long id) throws DAOException;

    /*
    find all, limit
     */
    List<T> findAll(int row) throws DAOException;

    /*
    count all
     */
    long countAll() throws DAOException;
}

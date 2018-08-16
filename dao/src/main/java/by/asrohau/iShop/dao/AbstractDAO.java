package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
/*
    abstract CRUD and abstract findAll() and abstract countAll()
 */
public interface AbstractDAO<T> {

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
    boolean delete(T t) throws DAOException;

    /*
    find all
     */
    List<T> findAll(int row) throws DAOException;

    /*
    count all
     */
    long countAll() throws DAOException;
}

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
    public abstract boolean save(T t) throws DAOException;

    /*
    abstract read, same as FIND
     */
    public abstract T find(T t) throws DAOException;

    /*
    update
     */
    public abstract boolean update(T t) throws DAOException;

    /*
    delete
     */
    public abstract boolean delete(T t) throws DAOException;

    /*
    find all
     */
    public abstract List<T> findAll(int row) throws DAOException;

    /*
    count all
     */
    public abstract long countAll() throws DAOException;
}

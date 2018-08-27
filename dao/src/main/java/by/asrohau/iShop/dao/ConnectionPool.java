package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.exception.DAOException;

import java.sql.Connection;

public interface ConnectionPool {
    Connection provide() throws DAOException;
    void retrieve(Connection connection) throws DAOException;
}

package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.exception.DAOException;

import java.sql.Connection;


public interface ConnectionPool {

    /**
     * <p>Provides with an existing free connection from pool</p>
     * @return Connection from pool
     * @throws DAOException module Exception
     */
    Connection provide() throws DAOException;

    /**
     * <p>Returns connection back the pool</p>
     * @param connection of java.sql.Connection
     * @throws DAOException module Exception
     */
    void retrieve(Connection connection) throws DAOException;
}

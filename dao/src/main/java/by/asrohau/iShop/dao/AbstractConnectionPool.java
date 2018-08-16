package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.util.ConnectionPool;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractConnectionPool {
    private final static Logger logger = Logger.getLogger(AbstractConnectionPool.class);
    private static ConnectionPool connectionPool = new ConnectionPool();

    protected Connection getConnection() throws DAOException {
        logger.info("AbstractConnectionPool.getConnection()");
        return connectionPool.provide();
    }

    protected void returnConnection(Connection connection) throws DAOException {
        logger.info("AbstractConnectionPool.returnConnection(Connection connection)");
        connectionPool.retrieve(connection);
    }

    protected void close(ResultSet resultSet, PreparedStatement preparedStatement) throws DAOException {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Error while closing resultSet or prepared statement, or connection", e);
        }
        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Error while closing resultSet or prepared statement, or connection", e);
        }
    }

}

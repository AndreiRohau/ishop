package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {

    private final static Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    private ConnectionPool connectionPool;

    public AbstractDAO(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    /*
    getting an available connection from ConnectionPool
     */
    protected Connection getConnection() throws DAOException {
        logger.info("AbstractConnectionPool.getConnection()");
        return connectionPool.provide();
    }

    /*
    returning back connection to ConnectionPool
     */
    protected void returnConnection(Connection connection) throws DAOException {
        logger.info("AbstractConnectionPool.returnConnection(Connection connection)");
        connectionPool.retrieve(connection);
    }

    /*
    unified close-method for PreparedStatement and ResultSet
     */
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

package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDAO {

    private final static Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

    protected ConnectionPool connectionPool;

    public AbstractDAO(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
    }

    /**
     * unified close-method for PreparedStatement and ResultSet
     * @param resultSet which was in use
     * @param preparedStatement which was in use
     * @throws DAOException is a module exception
     */
    protected void close(ResultSet resultSet, PreparedStatement preparedStatement) throws DAOException {
        try {
            if(resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Error while closing ResultSet", e);
        }
        try {
            if(preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException e) {
            throw new DAOException("Error while closing PreparedStatement", e);
        }
    }

}

package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.bean.Product;
import by.asrohau.iShop.bean.Reserve;
import by.asrohau.iShop.dao.AbstractConnection;
import by.asrohau.iShop.dao.ReserveDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static by.asrohau.iShop.dao.util.DAOFinals.*;

public class ReserveDAOImpl extends AbstractConnection implements ReserveDAO {

    private final static Logger logger = Logger.getLogger(ReserveDAOImpl.class);
    private PreparedStatement preparedStatement = null;
    private Connection connection = null;
    private ResultSet resultSet = null;

    @Override
    public boolean save(Reserve reserve) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SAVE_RESERVATION_QUERY.inString);
            preparedStatement.setLong(1, reserve.getrUserId());
            preparedStatement.setLong(2, reserve.getrProductId());

            int result = preparedStatement.executeUpdate();

            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public Reserve find(Reserve reserve) throws DAOException {
        return null;
    }

    @Override
    public boolean update(Reserve reserve) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(Reserve reserve) throws DAOException {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_RESERVED_QUERY.inString);
            preparedStatement.setLong(1, reserve.getId());

            int result = preparedStatement.executeUpdate();
            connection.commit();
            return (result != 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException(EXCEPTION_WHILE_ROLL_BACK.inString, ex);
            }
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public boolean deleteAllReserved(long userId) throws DAOException {
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_ALL_RESERVED_QUERY.inString);
            preparedStatement.setLong(1, userId);

            int result = preparedStatement.executeUpdate();
            connection.commit();
            return (result != 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException(EXCEPTION_WHILE_ROLL_BACK.inString, ex);
            }
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public List<Reserve> findAll(int row) throws DAOException {
        return null;
    }

    @Override
    public List<Product> findAllReserved(long userId, int row) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_RESERVED_QUERY.inString);
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, Integer.parseInt(MAX_ROWS_AT_PAGE.inString));
            List<Product> productList = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            long reserveId;
            long productId;
            Product product;
            while (resultSet.next()) {
                reserveId = resultSet.getLong(1);
                productId = resultSet.getLong(3);
                product = new Product(productId, reserveId);
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }

    @Override
    public List<Long> findAllReservedIds(long userId) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SELECT_ALL_RESERVED_IDS_QUERY.inString);
            preparedStatement.setLong(1, userId);
            List<Long> productIdsList = new LinkedList<>();
            resultSet = preparedStatement.executeQuery();
            long productId;
            while (resultSet.next()) {
                productId = resultSet.getLong(3);
                productIdsList.add(productId);
            }
            return productIdsList;
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }    }

    @Override
    public long countAll() throws DAOException {
        return 0;
    }

    @Override
    public long countReservedByUserId(long userId) throws DAOException {
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_RESERVED_QUERY.inString);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException(EXCEPTION_WHILE_EXECUTING_DAO_METHOD.inString, e);
        } finally {
            close(resultSet, preparedStatement, connection);
        }
    }
}

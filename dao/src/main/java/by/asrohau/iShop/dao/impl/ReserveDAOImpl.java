package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.ReserveDAO;
import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.entity.Product;
import by.asrohau.iShop.entity.Reserve;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static by.asrohau.iShop.dao.util.DAOFinals.MAX_ROWS_AT_PAGE;

public class ReserveDAOImpl extends AbstractDAO implements ReserveDAO {

    private final static Logger logger = LoggerFactory.getLogger(ReserveDAOImpl.class);
    /*
    ReserveDAO queries
     */
    private static final String SAVE_RESERVATION_QUERY = "INSERT INTO shop.reserve (userId, productId) VALUES (?,?)";
    //private static final String FIND_RESERVE_BY_ID_QUERY = "SELECT * FROM shop.reserve WHERE id = ?";
    private static final String FIND_RESERVES_BY_USER_ID_QUERY = "SELECT * FROM shop.reserve WHERE userId = ?";
    private static final String FIND_RESERVES_BY_USER_ID_LIMIT_QUERY = "SELECT * FROM shop.reserve WHERE userId = ? LIMIT ?, ?";
    private static final String COUNT_RESERVED_QUERY = "SELECT COUNT(*) FROM shop.reserve WHERE userId = ?";
    private static final String DELETE_RESERVATION_BY_ID_QUERY = "DELETE FROM shop.reserve WHERE id = ?";
    private static final String DELETE_RESERVATIONS_BY_USER_ID_QUERY = "DELETE FROM shop.reserve WHERE userId = ?";
    
    @Override
    public boolean save(Reserve reserve) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SAVE_RESERVATION_QUERY);
            preparedStatement.setLong(1, reserve.getrUserId());
            preparedStatement.setLong(2, reserve.getrProductId());

            int result = preparedStatement.executeUpdate();

            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(null, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public Reserve find(Reserve reserve) throws DAOException {
        return null;
    }

    @Override
    public Reserve findOne(long id) throws DAOException {
        return null;
    }

    @Override
    public boolean update(Reserve reserve) throws DAOException {
        return false;
    }

    @Override
    public boolean delete(long id) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(DELETE_RESERVATION_BY_ID_QUERY);
            preparedStatement.setLong(1, id);

            int result = preparedStatement.executeUpdate();
            connection.commit();
            return (result != 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Error during rollback", ex);
            }
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(null, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public boolean deleteAllReserved(long userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement = connection.prepareStatement(DELETE_RESERVATIONS_BY_USER_ID_QUERY);
            preparedStatement.setLong(1, userId);

            int result = preparedStatement.executeUpdate();
            connection.commit();
            return (result != 0);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new DAOException("Error during rollback", ex);
            }
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(null, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public List<Reserve> findAll(int row) throws DAOException {
        return null;
    }

    @Override
    public List<Product> findAllReserved(long userId, int row) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_RESERVES_BY_USER_ID_LIMIT_QUERY);
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, MAX_ROWS_AT_PAGE);
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
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public List<Long> findAllReservedIds(long userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_RESERVES_BY_USER_ID_QUERY);
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
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public long countAll() throws DAOException {
        return 0;
    }

    @Override
    public long countReservedByUserId(long userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_RESERVED_QUERY);
            preparedStatement.setLong(1, userId);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getLong(1);
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }
}

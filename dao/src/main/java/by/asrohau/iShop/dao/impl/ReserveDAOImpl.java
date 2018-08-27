package by.asrohau.iShop.dao.impl;

import by.asrohau.iShop.dao.AbstractDAO;
import by.asrohau.iShop.dao.ConnectionPool;
import by.asrohau.iShop.dao.ReserveDAO;
import by.asrohau.iShop.dao.exception.DAOException;
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

import static by.asrohau.iShop.dao.DAOFinals.MAX_ROWS_AT_PAGE;

public class ReserveDAOImpl extends AbstractDAO implements ReserveDAO {

    private final static Logger logger = LoggerFactory.getLogger(ReserveDAOImpl.class);
    /*
    ReserveDAO queries
     */
    private static final String SAVE_RESERVATION_QUERY = "INSERT INTO shop.reserve (userId, productId) VALUES (?,?)";
    private static final String FIND_RESERVES_LIMIT_QUERY = "SELECT * FROM shop.reserve LIMIT ?,?";
    private static final String FIND_RESERVE_BY_USER_ID_AND_PRODUCT_ID_QUERY = "SELECT * FROM shop.reserve WHERE userId = ? AND productID = ?";
    private static final String FIND_RESERVE_BY_ID_QUERY = "SELECT * FROM shop.reserve WHERE id = ?";
    private static final String FIND_RESERVES_BY_USER_ID_QUERY = "SELECT * FROM shop.reserve WHERE userId = ?";
    private static final String FIND_RESERVES_BY_USER_ID_LIMIT_QUERY = "SELECT * FROM shop.reserve WHERE userId = ? LIMIT ?, ?";
    private static final String COUNT_RESERVED_QUERY = "SELECT COUNT(*) FROM shop.reserve";
    private static final String COUNT_RESERVED_BY_USER_ID_QUERY = "SELECT COUNT(*) FROM shop.reserve WHERE userId = ?";
    private static final String UPDATE_RESERVE_BY_ID_QUERY = "UPDATE shop.reserve SET userId = ?, productId = ? WHERE id = ?";
    private static final String DELETE_RESERVATION_BY_ID_QUERY = "DELETE FROM shop.reserve WHERE id = ?";

    public ReserveDAOImpl(ConnectionPool connectionPool) {
        super(connectionPool);
    }

    /*
        save reserve to reserve table
         */
    @Override
    public boolean save(Reserve reserve) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(SAVE_RESERVATION_QUERY);
            preparedStatement.setLong(1, reserve.getUserId());
            preparedStatement.setLong(2, reserve.getProductId());
            int result = preparedStatement.executeUpdate();
            return (result > 0);
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(null, preparedStatement);
            returnConnection(connection);
        }
    }

    /*
    finds reserve by user id and product id
     */
    @Override
    public Reserve find(Reserve reserve) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_RESERVE_BY_USER_ID_AND_PRODUCT_ID_QUERY);
            preparedStatement.setLong(1, reserve.getUserId());
            preparedStatement.setLong(2, reserve.getProductId());
            resultSet = preparedStatement.executeQuery();
            reserve = new Reserve();

            while (resultSet.next()) {
                reserve.setId(resultSet.getLong(1));
                reserve.setUserId(resultSet.getLong(2));
                reserve.setProductId(resultSet.getLong(3));
            }

            if (reserve.getId() != 0) {
                return reserve;
            }
            logger.info("Can not identify Reservation by UserId and ProductId");
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }

    }

    /*
    finds reservation by id
     */
    @Override
    public Reserve findOne(long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_RESERVE_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            Reserve reserve = new Reserve();
            while (resultSet.next()) {
                reserve.setId(resultSet.getLong(1));
                reserve.setUserId(resultSet.getLong(2));
                reserve.setProductId(resultSet.getLong(3));
            }

            if (reserve.getId() != 0) {
                return reserve;
            }
            logger.info("Can not identify User with id");
            return null;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    /*
    updates reservation by id
     */
    @Override
    public boolean update(Reserve reserve) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UPDATE_RESERVE_BY_ID_QUERY);
            preparedStatement.setLong(1, reserve.getUserId());
            preparedStatement.setLong(2, reserve.getProductId());
            preparedStatement.setLong(3, reserve.getId());

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

    /*
    delete reservation by id
     */
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

    /*
    finds all reservations in table
     */
    @Override
    public List<Reserve> findAll(int row) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_RESERVES_LIMIT_QUERY);
            preparedStatement.setInt(1, row);
            preparedStatement.setInt(2, MAX_ROWS_AT_PAGE);
            List<Reserve> reserves = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reserve reserve = new Reserve(
                        resultSet.getLong(1),
                        resultSet.getLong(2),
                        resultSet.getLong(3));
                reserves.add(reserve);
            }
            return reserves;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    /*
    count all reservations in table
     */
    @Override
    public long countAll() throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_RESERVED_QUERY);
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

    @Override
    public List<Reserve> findReservationsByUserId(long userId, int row) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(FIND_RESERVES_BY_USER_ID_LIMIT_QUERY);
            preparedStatement.setLong(1, userId);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, MAX_ROWS_AT_PAGE);
            List<Reserve> reservations = new ArrayList<>();
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Reserve reserve = new Reserve(
                        resultSet.getLong(1),
                        userId,
                        resultSet.getLong(3));
                reservations.add(reserve);
            }
            return reservations;
        } catch (SQLException e) {
            throw new DAOException("Error in DAO method", e);
        } finally {
            close(resultSet, preparedStatement);
            returnConnection(connection);
        }
    }

    @Override
    public List<Long> findReservedProductIdsByUserId(long userId) throws DAOException {
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
    public long countReservationsByUserId(long userId) throws DAOException {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(COUNT_RESERVED_BY_USER_ID_QUERY);
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

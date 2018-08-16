package by.asrohau.iShop.dao.util;

import by.asrohau.iShop.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static by.asrohau.iShop.dao.util.DAOFinals.*;

public class ConnectionPool {
    private final static Logger logger = Logger.getLogger(ConnectionPool.class);

    private static DatabaseConfigReader databaseConfigReader = new DatabaseConfigReader();
    private static final String DRIVER = databaseConfigReader.get(SQL_DRIVER);
    private static final int AMOUNT_OF_CONNECTIONS = Integer.parseInt(databaseConfigReader.get(DB_CONNECTIONS));
    private static final String URL = databaseConfigReader.get(DB_URL);
    private static final String USER = databaseConfigReader.get(DB_LOGIN);
    private static final String PASSWORD = databaseConfigReader.get(DB_PASSWORD);
    private static final String SETTINGS = databaseConfigReader.get(DB_SETTINGS);
    private static final String FIXED_URL = URL + "?user=" + USER + "&password=" + PASSWORD + "&" + SETTINGS;

    private static boolean driverIsLoaded = false;
    private BlockingQueue<Connection> availableConnections = new ArrayBlockingQueue<>(AMOUNT_OF_CONNECTIONS);
    private BlockingQueue<Connection> takenConnections = new ArrayBlockingQueue<>(AMOUNT_OF_CONNECTIONS);

    public ConnectionPool() {
        try {
            getJDBCDriver();
            for (int i = 0; i < AMOUNT_OF_CONNECTIONS; i++) {
                availableConnections.add(getConnection());
            }
        } catch (DAOException e) {}

        logger.info("availableConnections.size() is " + availableConnections.size());
        logger.info("takenConnections.size() is " + takenConnections.size());
    }

    public synchronized Connection provide() throws DAOException {
        Connection newConnection;
        if (availableConnections.size() == 0) {
            newConnection = getConnection();
        } else {
            try{
                newConnection = availableConnections.take();
                availableConnections.remove(newConnection);
                takenConnections.add(newConnection);
            } catch (InterruptedException e) {
                throw new DAOException(e);
            }
        }
        logger.info("ConnectionPool.provide()");
        logger.info("ConnectionPool.availableConnections.size() is " + availableConnections.size());
        logger.info("ConnectionPool.takenConnections.size() is " + takenConnections.size());
        return newConnection;
    }

    public synchronized void retrieve(Connection connection) throws DAOException {
        if (connection != null) {
            logger.info("ConnectionPool.retrieve(Connection connection)");
            if (takenConnections.remove(connection)) {
                availableConnections.offer(connection);
                logger.info("origin connection");
            } else {
                try {
                    logger.info("runtime connection");
                    connection.close();
                } catch (SQLException e) {
                    throw new DAOException(e);
                }
            }

            logger.info("ConnectionPool.availableConnections.size() is " + availableConnections.size());
            logger.info("ConnectionPool.takenConnections.size() is " + takenConnections.size());
        } else {
            logger.info("ConnectionPool.retrieve(Connection connection)");
            logger.info("connection is NULL");
        }
    }

    public static Connection getConnection() throws DAOException{
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(FIXED_URL);
        } catch (SQLException e) {
            throw new DAOException("Connection to database failed", e);
        }
        return connection;
    }

    private static void getJDBCDriver() throws DAOException {
        if (!driverIsLoaded) {
            try {
                Class.forName(DRIVER);
                driverIsLoaded = true;
                logger.info("MySQL driver is loaded");
            } catch (ClassNotFoundException e) {
                throw new DAOException("MySQL driver is not loaded", e);
            }
        }
    }

}
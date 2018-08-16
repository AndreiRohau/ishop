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
    private static boolean driverIsLoaded = false;

    private static final int AMOUNT_OF_CONNECTIONS = Integer.parseInt(databaseConfigReader.get(DB_CONNECTIONS.inString));
    private BlockingQueue<Connection> availableConnections = new ArrayBlockingQueue<>(AMOUNT_OF_CONNECTIONS);
    private BlockingQueue<Connection> takenConnections = new ArrayBlockingQueue<>(AMOUNT_OF_CONNECTIONS);

    public ConnectionPool() {
        try {
            getJDBCDriver();
        } catch (DAOException e) {}
        for (int i = 0; i < AMOUNT_OF_CONNECTIONS; i++) {
            availableConnections.add(getConnection());
        }
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

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(databaseConfigReader.get(DB_URL_FIXED.inString));
        } catch (SQLException e) {
            logger.error(CONNECTION_FAILED.inString);
        }
        return connection;
    }

    private static void getJDBCDriver() throws DAOException {
        if (!driverIsLoaded) {
            try {
                Class.forName(databaseConfigReader.get(SQL_DRIVER.inString));
                driverIsLoaded = true;
                logger.info(MYSQL_DRIVER_IS_LOADED.inString);
            } catch (ClassNotFoundException e) {
                logger.error(MYSQL_DRIVER_NOT_LOADED.inString);
                throw new DAOException(e);
            }
        }
    }

}
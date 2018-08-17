package by.asrohau.iShop.dao.util;

import by.asrohau.iShop.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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

    public Connection provide() throws DAOException {
        Connection newConnection;
        try{
            newConnection = availableConnections.take();
            takenConnections.add(newConnection);
        } catch (InterruptedException e) {
            throw new DAOException(e);
        }
        logger.info("ConnectionPool.provide()");
        logger.info("ConnectionPool.availableConnections.size() is " + availableConnections.size());
        logger.info("ConnectionPool.takenConnections.size() is " + takenConnections.size());
        return newConnection;
    }

    public void retrieve(Connection connection) throws DAOException {
        if (connection != null) {
            logger.info("ConnectionPool.retrieve(Connection connection)");
            takenConnections.remove(connection);
            availableConnections.add(connection);
        } else {
            logger.info("ConnectionPool.retrieve(Connection connection)");
            logger.info("connection is NULL");
        }
        logger.info("ConnectionPool.availableConnections.size() is " + availableConnections.size());
        logger.info("ConnectionPool.takenConnections.size() is " + takenConnections.size());
    }

    private static Connection getConnection() throws DAOException{
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(FIXED_URL);
        } catch (SQLException e) {
            logger.error("Could not connect to database");
            shutdownTomcat();
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
                logger.error("MySQL driver is not loaded");
                shutdownTomcat();
                throw new DAOException("MySQL driver is not loaded", e);
            }
        }
    }

    private static void shutdownTomcat() throws DAOException{
        try {
            // path to command C:\Program Files\apache-tomcat-8.5.15\conf\server.xml
            Socket socket = new Socket("localhost", 8005);
            if (socket.isConnected()) {
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                pw.println("SHUTDOWN");
                pw.close();
                socket.close();
            }
        } catch (IOException e) {
            throw new DAOException("Can NOT stop TOMCAT", e);
        }
    }

}
package by.asrohau.iShop.dao.databaseConnectionImpl;

import by.asrohau.iShop.dao.ConnectionPool;
import by.asrohau.iShop.dao.DatabaseConfigReader;
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

import static by.asrohau.iShop.dao.DAOFinals.*;

public class ConnectionPoolImpl implements ConnectionPool{

    private final static Logger logger = Logger.getLogger(ConnectionPoolImpl.class);
    private final String DRIVER;
    private final String FIXED_URL;
    private static boolean driverIsLoaded = false;

    private final BlockingQueue<Connection> availableConnections;
    private final BlockingQueue<Connection> takenConnections;


    public ConnectionPoolImpl(DatabaseConfigReader databaseConfigReader) {
        this.DRIVER = databaseConfigReader.get(SQL_DRIVER);
        String url = databaseConfigReader.get(DB_URL);
        String user = databaseConfigReader.get(DB_LOGIN);
        String password = databaseConfigReader.get(DB_PASSWORD);
        String settings = databaseConfigReader.get(DB_SETTINGS);
        this.FIXED_URL = url + "?user=" + user + "&password=" + password + "&" + settings;
        int amountOfConnections = Integer.parseInt(databaseConfigReader.get(DB_CONNECTIONS));
        this.availableConnections = new ArrayBlockingQueue<>(amountOfConnections);
        this.takenConnections = new ArrayBlockingQueue<>(amountOfConnections);

        try {
            getJDBCDriver();
            for (int i = 0; i < amountOfConnections; i++) {
                availableConnections.add(getConnection());
            }
        } catch (DAOException e) {}
        logger.info("availableConnections.size() is " + availableConnections.size());
        logger.info("takenConnections.size() is " + takenConnections.size());
    }

    @Override
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

    @Override
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

    /**
     * creates a connection
     */
    private Connection getConnection() throws DAOException{
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

    /**
     * method loads database driver
     */
    private void getJDBCDriver() throws DAOException {
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

    /**
     * in case of errors we can stop tomcat
     */
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
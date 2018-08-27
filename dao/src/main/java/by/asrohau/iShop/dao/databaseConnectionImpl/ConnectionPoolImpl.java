package by.asrohau.iShop.dao.databaseConnectionImpl;

import by.asrohau.iShop.dao.ConnectionPool;
import by.asrohau.iShop.dao.exception.DAOException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionPoolImpl implements ConnectionPool{

    private final static Logger logger = Logger.getLogger(ConnectionPoolImpl.class);
    private static boolean driverIsLoaded = false;


    public ConnectionPoolImpl() {
        try {
            getJDBCDriver();
            for (int i = 0; i < AMOUNT_OF_CONNECTIONS; i++) {
                availableConnections.add(getConnection());
            }
        } catch (DAOException e) {}
        logger.info("availableConnections.size() is " + availableConnections.size());
        logger.info("takenConnections.size() is " + takenConnections.size());
    }

    /*
    provides with a database connection if available according to connection pool
     */
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

    /*
    returns connection back to the connection pool
     */
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

    /*
    creates a connection
     */
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

    /*
    method loads database driver
     */
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

    /*
    in case of any fatal errors we can stop tomcat
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
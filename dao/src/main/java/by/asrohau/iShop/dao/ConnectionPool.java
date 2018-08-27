package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.exception.DAOException;

import java.sql.Connection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static by.asrohau.iShop.dao.DAOFinals.*;


public interface ConnectionPool {

    DatabaseConfigReader databaseConfigReader = DAOFactory.getDatabaseConfigReader();
    String DRIVER = databaseConfigReader.get(SQL_DRIVER);
    String URL = databaseConfigReader.get(DB_URL);
    String USER = databaseConfigReader.get(DB_LOGIN);
    String PASSWORD = databaseConfigReader.get(DB_PASSWORD);
    String SETTINGS = databaseConfigReader.get(DB_SETTINGS);
    String FIXED_URL = URL + "?user=" + USER + "&password=" + PASSWORD + "&" + SETTINGS;
    int AMOUNT_OF_CONNECTIONS = Integer.parseInt(databaseConfigReader.get(DB_CONNECTIONS));
    BlockingQueue<Connection> availableConnections = new ArrayBlockingQueue<>(AMOUNT_OF_CONNECTIONS);
    BlockingQueue<Connection> takenConnections = new ArrayBlockingQueue<>(AMOUNT_OF_CONNECTIONS);

    Connection provide() throws DAOException;
    void retrieve(Connection connection) throws DAOException;
}

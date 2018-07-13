package by.asrohau.iShop.dao.util;

import by.asrohau.iShop.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class JDBCFactory {

	private final static Logger logger = Logger.getLogger(JDBCFactory.class);

	private static DatabaseConfigReader databaseConfigReader;
	static {
		try {
			databaseConfigReader = new DatabaseConfigReader();
		}catch (DAOException e){
			//...//
		}
	}

	private static boolean driverIsLoaded = false;

	public static Connection getConnection() {
		try {
			Connection connection = null;
			getJDBCDriver();
			connection = DriverManager.getConnection(
					databaseConfigReader.get(DAOFinals.DB_URL_FIXED.inString),
					databaseConfigReader.get(DAOFinals.DB_LOGIN.inString),
					databaseConfigReader.get(DAOFinals.DB_PASSWORD.inString));
			return connection;
		} catch (SQLException e) {
			logger.error(DAOFinals.CONNECTION_FAILED.inString);
			return null;
		}
	}

	private static void getJDBCDriver() {
		if (!driverIsLoaded) {
			try {
				Class.forName(databaseConfigReader.get(DAOFinals.SQL_DRIVER.inString));
				driverIsLoaded = true;
			} catch (ClassNotFoundException e) {
				logger.error(DAOFinals.MYSQL_DRIVER_NOT_LOADED.inString);
				e.printStackTrace();
			}
		}
	}
}
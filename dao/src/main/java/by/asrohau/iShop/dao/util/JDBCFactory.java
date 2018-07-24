package by.asrohau.iShop.dao.util;

import by.asrohau.iShop.dao.exception.DAOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import static by.asrohau.iShop.dao.util.DAOFinals.*;

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
					databaseConfigReader.get(DB_URL_FIXED.inString),
					databaseConfigReader.get(DB_LOGIN.inString),
					databaseConfigReader.get(DB_PASSWORD.inString));
			return connection;
		} catch (SQLException e) {
			logger.error(CONNECTION_FAILED.inString);
			return null;
		}
	}

	private static void getJDBCDriver() {
		if (!driverIsLoaded) {
			try {
				Class.forName(databaseConfigReader.get(SQL_DRIVER.inString));
				driverIsLoaded = true;
			} catch (ClassNotFoundException e) {
				logger.error(MYSQL_DRIVER_NOT_LOADED.inString);
				e.printStackTrace();
			}
		}
	}
}
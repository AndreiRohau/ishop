package by.asrohau.iShop.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class JDBCFactory {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/shop";
	private static final String DB_FIXED_URL = DB_URL + "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true"
			+ "&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String DB_LOGIN = "root";
	private static final String DB_PASSWORD = "root";
	private static final String SQL_DRIVER = "com.mysql.jdbc.Driver";
	private static boolean driverIsLoaded = false;

	public static Connection getConnection() {
		try {
			Connection connection = null;
			getJDBCDriver();
			connection = DriverManager.getConnection(DB_FIXED_URL, DB_LOGIN, DB_PASSWORD);
			return connection;
		} catch (SQLException e) {
			System.out.println("Connection to database failed.");//stub
			return null;
		}
	}

	public static void getJDBCDriver() {
		if (!driverIsLoaded) {
			try {
				Class.forName(SQL_DRIVER);
				driverIsLoaded = true;
			} catch (ClassNotFoundException e) {
				System.out.println("MySQL driver is not loaded");
				e.printStackTrace();
			}
		}
	}
}

//public class JDBCFactory {
//	private static LocalPropertiess properties = new LocalPropertiess();
//	private static final String DB_URL = "db.url";
//	private static final String DB_LOGIN = "db.login";
//	private static final String DB_PASSWORD = "db.pass";
//	private static final String SQL_DRIVER = "db.mysql.driver";
//	private static boolean driverIsLoaded = false;
//	public static Connection getConnection() {
//		try {
//			Connection connection = null;
//			getJDBCDriver();
//			//fix URL
//			String fixedURL = properties.get(DB_URL) + "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
//					"&useLegacyDatetimeCode=false&serverTimezone=UTC";
//			connection = DriverManager.getConnection(fixedURL, properties.get(DB_LOGIN), properties.get(DB_PASSWORD));
//
//			return connection;
////		} catch (ClassNotFoundException e) {
////			e.printStackTrace();
////			return null;
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
//	}
//	
//	public static void getJDBCDriver() {
//		if (!driverIsLoaded) {
//			try {
//				Class.forName(SQL_DRIVER);
//				driverIsLoaded = true;
//			} catch (ClassNotFoundException e) {
//				System.out.println("MySQL driver is not loaded");
//				e.printStackTrace();
//			}
//		}
//	}
//}

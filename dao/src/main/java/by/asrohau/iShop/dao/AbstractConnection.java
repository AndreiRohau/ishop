package by.asrohau.iShop.dao;

import java.sql.Connection;
import java.util.List;

import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.util.JDBCFactory;

public abstract class AbstractConnection {

	protected Connection connection;
	
	protected Connection getConnection() {
		return this.connection = JDBCFactory.getConnection();	
	}


}

package by.asrohau.iShop.dao;

import java.sql.Connection;

import by.asrohau.iShop.dao.util.JDBCFactory;

public abstract class AbstractDAO<T> {

	protected Connection connection;
	
	protected Connection getConnection() {
		return this.connection = JDBCFactory.getConnection();	
	}
	
}

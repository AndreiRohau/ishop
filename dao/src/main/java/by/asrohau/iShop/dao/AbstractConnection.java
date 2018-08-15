package by.asrohau.iShop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.util.JDBCFactory;

import static by.asrohau.iShop.dao.util.DAOFinals.ERROR_IN_DAO_METHOD_FINAL_BLOCK;

public abstract class AbstractConnection {

	protected Connection connection;
	
	protected Connection getConnection() {
		return this.connection = JDBCFactory.getConnection();	
	}

	/*
	closing ResultSet, PreparedStatement, Connection overloaded method
	 */
	protected void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection) throws DAOException {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
		}
		try {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
		}
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
		}
	}
	protected void close(PreparedStatement preparedStatement, Connection connection) throws DAOException {
		try {
			if(preparedStatement != null) {
				preparedStatement.close();
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
		}
		try {
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new DAOException(ERROR_IN_DAO_METHOD_FINAL_BLOCK.inString, e);
		}
	}

}

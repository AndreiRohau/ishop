package by.asrohau.iShop.dao;

import java.sql.Connection;
import java.util.List;

import by.asrohau.iShop.dao.exception.DAOException;
import by.asrohau.iShop.dao.util.JDBCFactory;

public abstract class AbstractDAO<T> {

	protected Connection connection;
	
	protected Connection getConnection() {
		return this.connection = JDBCFactory.getConnection();	
	}
//
//	/*
//	abstract create, same as SAVE
//	 */
//	public abstract boolean save(T t) throws DAOException;
//
//	/*
//	abstract read, same as FIND
//	 */
//	public abstract T find(T t) throws DAOException;
//
//	/*
//	update
//	 */
//	public abstract boolean update(T t) throws DAOException;
//
//	/*
//	delete
//	 */
//	public abstract boolean delete(T t) throws DAOException;
//
//	/*
//	find all
//	 */
//	public abstract List<T> findAll(T t) throws DAOException;
//
//	/*
//	count all
//	 */
//	public abstract long countAll() throws DAOException;

}

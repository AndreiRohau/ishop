package by.asrohau.iShop.dao;

import by.asrohau.iShop.dao.exception.DAOException;

import java.util.List;

/**
 * abstract CRUD, findOne(long id), findAll(int row) and countAll()
 * @param <T> any entity
 */
public interface EntityFacadeFootprint<T> {

    /**
     * saves entity
     * @param t is a saving entity
     * @return true if successful, otherwise false
     * @throws DAOException is a module exception
     */
    boolean save(T t) throws DAOException;

    /**
     * finds entity, is specific for each entity
     * @param t includes searching criteria
     * @return entity
     * @throws DAOException is a module exception
     */
    T find(T t) throws DAOException;

    /**
     * finds entity by its id
     * @param id of the entity
     * @return found entity
     * @throws DAOException is a module exception
     */
    T findOne(long id) throws DAOException;

    /**
     * updates entity, can be specific for each entity
     * @param t includes info for update
     * @return true if successful, otherwise false
     * @throws DAOException is a module exception
     */
    boolean update(T t) throws DAOException;

    /**
     * deletes entity
     * @param id of the entity
     * @return true if successful, otherwise false
     * @throws DAOException is a module exception
     */
    boolean delete(long id) throws DAOException;

    /**
     * finds list of entities, limited with amount of entities per page
     * @param row tells from which row to start the list
     * @return list of entities
     * @throws DAOException is a module exception
     */
    List<T> findAll(int row) throws DAOException;

    /**
     * counts the amount of all entities
     * @return number of entities in the table
     * @throws DAOException is a module exception
     */
    long countAll() throws DAOException;
}

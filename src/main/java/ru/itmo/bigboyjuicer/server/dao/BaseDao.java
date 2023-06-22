package ru.itmo.bigboyjuicer.server.dao;

import ru.itmo.bigboyjuicer.server.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashSet;

public interface BaseDao<K, T> {
    LinkedHashSet<T> findAll() throws DaoException;
    T findEntityById(K id) throws DaoException;
    boolean delete(K id) throws DaoException;
    boolean create(T t) throws  DaoException;
    T update(T t) throws DaoException;
    default void close(Statement statement) {
        try {
            if(statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    default void close(Connection connection) {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

package com.sidapps.moviediscourse.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {

    private static final String URL = "jdbc:mysql://localhost:3306/moviediscourse";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;
    protected abstract String getTableName();

    public void create(T entity) {
    }

    public void update(T entity) {
    }

    public List<T> getAll() {
		return null;
    }

    public void delete(T entity) {
    }
}

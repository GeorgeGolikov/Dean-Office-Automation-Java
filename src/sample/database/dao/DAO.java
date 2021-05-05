package sample.database.dao;

import javafx.collections.ObservableList;

import java.sql.Connection;

public abstract class DAO<T> {
    protected static Connection conn;

    public abstract ObservableList<T> findAll();
    public abstract void save(T t);
    public abstract void delete(T t);
    public abstract void update(T _old, T _new);
}

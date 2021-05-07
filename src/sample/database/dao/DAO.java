package sample.database.dao;

import javafx.collections.ObservableList;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T> {
    protected static Connection conn;

    public abstract ObservableList<T> findAll();
    public abstract void save(T t);
    public abstract void delete(T t);
    public abstract void update(T _old, T _new);

    public abstract List<Integer> countDependencies(T t);

    protected static List<Integer> getCount(CallableStatement cstmt) throws SQLException {
        try (ResultSet rs = cstmt.getObject(2, ResultSet.class)) {
            List<Integer> res = new ArrayList<>();
            if (rs.next()) {
                res.add(rs.getInt(1));
            } else
                res = null;
            return res;
        }
    }
}

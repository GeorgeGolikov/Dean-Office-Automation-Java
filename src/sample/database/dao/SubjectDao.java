package sample.database.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.database.DBConnection;
import sample.entity.Subject;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class SubjectDao extends DAO<Subject> {
    public SubjectDao() throws SQLException {
        conn = DBConnection.getInstance();
    }

    @Override
    public ObservableList<Subject> findAll() {
        try (CallableStatement cstmt = conn.prepareCall("{call get_subjects(?)}")) {
            cstmt.registerOutParameter(1, Types.REF_CURSOR);
            cstmt.executeQuery();

            try (ResultSet rs = cstmt.getObject(1, ResultSet.class)) {
                ObservableList<Subject> res = FXCollections.observableArrayList();
                while(rs.next()) {
                    res.add(new Subject(rs.getString(1)));
                }
                return res;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Subject subject) {
        try (CallableStatement cstmt = conn.prepareCall("{call add_subjects(?)}")) {
            cstmt.setString(1, subject.getName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Subject subject) {
        try (CallableStatement cstmt = conn.prepareCall("{call del_subjects(?)}")) {
            cstmt.setString(1, subject.getName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Subject _old, Subject _new) {
        try (CallableStatement cstmt = conn.prepareCall("{call upd_subjects(?,?)}")) {
            cstmt.setString(1, _old.getName());
            cstmt.setString(2, _new.getName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

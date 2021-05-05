package sample.database.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.database.DBConnection;
import sample.entity.Student;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class StudentDao extends DAO<Student> {
    public StudentDao() throws SQLException {
        conn = DBConnection.getInstance();
    }

    @Override
    public ObservableList<Student> findAll() {
        try (CallableStatement cstmt = conn.prepareCall("{call get_students(?)}")) {
            cstmt.registerOutParameter(1, Types.REF_CURSOR);
            cstmt.executeQuery();

            try (ResultSet rs = cstmt.getObject(1, ResultSet.class)) {
                ObservableList<Student> res = FXCollections.observableArrayList();
                while(rs.next()) {
                    res.add(new Student(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3),
                                rs.getString(4),
                                rs.getString(5)
                            )
                    );
                }
                return res;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public void save(Student student) {
        try (CallableStatement cstmt = conn.prepareCall("{call add_students(?,?,?,?)}")) {
            cstmt.setString(1, student.getFirstName());
            cstmt.setString(2, student.getLastName());
            cstmt.setString(3, student.getFatherName());
            cstmt.setString(4, student.getGroupName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Student student) {
        try (CallableStatement cstmt = conn.prepareCall("{call del_students(?)}")) {
            cstmt.setInt(1, student.getId());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Student _old, Student _new) {
        try (CallableStatement cstmt = conn.prepareCall("{call upd_students(?,?,?,?,?)}")) {
            cstmt.setInt(1, _old.getId());
            cstmt.setString(2, _new.getFirstName());
            cstmt.setString(3, _new.getLastName());
            cstmt.setString(4, _new.getFatherName());
            cstmt.setString(5, _new.getGroupName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

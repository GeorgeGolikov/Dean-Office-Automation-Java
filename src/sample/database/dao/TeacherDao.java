package sample.database.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.database.DBConnection;
import sample.entity.Teacher;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class TeacherDao extends DAO<Teacher> {
    public TeacherDao() throws SQLException {
        conn = DBConnection.getInstance();
    }

    @Override
    public ObservableList<Teacher> findAll() {
        try (CallableStatement cstmt = conn.prepareCall("{call get_teachers(?)}")) {
            cstmt.registerOutParameter(1, Types.REF_CURSOR);
            cstmt.executeQuery();

            try (ResultSet rs = cstmt.getObject(1, ResultSet.class)) {
                ObservableList<Teacher> res = FXCollections.observableArrayList();
                while(rs.next()) {
                    res.add(new Teacher(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4)
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
    public void save(Teacher teacher) {
        try (CallableStatement cstmt = conn.prepareCall("{call add_teachers(?,?,?)}")) {
            cstmt.setString(1, teacher.getFirstName());
            cstmt.setString(2, teacher.getLastName());
            cstmt.setString(3, teacher.getFatherName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Teacher teacher) {
        try (CallableStatement cstmt = conn.prepareCall("{call del_teachers(?)}")) {
            cstmt.setInt(1, teacher.getId());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Teacher _old, Teacher _new) {
        try (CallableStatement cstmt = conn.prepareCall("{call upd_teachers(?,?,?,?)}")) {
            cstmt.setInt(1, _old.getId());
            cstmt.setString(2, _new.getFirstName());
            cstmt.setString(3, _new.getLastName());
            cstmt.setString(4, _new.getFatherName());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Integer> countDependencies(Teacher teacher) {
        try (CallableStatement cstmt = conn.prepareCall("{call count_teach_dependencies(?,?)}")) {
            cstmt.setInt(1, teacher.getId());
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.executeQuery();

            return getCount(cstmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

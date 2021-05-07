package sample.database.dao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.database.DBConnection;
import sample.entity.Mark;
import sample.entity.Student;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class MarkDao extends DAO<Mark> {
    public MarkDao() throws SQLException {
        conn = DBConnection.getInstance();
    }

    @Override
    public ObservableList<Mark> findAll() {
        return null;
    }

    public ObservableList<Mark> findAllByStudent(Student student) {
        try (CallableStatement cstmt = conn.prepareCall("{call get_marks(?,?)}")) {
            cstmt.setInt(1, student.getId());
            cstmt.registerOutParameter(2, Types.REF_CURSOR);
            cstmt.executeQuery();

            try (ResultSet rs = cstmt.getObject(2, ResultSet.class)) {
                ObservableList<Mark> res = FXCollections.observableArrayList();
                while(rs.next()) {
                    res.add(new Mark(
                                    rs.getInt(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4),
                                    rs.getInt(5)
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
    public void save(Mark mark) {
        try (CallableStatement cstmt = conn.prepareCall("{call add_marks(?,?,?,?)}")) {
            cstmt.setInt(1, mark.getStudentId());
            cstmt.setString(2, mark.getSubjectName());
            cstmt.setInt(3, mark.getTeacherId());
            cstmt.setInt(4, mark.getValue());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Mark mark) {
        try (CallableStatement cstmt = conn.prepareCall("{call del_marks(?)}")) {
            cstmt.setInt(1, mark.getId());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Mark _old, Mark _new) {
        try (CallableStatement cstmt = conn.prepareCall("{call upd_marks(?,?)}")) {
            cstmt.setInt(1, _old.getId());
            cstmt.setInt(2, _new.getValue());
            cstmt.executeQuery();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Integer> countDependencies(Mark mark) {
        return null;
    }
}

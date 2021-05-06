package sample.database.dao;

import javafx.collections.ObservableList;
import sample.database.DBConnection;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class AverageMarkDao extends DAO<Double> {
    public AverageMarkDao() throws SQLException {
        conn = DBConnection.getInstance();
    }

    public String calcPerfStud(String start, String end, Integer studentId) {
        try (CallableStatement cstmt = conn.prepareCall("{call calcPerfStud(?,?,?,?)}")) {
            return getAverageMarkStudAndTeach(start, end, studentId, cstmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public String calcPerfTeacher(String start, String end, Integer teacherId) {
        try (CallableStatement cstmt = conn.prepareCall("{call calcPerfTeacher(?,?,?,?)}")) {
            return getAverageMarkStudAndTeach(start, end, teacherId, cstmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String getAverageMarkStudAndTeach(String start, String end, Integer id,
                                              CallableStatement cstmt) throws SQLException {
        cstmt.setString(1, start);
        cstmt.setString(2, end);
        cstmt.setInt(3, id);
        cstmt.registerOutParameter(4, Types.REF_CURSOR);
        cstmt.executeQuery();

        ResultSet rs = cstmt.getObject(4, ResultSet.class);
        if (rs.next()) {
            return String.valueOf(rs.getFloat(1));
        }
        return null;
    }

    public String calcPerfGroup(String start, String end, String groupName) {
        try (CallableStatement cstmt = conn.prepareCall("{call calcPerfGroup(?,?,?,?)}")) {
            return getAverageMarkGroupAndSubj(start, end, groupName, cstmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public String calcPerfSubj(String start, String end, String subjName) {
        try (CallableStatement cstmt = conn.prepareCall("{call calcPerfSubj(?,?,?,?)}")) {
            return getAverageMarkGroupAndSubj(start, end, subjName, cstmt);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private String getAverageMarkGroupAndSubj(String start, String end, String name,
                                              CallableStatement cstmt) throws SQLException {
        cstmt.setString(1, start);
        cstmt.setString(2, end);
        cstmt.setString(3, name);
        cstmt.registerOutParameter(4, Types.REF_CURSOR);
        cstmt.executeQuery();

        ResultSet rs = cstmt.getObject(4, ResultSet.class);
        if (rs.next()) {
            return String.valueOf(rs.getFloat(1));
        }
        return null;
    }

    @Override
    public ObservableList<Double> findAll() {
        return null;
    }

    @Override
    public void save(Double aDouble) {

    }

    @Override
    public void delete(Double aDouble) {

    }

    @Override
    public void update(Double _old, Double _new) {

    }
}

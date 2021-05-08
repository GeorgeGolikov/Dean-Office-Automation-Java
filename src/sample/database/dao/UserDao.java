package sample.database.dao;

import sample.database.DBConnection;

import java.sql.*;

public class UserDao {
    private static Connection conn;

    public UserDao() throws SQLException {
        conn = DBConnection.getInstance();
    }

    public boolean selectUserFromDb(String login, String password) {
        try (CallableStatement cstmt = conn.prepareCall("{call get_user(?,?,?)}")) {
            cstmt.registerOutParameter(1, Types.REF_CURSOR);
            cstmt.setString(2, login);
            cstmt.setString(3, password);
            cstmt.executeQuery();

            try (ResultSet rs = cstmt.getObject(1, ResultSet.class)) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}

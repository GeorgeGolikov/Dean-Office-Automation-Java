package sample.database;

import oracle.jdbc.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Connection with db is Singleton
public final class DBConnection {
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String user = "c##my_user";
        String password = "my_pass";
        DriverManager.registerDriver(new OracleDriver());
        Connection connection = DriverManager.getConnection(url, user, password);

        if (connection.isValid(1)) {
            System.out.println("Connection successful!\n");
        }

        return connection;
    }

    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        if (connection == null)
            connection = getConnection();
        return connection;
    }

    private DBConnection() {

    }
}

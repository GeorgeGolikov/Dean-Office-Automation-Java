package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Second;
import sample.database.DBConnection;

import java.sql.*;

public class AuthController {
    private static Connection conn;

    @FXML
    private void initialize() {
        try {
            conn = DBConnection.getInstance();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        label.setText("");
    }

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Label label;

    public void openMainForm() {
        if (checkUser()) {
            Second second = new Second();
            try {
                second.showWindow();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            label.setText("Неверный логин или пароль");
        }
    }

    private boolean checkUser() {
        return selectUserFromDb();
    }

    // dao
    private boolean selectUserFromDb() {
        try (CallableStatement cstmt = conn.prepareCall("{call get_user(?,?,?)}")) {
            cstmt.registerOutParameter(1, Types.REF_CURSOR);
            cstmt.setString(2, login.getText());
            cstmt.setString(3, password.getText());
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

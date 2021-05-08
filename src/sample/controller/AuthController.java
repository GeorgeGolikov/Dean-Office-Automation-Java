package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.Second;
import sample.database.dao.*;
import sample.service.UserService;
import sample.service.impl.*;

import java.sql.*;

public class AuthController {
    private static UserService userService;

    static {
        try {
            userService = new UserServiceImpl(new UserDao());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void initialize() {
        label.setText("");
    }

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Label label;

    public void openMainForm() {
        if (userService.userExists(login.getText(), password.getText())) {
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
}

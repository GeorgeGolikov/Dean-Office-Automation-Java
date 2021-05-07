package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WarningController {
    Controller mainController;
    private boolean deletingFlag = false;
    private Stage stage;

    public void setMainController(Controller controller) {
        this.mainController = controller;
    }

    @FXML
    private void initialize() {
        stage = (Stage) label.getScene().getWindow();
        stage.setOnCloseRequest(e -> mainController.reallyDelete(deletingFlag));
    }

    @FXML
    private Label label;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    public void setLabelText(String text) {
        label.setText(text);
    }

    @FXML
    private void buttonOkClicked() {
        deletingFlag = true;
        stage.close();
    }

    @FXML
    private void cancelButtonClicked() {
        stage.close();
    }
}

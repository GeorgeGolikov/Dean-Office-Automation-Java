package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WarningController {
    Controller mainController;
    private boolean deletingFlag = false;

    public void setMainController(Controller controller) {
        this.mainController = controller;
    }

    @FXML
    private Label label;

    @FXML
    private Button okButton;

    @FXML
    private Button cancelButton;

    public void setLabelText(String text) {
        label.setText(text);
        label.setWrapText(true);
    }

    @FXML
    private void buttonOkClicked() {
        deletingFlag = true;
        closeStage();
    }

    @FXML
    private void cancelButtonClicked() {
        closeStage();
    }

    private void closeStage() {
        Stage stage = (Stage) label.getScene().getWindow();
        mainController.reallyDelete(deletingFlag);
        stage.close();
    }
}

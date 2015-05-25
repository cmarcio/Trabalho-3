package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by Marcio on 24/05/2015.
 */
public class AddUserController {
    @FXML private SplitMenuButton userType;
    @FXML private Button okBtn;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField userIdField;

    @FXML public void handleButtonAction(ActionEvent event) throws IOException {
        if (firstNameField.getCharacters().toString().isEmpty() || lastNameField.getCharacters().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(Main.getResourceBundle().getString("warning"));
            alert.setHeaderText(null);
            alert.setContentText(Main.getResourceBundle().getString("blankFieldWarning"));

            alert.showAndWait();
        }

    }
}

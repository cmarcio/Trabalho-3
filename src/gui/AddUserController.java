package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Marcio on 24/05/2015.
 */
public class AddUserController {
    @FXML private SplitMenuButton userType;
    @FXML private Button okBtn;
    @FXML private Button cancelBtn;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField userIdField;

    // Verify the conditions to processed with the application when the ok button is pressed
    // Create a new user when everything is correct
    @FXML public void handleOkButtonAction(ActionEvent event) throws IOException {
        if (firstNameField.getCharacters().toString().isEmpty() || lastNameField.getCharacters().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(Main.getResourceBundle().getString("warning"));
            alert.setHeaderText(null);
            alert.setContentText(Main.getResourceBundle().getString("blankFieldWarning"));

            alert.showAndWait();
        }
    }

    @FXML public void handleCancelButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        stage=(Stage) cancelBtn.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"), Main.getResourceBundle());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

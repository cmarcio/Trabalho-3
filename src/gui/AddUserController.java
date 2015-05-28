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
import library.users.Student;
import library.users.User;
import library.users.UsersFile;

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
    @FXML private TextField emailField;
    @FXML private TextField userIdField;

    @FXML public void handleOkButtonAction(ActionEvent event) throws IOException {
        // Cria um objeto de manipulação de dados
        UsersFile file = new UsersFile("UsersReg.txt");

        // Verifica se todos os campos já estão preenchidos
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty()
                || emailField.getText().isEmpty() || userIdField.getText().isEmpty()) {
            // Exibe uma mensagem de erro indicando que os campos não foram preenchidos
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(Main.getResourceBundle().getString("warning"));
            alert.setHeaderText(null);
            alert.setContentText(Main.getResourceBundle().getString("blankFieldWarning"));

            alert.showAndWait();
        }

        // Verifica se o número passado como id é válido
        else if (! isValidNumber(userIdField)) {
            // Exibe mensagem de erro
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(Main.getResourceBundle().getString("warning"));
            alert.setHeaderText(Main.getResourceBundle().getString("invalidEntry"));
            alert.setContentText(Main.getResourceBundle().getString("onlyNumber"));

            alert.showAndWait();
        }

        // Verifica se o usuário já não foi cadastrado
        else if (file.searchID(userIdField.getText()) != null) {
            // Exibe mensagem de erro
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(Main.getResourceBundle().getString("error"));
            alert.setHeaderText(Main.getResourceBundle().getString("invalidEntry"));
            alert.setContentText(Main.getResourceBundle().getString("userAlreadyRegistered"));

            alert.showAndWait();
        }

        // Cria o novo usuário e adiciona ao arquivo
        else {
            // Cria um objeto do tipo usuário
            User user = new Student(firstNameField.getText(), lastNameField.getText(), emailField.getText(), Long.parseLong(userIdField.getText()));
            // Salva o usuário
            file.storeUser(user);

            // Exibe mensagem de exito
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(Main.getResourceBundle().getString("success"));
            alert.setHeaderText(null);
            alert.setContentText(Main.getResourceBundle().getString("userRegistered"));
            alert.showAndWait();

            // Volta ao menu inicial
            Stage stage=(Stage) cancelBtn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"), Main.getResourceBundle());

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    // Se o botão cancelar for pressionado, volta ao menu principal
    @FXML public void handleCancelButtonAction(ActionEvent event) throws IOException {
        Stage stage=(Stage) cancelBtn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"), Main.getResourceBundle());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private boolean isValidNumber(TextField field) {
        String word = field.getText();
        for (int i = 0; i < word.length(); i++) {
            if (! Character.isDigit(word.charAt(i))) return false;
        }
        return true;
    }

    private boolean isValidName(TextField field) {
        String word = field.getText();
        for (int i = 0; i < word.length(); i++) {
            if ( ! Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }
}


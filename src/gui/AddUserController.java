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

import java.io.File;
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

    // Verifica se todos os campos j� est�o preenchido
    // Verifica se o usu�rio j� existe
    // Se essas condi��es forem satisfeitas o novo usu�rio � criado e colocado no arquivo de dados
    @FXML public void handleOkButtonAction(ActionEvent event) throws IOException {
        // Exibe uma mensagem de erro indicando que os campos n�o foram preenchidos
        if (firstNameField.getCharacters().toString().isEmpty() || lastNameField.getCharacters().toString().isEmpty() || emailField.getCharacters().toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(Main.getResourceBundle().getString("warning"));
            alert.setHeaderText(null);
            alert.setContentText(Main.getResourceBundle().getString("blankFieldWarning"));

            alert.showAndWait();
        }

        // Cria o novo usu�rio e adiciona ao arquivo
        else {
            // Cria um objeto de manipul��o de dados
            UsersFile file = new UsersFile("UsersReg.txt");
            // Cria um objeto do tipo usu�rio
            User user = new Student(firstNameField.getText(), lastNameField.getText(), emailField.getText());
            // Salva o usu�rio
            file.saveUser(user);
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

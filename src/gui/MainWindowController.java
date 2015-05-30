package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    @FXML private Button addUser;
    @FXML private Button addBook;
    @FXML private Button borrowBook;
    @FXML private Button returnBook;

    @FXML void handleButtonAction(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;
        // Se o botão de cadastrar usuário for selecionado
        if(event.getSource() == addUser){
            //get reference to the button's stage
            stage = (Stage) addUser.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("addUserWindow.fxml"), Main.getResourceBundle());
        }
        else if (event.getSource() == addBook) {
            stage = (Stage) addBook.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("addBookWindow.fxml"), Main.getResourceBundle());
        }
        else{
            stage=(Stage) addUser.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"), Main.getResourceBundle());
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // Exibe um warning contendo as strings internacionalizadas passadas como parâmetro
    public void showWarning(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(Main.getResourceBundle().getString(title));
        alert.setHeaderText(Main.getResourceBundle().getString(header));
        alert.setContentText(Main.getResourceBundle().getString(message));
        alert.showAndWait();
    }

    // Exibe um mensagem de erro contendo as strings internacionalizadas passadas como parâmetro
    public void showError(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(Main.getResourceBundle().getString(title));
        alert.setHeaderText(Main.getResourceBundle().getString(header));
        alert.setContentText(Main.getResourceBundle().getString(message));
        alert.showAndWait();
    }

    // Exibe um mensagem informativa contendo as strings internacionalizadas passadas como parâmetro
    public void showInformation(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(Main.getResourceBundle().getString(title));
        alert.setHeaderText(Main.getResourceBundle().getString(header));
        alert.setContentText(Main.getResourceBundle().getString(message));
        alert.showAndWait();
    }

    // Volta ao menu principal
    public  void backToMain(Button btn) {
        Stage stage=(Stage) btn.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"), Main.getResourceBundle());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            System.err.println("Erro ao carregar janela");
            e.printStackTrace();
        }
    }
}

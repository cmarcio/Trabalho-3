package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    @FXML private Button addUser;
    @FXML private Button addBook;
    @FXML private Button borrowBook;
    @FXML private Button returnBook;
    @FXML private Button setDate;
    @FXML private DatePicker datePicker;
    //public Calendar programDate;

    public MainWindowController () {
        //programDate = new Calendar() {
        //};
    }

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
        else if (event.getSource() == borrowBook) {
            stage = (Stage) addBook.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("borrowBookWindow.fxml"), Main.getResourceBundle());
        }
        else if (event.getSource() == setDate) {
            stage = (Stage) addBook.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("setDateWindow.fxml"), Main.getResourceBundle());
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

    @FXML public void handleDatePicker(ActionEvent event) {
        System.out.println(datePicker.getEditor().getText());
    }

    // Exibe um warning contendo as strings passadas como parâmetro
    public void showWarning(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Exibe um mensagem de erro contendo as strings passadas como parâmetro
    public void showError(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Exibe um mensagem informativa contendo as strings passadas como parâmetro
    public void showInformation(String title, String header, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(message);
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
            System.err.println("ERROR LOADING WINDOW!");
            e.printStackTrace();
        }
    }

    // Verifica se o número digitado possui algum caractere não numérico
    public boolean isValidNumber(TextField field) {
        String word = field.getText();
        for (int i = 0; i < word.length(); i++) {
            if (! Character.isDigit(word.charAt(i))) return false;
        }
        return true;
    }
}

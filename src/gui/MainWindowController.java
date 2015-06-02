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
import java.util.GregorianCalendar;

public class MainWindowController {
    @FXML private Button addUser;
    @FXML private Button addBook;
    @FXML private Button borrowBook;
    @FXML private Button returnBook;
    @FXML private Button okBtn;
    @FXML private Button listRegisters;
    @FXML private DatePicker datePicker;
    public static GregorianCalendar programDate;

    //public MainWindowController () {
     //   programDate = new GregorianCalendar();
    //}

    @FXML void handleButtonAction(ActionEvent event) throws IOException {
        // Se o botão de cadastrar usuário for selecionado
        if(event.getSource() == addUser)
            loadWindow(addUser, "addUserWindow.fxml");

        else if (event.getSource() == addBook)
            loadWindow(addBook, "addBookWindow.fxml");

        else if (event.getSource() == borrowBook)
            loadWindow(borrowBook, "borrowBookWindow.fxml");

        else if (event.getSource() == returnBook)
            loadWindow(borrowBook, "returnBookWindow.fxml");

        else if (event.getSource() == listRegisters)
            loadWindow(listRegisters, "listRegistersWindow.fxml");
        else
            loadWindow(borrowBook, "mainWindow.fxml");
    }

    @FXML public void handleDatePicker(ActionEvent event) {
        String[] date = datePicker.getEditor().getText().split("/");
        programDate = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]));
        showInformation(Main.getResourceBundle().getString("dateChanged"), null, datePicker.getEditor().getText());
    }

    @FXML public void handleOkButton(ActionEvent event) {
        if (datePicker.getEditor().getText().isEmpty()) {
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("chooseDate"));
        }
        else {
            Stage stage = (Stage) okBtn.getScene().getWindow();
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
    public void backToMain(Button btn) {
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

    // Carrega uma janela
    public void loadWindow(Button btn, String fxml) {
        Stage stage=(Stage) btn.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(fxml), Main.getResourceBundle());
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

    public static GregorianCalendar getProgramDate() {
        return programDate;
    }
}

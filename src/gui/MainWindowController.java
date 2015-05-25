package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        if(event.getSource() == addUser){
            //get reference to the button's stage
            stage = (Stage) addUser.getScene().getWindow();
            //load up OTHER FXML document
            root = FXMLLoader.load(getClass().getResource("addUserWindow.fxml"), Main.getResourceBundle());
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
}

package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.users.User;
import library.users.UsersFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Marcio on 02/06/2015.
 */
public class ListRegistersController extends MainWindowController implements Initializable{
    @FXML private Button cancelBtn;
    @FXML private Button listUsersBtn;
    @FXML private Button listBooksBtn;
    @FXML private Button listBorrowsBtn;

    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, String> firstNameCol;
    @FXML private TableColumn<User, String> lastNameCol;

    private ObservableList<User> userData = FXCollections.observableArrayList();
    private ObservableList<User> bookData = FXCollections.observableArrayList();


    @FXML private void handleTableView(ActionEvent event) {

    }

    @FXML private void handleCancelButton(ActionEvent event) {
        backToMain(cancelBtn);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializa todas as observebleLists que contem o s dados dos usuários, livros e empréstimos
        UsersFile usersFile = new UsersFile("UsersReg.txt");
        ArrayList<User> list = usersFile.getUsersList();
        for (int i = 0; i < list.size(); i++) {
            userData.add(list.get(i));
        }

        firstNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));

        userTable.setItems(userData);
    }
}

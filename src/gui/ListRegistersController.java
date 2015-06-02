package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.BorrowFile;
import library.Register;
import library.books.Book;
import library.books.BooksFile;
import library.users.User;
import library.users.UsersFile;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Marcio on 02/06/2015.
 */
public class ListRegistersController extends MainWindowController implements Initializable{
    @FXML private Button cancelBtn;

    // Tabela de usuários
    @FXML private TableView<User> userTable;
    @FXML private TableColumn<User, String> firstNameCol;
    @FXML private TableColumn<User, String> lastNameCol;
    @FXML private TableColumn<User, String> emailCol;
    @FXML private TableColumn<User, Long> userIdCol;
    @FXML private TableColumn<User, String> groupCol;

    // Tabela de livros
    @FXML private TableView<Book> bookTable;
    @FXML private TableColumn<Book, String> titleCol;
    @FXML private TableColumn<Book, String> authorCol;
    @FXML private TableColumn<Book, Long> bookIdCol;
    @FXML private TableColumn<Book, String> publisherCol;
    @FXML private TableColumn<Book, Integer> yearCol;
    @FXML private TableColumn<Book, Integer> editionCol;
    @FXML private TableColumn<Book, String> bookGroupCol;

    //Tabela de empréstimos
    @FXML private TableView<Register> borrowTable;
    @FXML private TableColumn<Register, Long> borrowUserCol;
    @FXML private TableColumn<Register, Long> borrowBookCol;
    @FXML private TableColumn<Register, Integer> borrowYearCol;
    @FXML private TableColumn<Register, Integer> borrowMonthCol;
    @FXML private TableColumn<Register, Integer> borrowDayCol;
    @FXML private TableColumn<Register, Integer> borrowHourCol;
    @FXML private TableColumn<Register, Integer> borrowMinutesCol;


    private ObservableList<User> userData = FXCollections.observableArrayList();
    private ObservableList<Book> bookData = FXCollections.observableArrayList();
    private ObservableList<Register> borrowData = FXCollections.observableArrayList();

    @FXML private void handleCancelButton(ActionEvent event) {
        backToMain(cancelBtn);
    }

    @FXML
    public void initialize(URL location, ResourceBundle resources) {
        // Inicializa todas as observebleLists que contem o s dados dos usuários, livros e empréstimos
        // Lista de usuários
        UsersFile usersFile = new UsersFile("UsersReg.txt");
        ArrayList<User> userList = usersFile.getUsersList();
        for (int i = 0; i < userList.size(); i++) {
            userData.add(userList.get(i));
        }

        // Lista de livros
        BooksFile booksFile = new BooksFile("BooksReg.txt");
        ArrayList<Book> bookList = booksFile.getBookList();
        for (int i = 0; i < bookList.size(); i++) {
            bookData.add(bookList.get(i));
        }

        // Lista de emprestimos
        BorrowFile borrowFile = new BorrowFile("BorrowReg.txt");
        ArrayList<Register> borrowList = borrowFile.toRegisters();
        for (int i = 0; i < borrowList.size(); i++) {
            borrowData.add(borrowList.get(i));
        }

        // Inicializa as colunas da tabela usuários
        firstNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        emailCol.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
        userIdCol.setCellValueFactory(new PropertyValueFactory<User, Long>("userId"));
        groupCol.setCellValueFactory(new PropertyValueFactory<User, String>("group"));

        // Inicializa as colunas da tabela livros
        titleCol.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<Book, String>("author"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<Book, String>("publisher"));
        bookIdCol.setCellValueFactory(new PropertyValueFactory<Book, Long>("bookNumber"));
        yearCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("year"));
        editionCol.setCellValueFactory(new PropertyValueFactory<Book, Integer>("edition"));
        bookGroupCol.setCellValueFactory(new PropertyValueFactory<Book, String>("bookType"));

        // Inicializa as colunas da tabela livros
        borrowUserCol.setCellValueFactory(new PropertyValueFactory<Register, Long>("userId"));
        borrowBookCol.setCellValueFactory(new PropertyValueFactory<Register, Long>("bookId"));
        borrowYearCol.setCellValueFactory(new PropertyValueFactory<Register, Integer>("year"));
        borrowMonthCol.setCellValueFactory(new PropertyValueFactory<Register, Integer>("month"));
        borrowDayCol.setCellValueFactory(new PropertyValueFactory<Register, Integer>("day"));
        borrowHourCol.setCellValueFactory(new PropertyValueFactory<Register, Integer>("hour"));
        borrowMinutesCol.setCellValueFactory(new PropertyValueFactory<Register, Integer>("minutes"));

        userTable.setItems(userData);
        bookTable.setItems(bookData);
        borrowTable.setItems(borrowData);
    }
}

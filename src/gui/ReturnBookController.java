package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import library.books.Book;
import library.books.BooksFile;
import library.books.BorrowFile;
import library.books.ReturnFile;
import library.users.User;
import library.users.UsersFile;

/**
 * Created by Marcio on 01/06/2015.
 */
public class ReturnBookController extends MainWindowController{
    @FXML
    private Button okBtn;
    @FXML private Button cancelBtn;
    @FXML private TextField bookId;
    @FXML private TextField userId;

    @FXML public void handleOkButton(ActionEvent event) {
        // Cria um objeto de manipulação de dados
        UsersFile userFile = new UsersFile("UsersReg.txt");
        BooksFile bookFile = new BooksFile("BooksReg.txt");
        ReturnFile returnFile = new ReturnFile("ReturnReg.txt");
        Book book = null;
        User user = null;

        // Verifica se todos os campos já estão preenchidos
        if (bookId.getText().isEmpty() || userId.getText().isEmpty())
            showWarning(Main.getResourceBundle().getString("warning"), null, Main.getResourceBundle().getString("blankFieldWarning"));

        // Verifica se os números passado como id é válido
        else if (! isValidNumber(bookId) || ! isValidNumber(userId))
            showWarning(Main.getResourceBundle().getString("warning"), null, Main.getResourceBundle().getString("onlyNumbersId"));

        // Verifica se o usuário está cadastrado
        else if (userFile.getUsersFile().exists() && (user = userFile.searchID(userId.getText())) == null)
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("userIdAbsent"));

        // Verifica se o livro está cadastrado
        else if (bookFile.getBooksFile().exists() && (book = bookFile.searchID(bookId.getText())) == null)
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("bookIdAbsent"));

            // Verifica se o livro está indisponível

            // Verifica se o usuário está bloqueado


        // Atualiza o arquivo de devoluções
        else {
            // Registra a devoluçao
            returnFile.storeReturn(book, user, getProgramDate());

            // Exibe mensagem de exito
            showInformation(Main.getResourceBundle().getString("success"), null, Main.getResourceBundle().getString("bookReturned"));

            backToMain(okBtn);
        }
    }

    @FXML public void handleCancelButton(ActionEvent event) {
        backToMain(cancelBtn);
    }
}

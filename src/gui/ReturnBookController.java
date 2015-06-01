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
        // Cria um objeto de manipula��o de dados
        UsersFile userFile = new UsersFile("UsersReg.txt");
        BooksFile bookFile = new BooksFile("BooksReg.txt");
        ReturnFile returnFile = new ReturnFile("ReturnReg.txt");
        Book book = null;
        User user = null;

        // Verifica se todos os campos j� est�o preenchidos
        if (bookId.getText().isEmpty() || userId.getText().isEmpty())
            showWarning(Main.getResourceBundle().getString("warning"), null, Main.getResourceBundle().getString("blankFieldWarning"));

        // Verifica se os n�meros passado como id � v�lido
        else if (! isValidNumber(bookId) || ! isValidNumber(userId))
            showWarning(Main.getResourceBundle().getString("warning"), null, Main.getResourceBundle().getString("onlyNumbersId"));

        // Verifica se o usu�rio est� cadastrado
        else if (userFile.getUsersFile().exists() && (user = userFile.searchID(userId.getText())) == null)
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("userIdAbsent"));

        // Verifica se o livro est� cadastrado
        else if (bookFile.getBooksFile().exists() && (book = bookFile.searchID(bookId.getText())) == null)
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("bookIdAbsent"));

            // Verifica se o livro est� indispon�vel

            // Verifica se o usu�rio est� bloqueado


        // Atualiza o arquivo de devolu��es
        else {
            // Registra a devolu�ao
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

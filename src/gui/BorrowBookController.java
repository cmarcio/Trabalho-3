package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import library.ReturnFile;
import library.books.Book;
import library.books.BooksFile;
import library.BorrowFile;
import library.users.*;

import java.util.Calendar;

/**
 * Created by Marcio on 30/05/2015.
 */
public class BorrowBookController extends MainWindowController {
    @FXML private Button okBtn;
    @FXML private Button cancelBtn;
    @FXML private TextField bookId;
    @FXML private TextField userId;

    @FXML public void handleOkButton(ActionEvent event) {
        // Cria um objeto de manipulação de dados
        UsersFile userFile = new UsersFile("UsersReg.txt");
        BooksFile bookFile = new BooksFile("BooksReg.txt");
        BorrowFile borrowFile = new BorrowFile("BorrowReg.txt");
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
        else if (book != null && ! book.isAvailable(borrowFile, returnFile)) {
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("bookUnavailable"));
        }

        // Verifica se o usuário pode pegar livros
        else if (user != null && user.isBlocked(borrowFile, returnFile)) {
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("userBlocked"));
            if (user.getUnblockDate() != null) {
                String day = Integer.toString(user.getUnblockDate().get(Calendar.DAY_OF_MONTH));
                String month = Integer.toString(user.getUnblockDate().get(Calendar.MONTH));
                String year = Integer.toString(user.getUnblockDate().get(Calendar.YEAR));
                String date = day.concat("/").concat(month).concat("/").concat(year);
                showInformation(Main.getResourceBundle().getString("unblockDate"), null, date);
            }
        }

        // Verifica se já alugou o número máximo de livros
        else if(user != null && !user.canBorrow()) {
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("maxBookNumber"));
        }

        // Atualiza o arquivo de empréstimos
        else {
            // Registra o empréstimo
            borrowFile.storeBorrow(book, user, getProgramDate());

            // Exibe mensagem de exito
            showInformation(Main.getResourceBundle().getString("success"), null, Main.getResourceBundle().getString("bookBorrowed"));

            backToMain(okBtn);
        }
    }

    @FXML public void handleCancelButton(ActionEvent event) {
        backToMain(cancelBtn);
    }
}

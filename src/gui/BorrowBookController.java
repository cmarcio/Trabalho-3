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
        // Cria um objeto de manipula��o de dados
        UsersFile userFile = new UsersFile("UsersReg.txt");
        BooksFile bookFile = new BooksFile("BooksReg.txt");
        BorrowFile borrowFile = new BorrowFile("BorrowReg.txt");
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
        else if (book != null && ! book.isAvailable(borrowFile, returnFile)) {
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("bookUnavailable"));
        }

        // Verifica se o usu�rio pode pegar livros
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

        // Verifica se j� alugou o n�mero m�ximo de livros
        else if(user != null && !user.canBorrow()) {
            showError(Main.getResourceBundle().getString("error"), null, Main.getResourceBundle().getString("maxBookNumber"));
        }

        // Atualiza o arquivo de empr�stimos
        else {
            // Registra o empr�stimo
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

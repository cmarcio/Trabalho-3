package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import library.books.*;

/**
 * Created by Marcio on 28/05/2015.
 */
public class AddBookController extends MainWindowController{
    @FXML private Button okBtn;
    @FXML private Button cancelBtn;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField publisherField;
    @FXML private TextField yearField;
    @FXML private TextField editionField;
    @FXML private SplitMenuButton typeMenu;
    @FXML private MenuItem textBookMenu;
    @FXML private MenuItem otherBookMenu;

    @FXML public void handleOkButton(ActionEvent event) {
        // Cria um objeto de manipulação de dados
        BooksFile file = new BooksFile("BooksReg.txt");

        // Verifica se todos os campos já estão preenchidos
        if (titleField.getText().isEmpty() || authorField.getText().isEmpty() || publisherField.getText().isEmpty()
                || yearField.getText().isEmpty() || editionField.getText().isEmpty())
            showWarning(Main.getResourceBundle().getString("warning"), null, Main.getResourceBundle().getString("blankFieldWarning"));

        // Verifica se o tipo de livro foi escolhido
        else if (typeMenu.getText().compareTo(Main.getResourceBundle().getString("group")) == 0)
            showWarning(Main.getResourceBundle().getString("warning"), null, Main.getResourceBundle().getString("blankFieldWarning"));

        // Verifica se o número passado como id é válido
        else if (! isValidNumber(yearField) || ! isValidNumber(editionField))
            showWarning(Main.getResourceBundle().getString("warning"), null, Main.getResourceBundle().getString("onlyNumber"));

        // Cria o novo usuário e adiciona ao arquivo
        else {
            String type = typeMenu.getText();

            // Cria um objeto do tipo usuário
            Book book;
            if (type.compareTo(Main.getResourceBundle().getString("textBook")) == 0)
                book = new TextBook(titleField.getText(), authorField.getText(), publisherField.getText(), Integer.parseInt(yearField.getText()), Integer.parseInt(editionField.getText()), -1);
            else
                book = new GeneralBook(titleField.getText(), authorField.getText(), publisherField.getText(), Integer.parseInt(yearField.getText()), Integer.parseInt(editionField.getText()), -1);

            // Salva o usuário
            file.storeBook(book);

            // Exibe mensagem de exito
            showInformation(Main.getResourceBundle().getString("success"), null, Main.getResourceBundle().getString("bookRegistered"));

            // Exibe o ID do livro adicionado
            showInformation(Main.getResourceBundle().getString("attention"), Main.getResourceBundle().getString("bookIdInformation"), Long.toString(book.getBookNumber()));

            backToMain(okBtn);
        }
    }

    @FXML public void handleSplitMenu(ActionEvent event) {
        if (event.getSource() == textBookMenu)
            typeMenu.setText(textBookMenu.getText());
        else
            typeMenu.setText(otherBookMenu.getText());
    }

    // Volta ao menu principal quando o botão cancelar é digitado
    @FXML public void handleCancelButton(ActionEvent event) {
        backToMain(cancelBtn);
    }
}

package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import library.users.*;

/**
 * Created by Marcio on 24/05/2015.
 */
public class AddUserController extends MainWindowController {
    @FXML private SplitMenuButton userType;
    @FXML private Button okBtn;
    @FXML private Button cancelBtn;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField userIdField;
    @FXML private MenuItem studentMenu;
    @FXML private MenuItem teacherMenu;
    @FXML private MenuItem communityMenu;


    @FXML public void handleOkButtonAction(ActionEvent event) {
        // Cria um objeto de manipulação de dados
        UsersFile file = new UsersFile("UsersReg.txt");

        // Verifica se todos os campos já estão preenchidos
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty()
                || userIdField.getText().isEmpty())
            showWarning("warning", null, "blankFieldWarning");

        // Verifica se o tipo de usuário foi escolhido
        else if (userType.getText().compareTo(Main.getResourceBundle().getString("group")) == 0)
            showWarning("warning", null, "blankFieldWarning");

        // Verifica se o número passado como id é válido
        else if (! isValidNumber(userIdField))
            showError("warning", "invalidEntry", "onlyNumber");

        // Verifica se o usuário já não foi cadastrado
        else if (file.searchID(userIdField.getText()) != null)
            showError("error", "invalidEntry", "userAlreadyRegistered");

        // Cria o novo usuário e adiciona ao arquivo
        else {
            String type = userType.getText();

            // Cria um objeto do tipo usuário
            User user;
            if (type.compareTo(Main.getResourceBundle().getString("student")) == 0)
                user = new Student(firstNameField.getText(), lastNameField.getText(), emailField.getText(), Long.parseLong(userIdField.getText()));
            else if (type.compareTo(Main.getResourceBundle().getString("teacher")) == 0)
                user = new Teacher(firstNameField.getText(), lastNameField.getText(), emailField.getText(), Long.parseLong(userIdField.getText()));
            else
                user = new CommunityMember(firstNameField.getText(), lastNameField.getText(), emailField.getText(), Long.parseLong(userIdField.getText()));

            // Salva o usuário
            file.storeUser(user);

            // Exibe mensagem de exito
            showInformation("success", null, "userRegistered");

            backToMain(okBtn);
        }
    }

    // Se o botão cancelar for pressionado, volta ao menu principal
    @FXML public void handleCancelButtonAction(ActionEvent event) {
        backToMain(cancelBtn);
    }

    @FXML public void handleSplitMenu(ActionEvent event) {
        if (event.getSource() == studentMenu)
            userType.setText(studentMenu.getText());
        else if (event.getSource() == teacherMenu)
            userType.setText(teacherMenu.getText());
        else
            userType.setText(communityMenu.getText());
    }



    // Verifica se o número digitado possui algum caractere não numérico
    private boolean isValidNumber(TextField field) {
        String word = field.getText();
        for (int i = 0; i < word.length(); i++) {
            if (! Character.isDigit(word.charAt(i))) return false;
        }
        return true;
    }

    // Ainda não decidi se vou usar isso
    private boolean isValidName(TextField field) {
        String word = field.getText();
        for (int i = 0; i < word.length(); i++) {
            if ( ! Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }
}


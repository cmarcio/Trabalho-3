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
        // Cria um objeto de manipula��o de dados
        UsersFile file = new UsersFile("UsersReg.txt");

        // Verifica se todos os campos j� est�o preenchidos
        if (firstNameField.getText().isEmpty() || lastNameField.getText().isEmpty() || emailField.getText().isEmpty()
                || userIdField.getText().isEmpty())
            showWarning("warning", null, "blankFieldWarning");

        // Verifica se o tipo de usu�rio foi escolhido
        else if (userType.getText().compareTo(Main.getResourceBundle().getString("group")) == 0)
            showWarning("warning", null, "blankFieldWarning");

        // Verifica se o n�mero passado como id � v�lido
        else if (! isValidNumber(userIdField))
            showError("warning", "invalidEntry", "onlyNumber");

        // Verifica se o usu�rio j� n�o foi cadastrado
        else if (file.searchID(userIdField.getText()) != null)
            showError("error", "invalidEntry", "userAlreadyRegistered");

        // Cria o novo usu�rio e adiciona ao arquivo
        else {
            String type = userType.getText();

            // Cria um objeto do tipo usu�rio
            User user;
            if (type.compareTo(Main.getResourceBundle().getString("student")) == 0)
                user = new Student(firstNameField.getText(), lastNameField.getText(), emailField.getText(), Long.parseLong(userIdField.getText()));
            else if (type.compareTo(Main.getResourceBundle().getString("teacher")) == 0)
                user = new Teacher(firstNameField.getText(), lastNameField.getText(), emailField.getText(), Long.parseLong(userIdField.getText()));
            else
                user = new CommunityMember(firstNameField.getText(), lastNameField.getText(), emailField.getText(), Long.parseLong(userIdField.getText()));

            // Salva o usu�rio
            file.storeUser(user);

            // Exibe mensagem de exito
            showInformation("success", null, "userRegistered");

            backToMain(okBtn);
        }
    }

    // Se o bot�o cancelar for pressionado, volta ao menu principal
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



    // Verifica se o n�mero digitado possui algum caractere n�o num�rico
    private boolean isValidNumber(TextField field) {
        String word = field.getText();
        for (int i = 0; i < word.length(); i++) {
            if (! Character.isDigit(word.charAt(i))) return false;
        }
        return true;
    }

    // Ainda n�o decidi se vou usar isso
    private boolean isValidName(TextField field) {
        String word = field.getText();
        for (int i = 0; i < word.length(); i++) {
            if ( ! Character.isLetter(word.charAt(i))) return false;
        }
        return true;
    }
}


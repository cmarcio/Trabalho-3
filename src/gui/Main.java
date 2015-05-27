package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class Main extends Application {
    private static ResourceBundle messages;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"), Main.getResourceBundle());
        primaryStage.setTitle("Library");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Locale currentLocale;

        currentLocale = Locale.getDefault();
        try {   // Try to start the resource bundle with the system default language
            messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        } catch(MissingResourceException mre){  // If trows exception set language to english
            currentLocale = new Locale("en", "US");
            messages = ResourceBundle.getBundle("MessagesBundle", currentLocale);
        }
        
        launch(args);
    }

    public static ResourceBundle getResourceBundle() {
        return messages;
    }
}

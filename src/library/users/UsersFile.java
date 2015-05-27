package library.users;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Marcio on 26/05/2015.
 */
public class UsersFile {
    private File usersFile;

    public UsersFile(String fileName) {
        // Verifica se o diretório de dados já foi criado
        File folder = new File("data");
        if (!folder.exists())
            folder.mkdir(); // Cria o diretório caso não tenha sido criado

        // Cria o arquivo dentro do diretório de dados
        File usersReg = new File(folder, fileName);

        // Salva o ponteiro para o arquivo no objeto
        usersFile = usersReg;
    }

    public void saveUser(User user) throws IOException {
        FileWriter fileWriter = new FileWriter(usersFile, true);
        PrintWriter output = new PrintWriter(fileWriter);

        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String email = user.getEmail();
        String userId = Long.toString(user.getUserId());
        String status;
        if (user.isBlocked())
            status = "BLOCKED";
        else status = "UNBLOCKED";

        output.printf("%s, %s, %s, %s, %s\n", firstName, lastName, email, userId, status);

        output.close();
        fileWriter.close();
    }
 }

package library.users;

import java.io.*;
import java.util.Date;

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
        // Salva o ponteiro para o arquivo no objeto
        usersFile = new File(folder, fileName);
    }

    public void storeUser(User user) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(usersFile, true);
            PrintWriter output = new PrintWriter(fileWriter);

            // Converte os dados do usuário para string
            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();
            String userId = Long.toString(user.getUserId());
            String type = user.getGroup();
            String data = user.getUnblockDate().toString();
            String status;
            if (user.isBlocked())
                status = "BLOCKED";
            else status = "UNBLOCKED";

            // Escreve os dados do usuário no arquivo
            output.printf("%s, %s, %s, %s, %s, %s, %s\n", firstName, lastName, email, userId, type, status, data);

            output.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User searchID(long ID) {
        FileReader reader = null;
        User userFound = null;
        String line;
        try {
            reader = new FileReader(usersFile);
            BufferedReader input = new BufferedReader(reader);

            // Le linha por linha do arquivo até o fim
            while ((line = input.readLine()) != null){
                // Quebra a linha em partes
                String[] fields = line.split(",");
                // Verifica se o campo user ID está nesse registro
                if (fields[3].equals(Long.toString(ID))) {
                    // Cria um novo objeto de acordo com o registro lido
                    userFound = createUserByReg(fields);

                    input.close();
                    reader.close();
                    return userFound;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userFound;
    }

    // Recebe uma string com os campos de um registro do arquivo de dados e cria o respectivo objeto
    private User createUserByReg(String[] fields) {
        String firstName = fields[0];
        String lastName = fields[1];
        String email = fields[2];
        long userId = Long.getLong(fields[3]);
        boolean blocked = fields[5].equals("BLOCKED");
        Date date;
        if (blocked)
            date = null;
        else date = new Date(fields[6]);

        User newUser = null;

        switch(fields[4]) {
            case "TEACHER":
                newUser = new Teacher(firstName, lastName, email, userId, blocked, date);
                break;
            case "STUDENT":
                newUser = new Student(firstName, lastName, email, userId, blocked, date);
                break;
            case "COMMUNITY MEMBER":
                newUser = new CommunityMember(firstName, lastName, email, userId, blocked, date);
                break;
        }

        return newUser;
    }
 }

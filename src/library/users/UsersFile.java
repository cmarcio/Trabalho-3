package library.users;

import java.io.*;
import java.util.ArrayList;

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

            // Escreve os dados do usuário no arquivo
            output.printf("%s,%s,%s,%s,%s\n", firstName, lastName, email, userId, type);

            output.close();
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("ERROR SAVING USER IN FILE!");
            e.printStackTrace();
        }
    }

    public User searchID(String ID) {
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
                if (fields[3].equals(ID)) {
                    // Cria um novo objeto de acordo com o registro lido
                    userFound = createUserByReg(fields);

                    input.close();
                    reader.close();
                    return userFound;
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR WHILE LOOKING FOR ID IN USER FILE!");
            e.printStackTrace();
        }
        return userFound;
    }

    // Recebe uma string com os campos de um registro do arquivo de dados e cria o respectivo objeto
    private User createUserByReg(String[] fields) {
        String firstName = fields[0];
        String lastName = fields[1];
        String email = fields[2];
        long userId = Long.parseLong(fields[3]);

        User newUser = null;
        switch(fields[4]) {
            case "TEACHER":
                newUser = new Teacher(firstName, lastName, email, userId);
                break;
            case "STUDENT":
                newUser = new Student(firstName, lastName, email, userId);
                break;
            case "COMMUNITY MEMBER":
                newUser = new CommunityMember(firstName, lastName, email, userId);
                break;
        }
        return newUser;
    }

    public ArrayList<User> getUsersList() {
        ArrayList<User> userList = new ArrayList<User>();
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
                // Cria um novo objeto de acordo com o registro lido
                userFound = createUserByReg(fields);
                // Adiciona o usuánio na lista
                userList.add(userFound);
            }
            input.close();
            reader.close();

        } catch (IOException e) {
            System.err.println("ERROR WHILE LOOKING FOR ID IN USER FILE!");
            e.printStackTrace();
        }
        return userList;
    }

    public File getUsersFile() {
        return usersFile;
    }
}

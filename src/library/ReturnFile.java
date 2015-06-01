package library;

import library.books.Book;
import library.users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Marcio on 01/06/2015.
 */
public class ReturnFile {
    private File returnFile;

    public ReturnFile(String fileName) {
        // Verifica se o diretório de dados já foi criado
        File folder = new File("data");
        if (!folder.exists())
            folder.mkdir(); // Cria o diretório caso não tenha sido criado

        // Cria o arquivo dentro do diretório de dados
        returnFile = new File(folder, fileName);
    }

    public void storeReturn(Book book, User user, GregorianCalendar calendar) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(returnFile, true);
            PrintWriter output = new PrintWriter(fileWriter);

            String bookId = Long.toString(book.getBookNumber());
            String userId = Long.toString(user.getUserId());
            String date = Integer.toString(calendar.get(Calendar.DATE));
            String month = Integer.toString(calendar.get(Calendar.MONTH) + 1);
            String year = Integer.toString(calendar.get(Calendar.YEAR));
            Date time = new Date();
            String hour = Integer.toString(time.getHours());
            String minutes = Integer.toString(time.getMinutes());

            // Escreve os dados do livro no arquivo
            output.printf("%s,%s,%s,%s,%s,%s,%s\n", bookId, userId, date, month, year, hour, minutes);

            output.close();
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("ERROR SAVING RETURN IN FILE!");
            e.printStackTrace();
        }
    }

    public ArrayList<Register> toRegisters() {
        ArrayList<Register> list = new ArrayList<Register>();
        FileReader reader = null;
        String line;

        if (returnFile.exists()) {
            try {
                reader = new FileReader(returnFile);
                BufferedReader input = new BufferedReader(reader);

                // Le linha por linha do arquivo até o fim
                while ((line = input.readLine()) != null) {
                    // Quebra a linha em partes
                    String[] fields = line.split(",");
                    // Cria um novo objeto e adiciona os valores aos campos
                    GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(fields[4]), Integer.parseInt(fields[3]), Integer.parseInt(fields[2]), Integer.parseInt(fields[5]), Integer.parseInt(fields[6]));
                    Register reg = new Register(calendar, Long.parseLong(fields[0]), Long.parseLong(fields[1]));
                    // Adiciona o objeto a list
                    list.add(reg);
                }

                input.close();
                reader.close();
            } catch (IOException e) {
                System.err.println("ERROR WHILE READING RETURN FILE!");
                e.printStackTrace();
            }
        }
        return list;
    }
}

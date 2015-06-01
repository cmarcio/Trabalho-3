package library.books;

import library.users.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
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

            // Escreve os dados do livro no arquivo
            output.printf("%s,%s,%s,%s,%s\n", bookId, userId, date, month, year);

            output.close();
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("ERROR SAVING RETURN IN FILE!");
            e.printStackTrace();
        }
    }
}

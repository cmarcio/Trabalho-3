package library.books;

import library.users.User;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Marcio on 01/06/2015.
 */
public class BorrowFile {
    private File borrowFile;

    public BorrowFile(String fileName) {
        // Verifica se o diret�rio de dados j� foi criado
        File folder = new File("data");
        if (!folder.exists())
            folder.mkdir(); // Cria o diret�rio caso n�o tenha sido criado

        // Cria o arquivo dentro do diret�rio de dados
        borrowFile = new File(folder, fileName);
    }

    public void storeBook(Book book, User user, GregorianCalendar calendar) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(borrowFile, true);
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
            System.err.println("ERROR SAVING BORROW IN FILE!");
            e.printStackTrace();
        }
    }
}

package library.books;

import library.users.User;

import java.io.*;

/**
 * Created by Marcio on 30/05/2015.
 */
public class BooksFile {
    private File booksFile;

    public BooksFile(String fileName) {
        // Verifica se o diretório de dados já foi criado
        File folder = new File("data");
        if (!folder.exists())
            folder.mkdir(); // Cria o diretório caso não tenha sido criado

        // Cria o arquivo dentro do diretório de dados
        booksFile = new File(folder, fileName);
    }

    public void storeBook(Book book) {
        FileWriter fileWriter = null;
        try {
            // Determina o ID do livro
            if (booksFile.exists()) {
                LineNumberReader lnr = new LineNumberReader(new FileReader(booksFile));
                lnr.skip(Long.MAX_VALUE);
                book.setBookNumber(lnr.getLineNumber());
            }
            else book.setBookNumber(0);

            fileWriter = new FileWriter(booksFile, true);
            PrintWriter output = new PrintWriter(fileWriter);

            // Converte os dados do livro para string
            String title = book.getTitle();
            String author = book.getAuthor();
            String publisher = book.getPublisher();
            String year = Integer.toString(book.getYear());
            String edition = Integer.toString(book.getEdition());
            String bookNumber = Long.toString(book.getBookNumber());

            // Escreve os dados do livro no arquivo
            output.printf("%s,%s,%s,%s,%s,%s\n", title, author, publisher, year, edition, bookNumber);

            output.close();
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("ERROR SAVING BOOK IN FILE!");
            e.printStackTrace();
        }
    }
}

package library.books;

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
            output.printf("%s,%s,%s,%s,%s,%s,%s\n", title, author, publisher, year, edition, bookNumber, book.getBookType());

            output.close();
            fileWriter.close();

        } catch (IOException e) {
            System.err.println("ERROR SAVING BOOK IN FILE!");
            e.printStackTrace();
        }
    }

    public Book searchID(String ID) {
        FileReader reader = null;
        Book bookFound = null;
        String line;
        try {
            reader = new FileReader(booksFile);
            BufferedReader input = new BufferedReader(reader);

            // Le linha por linha do arquivo até o fim
            while ((line = input.readLine()) != null){
                // Quebra a linha em partes
                String[] fields = line.split(",");
                // Verifica se o campo user ID está nesse registro
                if (fields[5].equals(ID)) {
                    // Cria um novo objeto de acordo com o registro lido
                    bookFound = createBookByReg(fields);

                    input.close();
                    reader.close();
                    return bookFound;
                }
            }
        } catch (IOException e) {
            System.err.println("ERROR WHILE LOOKING FOR ID IN BOOK FILE!");
            e.printStackTrace();
        }
        return bookFound;
    }

    // Recebe uma string com os campos de um registro do arquivo de dados e cria o respectivo objeto
    private Book createBookByReg(String[] fields) {
        String title = fields[0];
        String author = fields[1];
        String publisher = fields[2];
        int year = Integer.parseInt(fields[3]);
        int edition = Integer.parseInt(fields[4]);
        Long bookId = Long.parseLong(fields[5]);

        Book newBook = null;
        switch(fields[6]) {
            case "TEXT BOOK":
                newBook = new TextBook(title, author, publisher, year, edition, bookId);
                break;
            case "GENERAL BOOK":
                newBook = new GeneralBook(title, author, publisher, year, edition, bookId);
                break;
        }
        return newBook;
    }

    public File getBooksFile() {
        return booksFile;
    }
}

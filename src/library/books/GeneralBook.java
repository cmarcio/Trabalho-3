package library.books;

/**
 * Created by Marcio on 24/05/2015.
 */
public class GeneralBook extends Book {
    public GeneralBook(String title, String author, String publisher, int year, int edition, long bookNumber) {
        super(title, author, publisher, year, edition, bookNumber);
    }

    @Override
    public String getBookType() {
        return "GENERAL BOOK";
    }
}

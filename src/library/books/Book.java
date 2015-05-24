package library.books;

import java.util.Date;

/**
 * Created by Marcio on 24/05/2015.
 */
public class Book {
    private String title;
    private String author;
    private String publisher;
    private int year;
    private int edition;
    private int bookNumber;
    private boolean available;
    private Date returnDate;

    public Book(String title, String author, String publisher, int year, int edition, int bookNumber) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.edition = edition;
        this.bookNumber = bookNumber;
        this.available = true;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getEdition() {
        return edition;
    }

    public void setBookNumber(int bookNumber) {
        this.bookNumber = bookNumber;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}

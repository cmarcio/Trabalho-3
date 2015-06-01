package library.users;

import library.books.Book;

/**
 * Created by Marcio on 24/05/2015.
 */
public class Student extends UniversityMember {

    public Student(String firstName, String lastName, String email, long userId) {
        super(firstName, lastName, email, userId);
        books = new Book[4];
        for (int i = 0; i < books.length; i++)
            books[i] = null;
    }

    @Override
    public String getGroup() {
        return "STUDENT";
    }

    @Override
    public int getBorrowTime() {
        return 15;
    }


}

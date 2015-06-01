package library.users;

import library.books.Book;

/**
 * Created by Marcio on 24/05/2015.
 */
public class Teacher extends UniversityMember {

    public Teacher(String firstName, String lastName, String email, long userId) {
        super(firstName, lastName, email, userId);
        books = new Book[6];
        for (int i = 0; i < books.length; i++)
            books[i] = null;
    }

    @Override
    public String getGroup() {
        return "TEACHER";
    }

    public int getBorrowTime() {
        return 60;
    }
}

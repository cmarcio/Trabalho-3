package library.users;

import library.books.Book;

/**
 * Created by Marcio on 24/05/2015.
 */
public class CommunityMember extends User {

    public CommunityMember(String firstName, String lastName, String email, long userId) {
        super(firstName, lastName, email, userId);
        books = new Book[2];
        for (int i = 0; i < books.length; i++)
            books[i] = null;
    }

    @Override
    public String getGroup() {
        return "COMMUNITY MEMBER";
    }

    @Override
    public int getBorrowTime() {
        return 15;
    }
}

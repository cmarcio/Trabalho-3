package library.users;

import java.util.Date;

/**
 * Created by Marcio on 24/05/2015.
 */
public abstract class UniversityMember extends User {
    public UniversityMember(String firstName, String lastName, String email, long userId) {
        super(firstName, lastName, email, userId);
    }
}

package library.users;

import java.util.Date;

/**
 * Created by Marcio on 24/05/2015.
 */
public class Student extends UniversityMember {
    public Student(String firstName, String lastName, String email, long userId) {
        super(firstName, lastName, email, userId);
    }

    @Override
    public String getGroup() {
        return "STUDENT";
    }
}

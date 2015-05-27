package library.users;

/**
 * Created by Marcio on 24/05/2015.
 */
public class Student extends UniversityMember {
    public Student(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    @Override
    public String getGroup() {
        return "STUDENT";
    }
}

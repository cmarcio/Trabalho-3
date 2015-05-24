package library.users;

/**
 * Created by Marcio on 24/05/2015.
 */
public abstract class User {
    private String firstName;
    private String lastName;
    private int userNumber;

    protected User(String firstName, String lastName, int userNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userNumber = userNumber;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setUserNumber(int userNumber) {
        this.userNumber = userNumber;
    }

    public int getUserNumber() {
        return userNumber;
    }
}

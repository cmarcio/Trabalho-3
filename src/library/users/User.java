package library.users;

import java.util.Date;

/**
 * Created by Marcio on 24/05/2015.
 */
public abstract class User {
    private String firstName;
    private String lastName;
    private int userID;
    private boolean blocked;
    private Date unblockDate;

    protected User(String firstName, String lastName, int userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userID = userID;
        this.blocked = false;
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

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setUnblockDate(Date unblockDate) {
        this.unblockDate = unblockDate;
    }

    public Date getUnblockDate() {
        return unblockDate;
    }
}

package library.users;

import java.util.Date;

/**
 * Created by Marcio on 24/05/2015.
 */
public abstract class User {
    private String firstName;
    private String lastName;
    private String email;
    private long userId;
    private boolean blocked;
    private Date unblockDate;

    public User(String firstName, String lastName, String email, long userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
        this.blocked = false;
        this.unblockDate = null;
    }

    public User(String firstName, String lastName, String email, long userId, boolean status, Date date) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userId = userId;
        this.blocked = status;
        this.unblockDate = date;
    }

    public abstract String getGroup();

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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getUserId() {
        return userId;
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

package library.users;

/**
 * Created by Marcio on 24/05/2015.
 */
public class CommunityMember extends User {
    public CommunityMember(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    @Override
    public String getGroup() {
        return "COMMUNITY MEMBER";
    }
}

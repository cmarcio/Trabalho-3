package library.users;

/**
 * Created by Marcio on 24/05/2015.
 */
public class CommunityMember extends User {
    public CommunityMember(String firstName, String lastName, String email, long userId) {
        super(firstName, lastName, email, userId);
    }

    @Override
    public String getGroup() {
        return "COMMUNITY MEMBER";
    }
}

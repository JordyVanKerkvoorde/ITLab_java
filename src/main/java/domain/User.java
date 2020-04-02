package domain;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private UserType userType;
    private UserStatus userStatus;
    //private Media avatar;
    private int penalties;

    public User(String firstName, String lastName, UserType userType, UserStatus userStatus, int penalties) {
        setFirstName(firstName);
        setLastName(lastName);
        setUserStatus(userStatus);
        setUserType(userType);
        setPenalties(penalties);
    }

    //JPA
    public User() {
    }

    public String getUserId() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }
}


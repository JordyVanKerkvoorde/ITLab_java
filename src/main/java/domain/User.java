package domain;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private UserType userType;
    private UserStatus userStatus;
    //private Media avatar;
    private int penalties;
    private String userName;
    private String email;
    private boolean EmailConfirmed;

    public User(String userId, String firstName, String lastName, UserType userType, UserStatus userStatus, int penalties, String userName, String email, boolean emailConfirmed) {
        setUserId(userId);
        setFirstName(firstName);
        setLastName(lastName);
        setUserStatus(userStatus);
        setUserType(userType);
        setPenalties(penalties);
        setUserName(userName);
        setEmail(email);
        setEmailConfirmed(emailConfirmed);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEmailConfirmed() {
        return EmailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        EmailConfirmed = emailConfirmed;
    }

    //JPA
    public User() {
    }

    private void setUserId(String userId) {
        userId = userId;
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


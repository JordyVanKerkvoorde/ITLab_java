package domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AspNetUsers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private String id;
    @Column(name = "UserId")
    private String userId;
    @Column(name = "FirstName")
    private String firstName;
    @Column(name = "LastName")
    private String lastName;
    @Column(name = "UserType")
    private UserType userType;
    @Column(name = "UserStatus")
    private UserStatus userStatus;
    @Column(name = "AvatarMediaId")
    @OneToOne
    private Media avatar;
    @Column(name = "Penalties")
    private int penalties;
    @Column(name = "UserName")
    private String userName;
    @Column(name = "Email")
    private String email;
    @Column(name = "EmailConfirmed")
    private boolean emailConfirmed;
    @OneToMany
    private List<UserSession> userSessions;

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
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
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

    public List<UserSession> getUserSessions() {
        return userSessions;
    }

    public void addUserSession(UserSession userSession){
        userSessions.add(userSession);
    }


}


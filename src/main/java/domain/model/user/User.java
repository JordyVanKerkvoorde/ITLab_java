package domain.model.user;

import domain.model.session.Media;
import org.apache.commons.validator.routines.EmailValidator;
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
    @JoinColumn(name = "AvatarMediaId")
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

    //JPA
    public User() {
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Voornaam mag niet leeg zijn.");
        }
        this.firstName = firstName;
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
        if(!EmailValidator.getInstance().isValid(email)){
            throw new IllegalArgumentException("E-mail niet van juiste formaat");
        }
        this.email = email;
    }

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException("Familienaam mag niet leeg zijn.");
        }
        else {
            this.lastName = lastName;
        }
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
        if (penalties < 0 || penalties > 3) {
            throw new IllegalArgumentException("Aantal penalties kan niet kleiner zijn dan 0 of je hebt het maximaal aantal penalties overschreden (max 3).");
        }
        else {
            this.penalties = penalties;
        }
    }

    public List<UserSession> getUserSessions() {
        return userSessions;
    }

//    public void addUserSession(UserSession userSession){
//        for (UserSession us: userSessions ) {
//            if (us.getUser().equals(userSession.getUser())) {
//                throw new IllegalArgumentException("De gebruiker is al ingeschreven op de sessie.");
//            }
//        }
//        if(!userSession.getSession().equals(this)){
//            throw new IllegalArgumentException("Dit is niet de juiste sessie waaraan de usersessie moet toegevoegd worden.");
//        }
//        userSessions.add(userSession);
//    }


    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userType=" + userType +
                ", userStatus=" + userStatus +
                ", avatar=" + avatar +
                ", penalties=" + penalties +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", emailConfirmed=" + emailConfirmed +
                ", userSessions=" + userSessions +
                '}';
    }
}


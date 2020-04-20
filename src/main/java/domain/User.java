package domain;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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

    //JPA
    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.isEmpty() || firstName.length() > 20) {
            throw new IllegalArgumentException("Voornaam moet tussen de 1 en 20 characters zijn.");
        }
        else {
            this.firstName = firstName;
        }
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
        try{
            InternetAddress adress = new InternetAddress(email);
            adress.validate();
        } catch (AddressException e) {
            throw new IllegalArgumentException("Emailadres is niet van juiste formaat.");
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

    private void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.isEmpty() || lastName.length() > 20) {
            throw new IllegalArgumentException("Voornaam moet tussen de 1 en 20 characters zijn.");
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
        if (penalties < 0) {
            throw new IllegalArgumentException("Aantal penalties kan niet kleiner zijn dan 0.");
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


}


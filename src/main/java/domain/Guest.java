package domain;

import org.apache.commons.validator.routines.EmailValidator;
import javax.persistence.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@Table(name = "Guest")
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GuestId")
    private int guestId;
    @Column(name = "Name")
    private String name;
    @Column(name = "Email")
    private String email;
    @Column(name = "PhoneNumber")
    private String phoneNumber;

    public Guest(String name, String email, String phoneNumber) {
        setName(name);
        setEmail(email);
        setPhoneNumber(phoneNumber);
    }

    public Guest() {
    }

    public int getGuestId() {
        return guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber)
    {
        final String PHONE_REGEX = "^\\\\d{8,9}$";
        final Pattern pattern = Pattern.compile(PHONE_REGEX);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()){
            throw new IllegalArgumentException("Telefoonnummer is niet van juiste formaat");
        }
        else {
            this.phoneNumber = phoneNumber;
        }

    }
}

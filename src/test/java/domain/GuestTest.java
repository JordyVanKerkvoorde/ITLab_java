package domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class GuestTest {

    private Guest guest;

    @BeforeEach
    void before() {
        guest = new Guest();
    }

    @ParameterizedTest
    @ValueSource(strings= { "Jan-Willem Pieters", "Jordy Van Kerkvoorde", "Patrick O'Brian" })
    void setName(String name) {
        guest.setName(name);
        Assertions.assertEquals(name, guest.getName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings= { "J@n Willem", "T0m Pieters", "Sander ; Machado" })
    void setNameOngeldig(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> guest.setName(name));
    }

    @ParameterizedTest
    @ValueSource(strings= { "Jan.willem@student.hogent.be", "Sander@gmail.com", "jordy@hotmail.nl", "yorick.vdw@outlook.be" })
    void setEmail(String email) {
        guest.setEmail(email);
        Assertions.assertEquals(email, guest.getEmail());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings= { "Jan@fsdfds", "Sander@%", "Jordy", "@", "Pieter@Jan@gmail.com" })
    void setEmailOngeldig(String email) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> guest.setEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings= { "051324679", "0477134679", "012345678" })
    void setPhoneNumber(String phoneNumber) {
        guest.setPhoneNumber(phoneNumber);
        Assertions.assertEquals(phoneNumber, guest.getPhoneNumber());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings= { "04841234567", "050e22316", "123456", "1.2.3.4.5" })
    void setPhoneNumberOngeldig(String phoneNumber) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> guest.setPhoneNumber(phoneNumber));
    }
}
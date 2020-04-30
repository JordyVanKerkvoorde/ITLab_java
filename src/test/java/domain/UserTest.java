package domain;

import domain.model.user.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class UserTest {

    private User user;

    @BeforeEach
    void before() {
        user = new User();
    }

    @ParameterizedTest
    @ValueSource(strings= { "Jan", "Sander", "Jordy", "Yorick" })
    void setFirstName(String name) {
        user.setFirstName(name);
        Assertions.assertEquals(name, user.getFirstName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setFirstNameOngeldig(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {user.setFirstName(name);});
    }

    @ParameterizedTest
    @ValueSource(strings= { "Jan.willem@student.hogent.be", "Sander@gmail.com", "jordy@hotmail.nl", "yorick.vdw@outlook.be" })
    void setEmail(String email) {
        user.setEmail(email);
        Assertions.assertEquals(email, user.getEmail());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings= { "Jan@fsdfds", "Sander@%", "Jordy", "@", "Pieter@Jan@gmail.com" })
    void setEmailOngeldig(String email) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {user.setEmail(email);});
    }

    @ParameterizedTest
    @ValueSource(strings= { "Willem", "Machado", "Van Kerkvoorde", "Vandewoestyne" })
    void setLastName(String name) {
        user.setLastName(name);
        Assertions.assertEquals(name, user.getLastName());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setLastNameOngeldig(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {user.setLastName(name);});
    }

    @ParameterizedTest
    @ValueSource(ints= { 0, 1, 2, 3 })
    void setPenalties(int penalties) {
        user.setPenalties(penalties);
        Assertions.assertEquals(penalties, user.getPenalties());
    }

    @ParameterizedTest
    @ValueSource(ints= { Integer.MIN_VALUE, Integer.MAX_VALUE, -10, -5, -1, 4, 10, 20})
    void setPenaltiesOngeldig(int penalties) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {user.setPenalties(penalties);});
    }
}
package domain;

import domain.model.session.CampusEnum;
import domain.model.session.Location;
import domain.model.session.Session;
import domain.model.user.User;
import domain.model.user.UserSession;
import domain.model.user.UserStatus;
import domain.model.user.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

class SessionTest {

    private Session session;
    private User user;
    private User responsible;
    private User inactiveuser;
    private Location location;
    private UserSession userSession1;
    private UserSession userSession2;


    @BeforeEach
    void before() {
        session = new Session();
        user = new User("456465jw", "Jan", "Willem", UserType.USER, UserStatus.ACTIVE, 0, "janwillem@hogent.be", "janwillem@hogent.be", true);
        responsible = new User("456465sm", "Sander", "Machado", UserType.RESPONSIBLE, UserStatus.ACTIVE, 0, "sandermachado@hogent.be", "sandermachado@hogent.be", true);
        inactiveuser = new User("456465jv", "Jordy", "Van Kerkvoorde", UserType.USER, UserStatus.INACTIVE, 0, "jordyv@hogent.be", "jordyv@hogent.be", true);
        location = new Location("GebouwB.201", CampusEnum.SCHOONMEERSEN, 60);
        session.setLocation(location);

        userSession1 = new UserSession(user, session);
        userSession2 = new UserSession(user, new Session());
    }

    @ParameterizedTest
    @ValueSource(strings= { "What I Wish I Had Known Before Scaling Uber to 1000 Services", "Life is Terrible: Let’s Talk About the Web" })
    void setTitle(String title) {
        session.setTitle(title);
        session.setTitle(title);
        Assertions.assertEquals(title, session.getTitle());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setTitleOngeldig(String title) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> session.setTitle(title));
    }

    @ParameterizedTest
    @ValueSource(strings= { "What I Wish I Had Known Before Scaling Uber to 1000 Services", "Life is Terrible: Let’s Talk About the Web" })
    void setDescription(String description) {
        session.setDescription(description);
        Assertions.assertEquals(description, session.getDescription());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void setDescriptionOngeldig(String description) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> session.setDescription(description));
    }

    @Test
    void setResponsible() {
        session.setResponsible(user);
        Assertions.assertEquals(user, session.getResponsible());
        Assertions.assertEquals(UserType.RESPONSIBLE, user.getUserType());
        session.setResponsible(responsible);
        Assertions.assertEquals(responsible, session.getResponsible());
        Assertions.assertEquals(UserType.RESPONSIBLE, responsible.getUserType());
    }

    @Test
    void setResponsibleOngeldig() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> session.setResponsible(inactiveuser));
    }

    @Test
    void setStartAndEnd(){
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now().plusHours(2);
        session.setStartAndEnd(start, end);
        Assertions.assertEquals(start, session.getStart());
        Assertions.assertEquals(end, session.getEnd());

    }

    @Test
    void setStartAndEndOngeldig(){
        LocalDateTime start = LocalDateTime.now().plusHours(2);
        LocalDateTime end = LocalDateTime.now();
        Assertions.assertThrows(IllegalArgumentException.class, () -> session.setStartAndEnd(start, end));
        Assertions.assertThrows(NullPointerException.class, () -> session.setStartAndEnd(start, null));
        Assertions.assertThrows(NullPointerException.class, () -> session.setStartAndEnd(null, end));
    }

    @ParameterizedTest
    @ValueSource(ints= { 1, 2, 10, 30, 50, 60 })
    void setCapacity(int capacity) {
        session.setCapacity(capacity);
        Assertions.assertEquals(capacity, session.getCapacity());
    }

    @ParameterizedTest
    @ValueSource(ints= { Integer.MIN_VALUE, Integer.MAX_VALUE, -50, -10, -1, 0, 61, 70, 100 })
    void setCapacityOngeldig(int capacity) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> session.setCapacity(capacity));
    }

    @Test
    void addUserSession() {
        session.addUserSession(userSession1);
        Assertions.assertTrue(session.getUserSessions().contains(userSession1));
    }

    @Test
    void addUserSessionOngeldig() {
        session.addUserSession(userSession1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> session.addUserSession(userSession1));
        Assertions.assertThrows(IllegalArgumentException.class, () -> session.addUserSession(userSession2));
    }
}
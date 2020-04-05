package domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MockData {
    public static List<User> mockUsers = new ArrayList<>(
            Arrays.asList(
                    new User("866840yv", "Yorick", "Van de Woestyne", UserType.ADMIN, UserStatus.ACTIVE, 0, "yorick.vandewoestyne@student.hogent.be", "yorick.vandewoestyne@student.hogent.be", true),
                    new User("101001jw", "Jan", "Willem", UserType.USER, UserStatus.BLOCKED, 0, "jan.willem@student.hogent.be", "jan.willem@student.hogent.be", true),
                    new User("42069sm", "Sander", "Machado", UserType.RESPONSIBLE, UserStatus.ACTIVE, 0, "sander.castanheiramachado@student.hogent.be", "sander.castanheiramachado@student.hogent.be", true),
                    new User("00200jv", "Jordy", "Van Kerkvoorde", UserType.USER, UserStatus.ACTIVE, 0, "jordy.vankerkvoorde@student.hogent.be", "jordy.vankerkvoorde@student.hogent.be", true)
            )
    );
    public static List<Location> mockLocations = new ArrayList<>(
            Arrays.asList(
                    new Location("GSCHB1.420", CampusEnum.SCHOONMEERSEN, 500),
                    new Location("GSCHA6.099", CampusEnum.AALST, 420),
                    new Location("GSCHM4.012", CampusEnum.MERCATOR, 200),
                    new Location("GSCHB3.220", CampusEnum.SCHOONMEERSEN, 100)
            )
    );
    public static List<Session> mockSessions = new ArrayList<>(
            Arrays.asList(
                    new Session("What I Wish I Had Known Before Scaling Uber to 1000 Services",
                            "Matt Ranney, Senior Staff Engineer bij Uber, vertelt over zijn ervaringen met microservices bij Uber: \"To Keep up with Uber's growth, we've embraced microservices in a big way.This has led to an explosion of new services, crossing over 1, 000 production services in early March 2016.Along the way we've learned a lot, and if we had to do it all over again, we'd do some things differently.If you are earlier along on your personal microservices journey than we are, then this talk may save you from having to learn some things learn the hard way. \"",
                            mockUsers.get(0), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(2).plusHours(2), 1, mockLocations.get(0)),
                    new Session("Life is Terrible: Let’s Talk About the Web",
                            "Zij die Webapps 2 gehad hebben kennen JavaScript al, anderen beginnen nu net aan hun \"avontuur\". James Mickens kwam vorig semester al aan bod met een tirade over security en machine learning, nu is hij terug om iedereen te laten weten wat hij vindt van de nummer 1 programmeertaal voor het Web.",
                            mockUsers.get(0), LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(3).plusHours(2), 69, mockLocations.get(1)),
                    new Session("De weg naar de Cloud, hoe doen bedrijven dat nu eigenlijk?", "Diederik Wyffels heeft al 20 jaar ervaring in de branche en focust zich vooral op het begeleiden van bedrijven die moeite hebben met het schalen van hun IT-infrastructuur. In deze talk bespreekt hij concreet hoe bedrijven begeleid worden in hun overstap naar de cloud.",
                            mockUsers.get(2), LocalDateTime.now().minusDays(-7), LocalDateTime.now().minusDays(-7).plusHours(2), 30, mockLocations.get(2))
            )
    );
}

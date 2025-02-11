package domain.model.session;

import domain.model.user.User;
import domain.model.user.UserSession;
import domain.model.user.UserStatus;
import domain.model.user.UserType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "Session.findAll", query="Select s from Session s")
})

@Table(name = "Session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SessionId")
    private int sessionId;
    @Column(name = "Title")
    private String title;
    @Column(name = "Description")
    private String description;
    @ManyToOne
    @JoinColumn(name="ResponsibleId")
    private User responsible;
    @Column(name = "StartTime", columnDefinition = "TIMESTAMP")
    private LocalDateTime start;
    @Column(name = "EndTime", columnDefinition = "TIMESTAMP")
    private LocalDateTime end;
    @Column(name = "Capacity")
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "LocationId")
    private Location location;
    @OneToMany
    private List<Media> media;
    @OneToMany
    private List<Guest> guests;
    @OneToMany
    private List<Feedback> feedback;
    @Column(name = "IsOpened")
    private boolean isOpened;
    @ManyToMany
    private List<User> presentUsers;
    @OneToMany
    private List<UserSession> userSessions;

    public Session(String title, String description, User responsible, LocalDateTime start, LocalDateTime end, int capacity, Location location) {
        setTitle(title);
        setDescription(description);
        setResponsible(responsible);
        setStartAndEnd(start, end);
        setLocation(location);
        setCapacity(capacity);
        initLists();
    }

    public Session() {
        initLists();
    }

    public void initLists() {
        userSessions = new ArrayList<>();
        feedback = new ArrayList<>();
        presentUsers = new ArrayList<>();
        media = new ArrayList<>();
        guests = new ArrayList<>();
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Titel mag niet leeg zijn.");
        }

        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Beschrijving mag niet leeg zijn.");
        }

        this.description = description;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {

        if (responsible != null) {
            if(!responsible.getUserStatus().equals(UserStatus.ACTIVE)){
                throw new IllegalArgumentException("De persoon die je verantwoordelijk wilt maken is geen actieve gebruiker.");
            }
            if(responsible.getUserType().equals(UserType.USER)){
                responsible.setUserType(UserType.RESPONSIBLE);
            }
            this.responsible = responsible;
        }
    }

    public LocalDateTime getStart() {
        return start;
    }


    public LocalDateTime getEnd() {
        return end;
    }


    public void setStartAndEnd(LocalDateTime start, LocalDateTime end) {
        LocalDateTime oldStart = getStart();
        this.start = start;

        if (start == null || end == null) {
            throw  new NullPointerException();
        }

        if (end.isBefore(start)) {
            this.start = oldStart;
            throw new IllegalArgumentException("Het einde van een sessie kan niet voor het begin liggen!");
        }

        this.end = end;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        if(location.getCapacity() < capacity || capacity <= 0){
            throw new IllegalArgumentException("De capaciteit van de sessie is groter dan wat de locatie aankan of is kleiner of gelijk aan 0.");
        }

        this.capacity = capacity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        //checken of locatie vrij is wanneer je deze set uitvoert
        this.location = location;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public void addMedia(Media media){
        this.media.add(media);
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

    public void addGuest(Guest guest){
        this.guests.add(guest);
    }

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    public List<User> getPresentUsers() {
        return presentUsers;
    }

    public void setPresentUsers(List<User> presentUsers) {
        this.presentUsers = presentUsers;
    }

    public List<UserSession> getUserSessions() {
        return userSessions;
    }

    public void addUserSession(UserSession userSession){
        for (UserSession us: userSessions ) {
            if (us.getUser().equals(userSession.getUser())) {
                throw new IllegalArgumentException("De gebruiker is al ingeschreven op de sessie.");
            }
        }
        if(!userSession.getSession().equals(this)){
            throw new IllegalArgumentException("Dit is niet de juiste sessie waaraan de usersessie moet toegevoegd worden.");
        }

        userSessions.add(userSession);
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId=" + sessionId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", responsible=" + responsible +
                ", start=" + start +
                ", end=" + end +
                ", Capacity=" + capacity +
                ", location=" + location +
                ", media=" + media +
                ", guests=" + guests +
                ", feedback=" + feedback +
                ", isOpened=" + isOpened +
                ", presentUsers=" + presentUsers +
                '}';
    }
}

package domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NamedQuery(name = "Session.findAll", query="Select s from Session s")
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
    private User responsible;
    @Column(name = "Start")
    private LocalDateTime start;
    @Column(name = "End")
    private LocalDateTime end;
    @Column(name = "Capacity")
    private int Capacity;
    @ManyToOne
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
        setStart(start);
        setEnd(end);
        setLocation(location);
        setCapacity(capacity);
    }

    public Session() {
    }

    public int getSessionId() {
        return sessionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title.isEmpty() || title.length() > 50) {
            throw new IllegalArgumentException("Titel moet tussen de 1 en 50 characters zijn.");
        }
        else {
            this.title = title;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description.isEmpty()) {
            throw new IllegalArgumentException("Beschrijving mag niet leeg zijn.");
        }
        else {
            this.description = description;
        }
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        if(!responsible.getUserStatus().equals(UserStatus.ACTIVE)){
            throw new IllegalArgumentException("De persoon die je verantwoordelijk wilt maken is geen actieve gebruiker.");
        }
        else{
            if(responsible.getUserType().equals(UserType.USER)){
                responsible.setUserType(UserType.RESPONSIBLE);
            }
            this.responsible = responsible;
        }
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        if(start.isAfter(end)){
            throw new IllegalArgumentException("Het begin van een sessie kan niet na het einde liggen.");
        }
        else {
            this.start = start;
        }
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        if(end.isBefore(start)){
            throw new IllegalArgumentException("Het einde van een sessie kan niet voor het begin liggen.");
        }
        else {
            this.end = end;
        }
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        if(location.getCapacity() < capacity){
            throw new IllegalArgumentException("De capaciteit van de sessie is groter dan wat de locatie aankan.");
        }
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        //checken of locatie vrij is
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

    public void addFeedback(Feedback feedback){
        this.feedback.add(feedback);
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
                ", Capacity=" + Capacity +
                ", location=" + location +
                ", media=" + media +
                ", guests=" + guests +
                ", feedback=" + feedback +
                ", isOpened=" + isOpened +
                ", presentUsers=" + presentUsers +
                '}';
    }
}

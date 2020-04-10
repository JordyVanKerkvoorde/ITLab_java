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
        this.title = title;
        this.description = description;
        this.responsible = responsible;
        this.start = start;
        this.end = end;
        Capacity = capacity;
        this.location = location;
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
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getResponsible() {
        return responsible;
    }

    public void setResponsible(User responsible) {
        this.responsible = responsible;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity(int capacity) {
        Capacity = capacity;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<Media> getMedia() {
        return media;
    }

    public void setMedia(List<Media> media) {
        this.media = media;
    }

    public List<Guest> getGuests() {
        return guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
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

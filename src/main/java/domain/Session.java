package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Session {

    private int sessionId;
    private String title;
    private String description;
    private User responsible;
    private LocalDateTime start;
    private LocalDateTime end;
    private int Capacity;
    private Location location;
    private List<Media> media;
    private List<Guest> guests;
    private List<Feedback> feedback;
    private boolean isOpened;
    private List<User> presentUsers;

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

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
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

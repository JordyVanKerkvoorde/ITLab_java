package domain;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="Announcements")
public class Announcement {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(name="PostTime")
    private LocalDateTime postTime;
    @Column(name="Message")
    private String message;

    public Announcement(LocalDateTime postTime, String message) {
        this.postTime = postTime;
        this.message = message;
    }

    //JPA
    public Announcement() {
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getPostTime() {
        return postTime;
    }

    public void setPostTime(LocalDateTime postTime) {
        this.postTime = postTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

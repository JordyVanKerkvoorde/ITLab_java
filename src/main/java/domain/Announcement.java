package domain;

import javax.persistence.*;
import java.time.LocalDate;
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
        //Is de postTime niet gelijk aan LocalDateTime.now()?
        setPostTime(postTime);
//        this.postTime = postTime;
        setMessage(message);
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
//        this.postTime = postTime;

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message.isEmpty()) {
            throw new IllegalArgumentException("De aankondiging mag niet leeg zijn.");
        }
        else {
            this.message = message;
        }
    }
}

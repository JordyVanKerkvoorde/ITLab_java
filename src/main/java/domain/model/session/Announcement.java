package domain.model.session;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
@Entity
@Table(name="Announcements")
public class Announcement implements Serializable {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(name="PostTime")
    private LocalDateTime postTime;
    @Column(name="Message")
    private String message;

    public Announcement(String message) {
        //Is de postTime niet gelijk aan LocalDateTime.now()?
        setPostTime();
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

    public void setPostTime() {
        this.postTime = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        if (message.isBlank()) {
            throw new IllegalArgumentException("De aankondiging mag niet leeg zijn.");
        }

        this.message = message;
    }
}

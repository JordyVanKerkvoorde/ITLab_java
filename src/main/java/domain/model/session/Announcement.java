package domain.model.session;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name="Announcements")
public class Announcement{

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    @Column(name="PostTime")
    private LocalDateTime postTime;
    @Column(name="Message")
    private String message;
    @Column(name = "Mailed")
    private boolean mailed;

    public Announcement(String message) {
        //Is de postTime niet gelijk aan LocalDateTime.now()?
        setPostTime();
        setMessage(message);
    }

    //JPA
    public Announcement() {
    }

    public int getId() {
        return id;
    }

    public boolean isMailed() {
        return mailed;
    }

    public void setMailed(boolean mailed) {
        this.mailed = mailed;
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
        if (message.trim().isEmpty()) {
            throw new IllegalArgumentException("De aankondiging mag niet leeg zijn.");
        }

        this.message = message;
    }
}

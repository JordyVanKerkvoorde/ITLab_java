package domain;

import java.time.LocalDateTime;

public class Announcement {
    private int id;
    private LocalDateTime postTime;
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

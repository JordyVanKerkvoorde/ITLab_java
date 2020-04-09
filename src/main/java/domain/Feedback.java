package domain;

import javax.persistence.*;

@Entity
@Table(name= "Feedback")
public class Feedback {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int feedbackId;
    @Column(name="Score")
    private int score;
    @Column(name="Description")
    private String description;
    private User user;
    private Session session;


    public Feedback(int score, User user, String description, Session session) {
        this.score = score;
        this.user = user;
        this.description = description;
        this.session = session;
    }

    //JPA
    public Feedback() {
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

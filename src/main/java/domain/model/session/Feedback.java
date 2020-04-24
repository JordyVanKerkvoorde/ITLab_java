package domain.model.session;

import domain.model.user.User;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name= "Feedback")
public class Feedback{
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int feedbackId;
    @Column(name="Score")
    private int score;
    @Column(name="Description")
    private String description;
    @ManyToOne
    private User user;
    @ManyToOne
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

//    hebben we hier setters nodig?

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

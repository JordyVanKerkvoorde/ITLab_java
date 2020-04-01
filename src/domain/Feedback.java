package domain;

public class Feedback {
<<<<<<< HEAD
    private int feedbackId;
    private int score;
    private User user;
    private String description;


    public Feedback(int score, User user, String description) {
        this.score = score;
        this.user = user;
        this.description = description;
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
=======
>>>>>>> origin/master
}

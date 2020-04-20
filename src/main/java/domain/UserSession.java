package domain;

import javax.persistence.*;

@Entity
@Table(name = "UserSession")
public class UserSession {

    @Id
    @Column(name = "SessionId")
    private int sessionId;
    @Id
    @Column(name = "UserId")
    private String userId;
    @Transient
    @ManyToOne
    private User user;
    @Transient
    @ManyToOne
    private Session session;

    public UserSession(User user, Session session) {
        this.user = user;
        this.session = session;
        this.userId = user.getUserId();
        this.sessionId = session.getSessionId();
    }

    public UserSession(){

    }

    public User getUser() {
        return user;
    }

    public Session getSession() {
        return session;
    }
}

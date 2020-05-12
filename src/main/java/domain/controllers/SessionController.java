package domain.controllers;

import domain.model.session.Session;
import repository.SessionDao;
import repository.SessionDaoJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionController {
    private SessionDao sessionDao;
    private List<Session> sessions;

    public SessionController() {
        this.sessionDao = new SessionDaoJpa();
        loadSessions();
    }

    public void setSessionDao(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    /**
     * @param id is the id of the session in the db
     * @return returns session if found otherwise return null
     */
    public Session getSessionById(int id) {
        return sessions.stream().filter(p -> p.getSessionId() == id).findFirst().orElse(null);
    }

    public Session getSessionByTitle(String title){
        return sessions.stream().filter(p -> p.getTitle().equals(title)).findFirst().orElse(null);
    }

    public List<Session> getSessions(){
        return sessions;
    }

    private void loadSessions() {
        sessions = sessionDao.findAll();
    }

    public void addSession(Session session){
        sessionDao.createSession(session);
    }

    public void updateSession(Session updatedSession) {
        Session old = getSessionById(updatedSession.getSessionId());
        if (old == null) {
            throw new NullPointerException("Sessie niet gevonden.");
        }
        sessionDao.updateSession(old, updatedSession);
    }
}

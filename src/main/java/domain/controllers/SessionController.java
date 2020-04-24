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
        getSessions();
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

    private void getSessions() {
        sessions = sessionDao.findAll();
    }
}

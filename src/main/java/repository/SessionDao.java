package repository;

import domain.model.session.Session;

import javax.persistence.EntityNotFoundException;

public interface SessionDao extends GenericDao<Session>{
    void createSession(Session session);
    void updateSession(Session oldValue, Session newValue);
}

package repository;

import domain.model.session.Session;

import javax.persistence.EntityNotFoundException;

public interface SessionDao extends GenericDao<Session>{
    void createSession(Session session) throws Exception;
    void updateSession(Session oldValue, Session newValue) throws Exception;
}

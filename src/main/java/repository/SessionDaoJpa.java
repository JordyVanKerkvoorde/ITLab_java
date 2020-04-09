package repository;

import domain.Session;

import javax.persistence.EntityNotFoundException;

public class SessionDaoJpa extends GenericDaoJpa<Session> implements SessionDao {
    public SessionDaoJpa() {
        super(Session.class);
    }

    @Override
    public Session getSessionById(int id) throws EntityNotFoundException {
        return null;
    }
}

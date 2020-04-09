package repository;

import domain.Session;

import javax.persistence.EntityNotFoundException;

public interface SessionDao {
    public Session getSessionById(int id) throws EntityNotFoundException;
}

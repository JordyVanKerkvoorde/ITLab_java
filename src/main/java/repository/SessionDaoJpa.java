package repository;

import domain.controllers.SessionController;
import domain.model.session.Session;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class SessionDaoJpa extends GenericDaoJpa<Session> implements SessionDao {

    public SessionDaoJpa() {
        super(Session.class);
    }

    @Override
    public void createSession(Session session){
        try {
            startTransaction();
            insert(session);
            commitTransaction();


        }catch (Exception e){
            e.printStackTrace();
            rollbackTransaction();
        }
    }

    @Override
    public void updateSession(Session newValue){
        try{
            startTransaction();
            update(newValue);
            commitTransaction();

        }catch (Exception e){
            e.printStackTrace();
            rollbackTransaction();
        }
    }
}

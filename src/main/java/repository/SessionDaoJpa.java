package repository;

import domain.model.session.Session;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;

public class SessionDaoJpa extends GenericDaoJpa<Session> implements SessionDao {

    public SessionDaoJpa() {
        super(Session.class);
    }

    @Override
    public void createSession(Session session){
        try{
            em.getTransaction().begin();
            em.persist(session);
            em.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSession(Session oldValue, Session newValue){

    }
}

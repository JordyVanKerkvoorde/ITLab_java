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
            if (session.getSessionId() == 0) {
                session.setSessionId(SessionController.getInstance().getSessions().size() + 1);
            }
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

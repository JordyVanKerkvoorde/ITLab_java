package ITLab;

import domain.controllers.SessionController;
import domain.model.session.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DbMain {
    public static void main(String[] args){
        SessionController sessionController = new SessionController();
        Session session = sessionController.getSessionById(3);
        System.out.println(session.getTitle());
    }
}
